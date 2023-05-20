package org.example.service;

import org.example.model.Question;
import org.example.repository.ConnectionSingleton;
import org.example.repository.QuestionRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;


public class QuestionServiceTest {
    private Connection connection;
    private final QuestionRepositoryImpl impl = new QuestionRepositoryImpl(connection);
    QuestionService questionService = new QuestionService(impl);

    public QuestionServiceTest() throws SQLException {
    }

    @Before
    public void init() throws SQLException {
        connection = ConnectionSingleton.getConnection();
    }

    @Test
    public void getRandomQuestionByTopicTest() throws SQLException {
        String topic = "incapsulation";

        Question rndQuestByTopic = questionService.getRandomQuestionByTopic(topic);
        Assert.assertTrue(impl.getByTopic(topic).contains(rndQuestByTopic));
    }

    @Test
    public void getRandomQuestionTest() throws SQLException {
        Question randomQuestion = questionService.getRandomQuestion();
        Assert.assertTrue(impl.getAllQuestions().contains(randomQuestion));
    }

    @Test
    public void getRandomQuestionImprTest() throws SQLException {
        Question question = questionService.getRandomQuestionImpr();
        System.out.println(question);
        Assert.assertTrue(impl.getAllQuestions().contains(question));
    }

    @Test
    public void addQuestionTest() throws SQLException {
        Question question = new Question("Java VS Python", "TestTopic");
        Question questionTest = questionService.addQuestion(question);
        question.setId(questionTest.getId());
        Assert.assertEquals(questionTest, question);
        impl.delete(question.getId());
    }

    @Test
    public void deleteQuestionTest() throws SQLException {
        Question question = new Question("Java VS Python", "TestTopic");
        Question questionTest = questionService.addQuestion(question);
        questionService.deleteQuestion(questionTest.getId());
        Assert.assertEquals(impl.checkIfExistId(questionTest.getId()), 0);
    }


}
