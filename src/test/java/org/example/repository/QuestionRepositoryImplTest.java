package org.example.repository;

import org.example.model.Question;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class QuestionRepositoryImplTest {

    private Connection connection;
    @Before
    public void init() throws SQLException {
        connection = ConnectionSingleton.getConnection();
    }
    @Test
    public void saveTest() throws SQLException {
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        Question question = new Question("oop principles111", "oop");
        questionRepository.save(question);
        System.out.println("new question was added");
    }
    @Test
    public void getTest() throws SQLException {
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        int id = 2;
        System.out.println(questionRepository.get(id));
    }

    @Test
    public void getByTopicTest() throws SQLException {
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        String topic = "OOP";
        System.out.println(questionRepository.getByTopic(topic));
    }


    @Test
    public void updateTest() throws SQLException {
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        String topic = "oop";
        int id = 2;
        questionRepository.update(id, topic);
        System.out.println("question " + id + " was updated!");
    }

    @Test
    public void deleteTest() throws SQLException {
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        int id = 7;
        questionRepository.delete(id);
        System.out.println("question " + id + " was deleted!");
    }
}
