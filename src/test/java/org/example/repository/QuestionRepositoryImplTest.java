package org.example.repository;

import org.example.model.Question;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionRepositoryImplTest {

    private String user = "postgres";
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String password = "xxx";
    private Connection connection;

    @Before
    public void init() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    @Test
    public void getTest() {
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        int id = 2;
        System.out.println(questionRepository.get(id));
    }

    @Test
    public void getByTopicTest() {
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        String topic = "OOP";
        System.out.println(questionRepository.getByTopic(topic));
    }

    @Test
    public void saveTest() {
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        Question question = new Question(8, "oop principles", "oop");
        questionRepository.save(question);
        System.out.println("new question was added");
    }

    @Test
    public void updateTest() {
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        String topic = "oop";
        int id = 2;
        questionRepository.update(id, topic);
        System.out.println("question " + id + " was updated!");
    }

    @Test
    public void deleteTest() {
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        int id = 7;
        questionRepository.delete(id);
        System.out.println("question " + id + " was deleted!");
    }
}
