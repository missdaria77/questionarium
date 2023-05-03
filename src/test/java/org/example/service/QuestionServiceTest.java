package org.example.service;

import org.example.repository.QuestionRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionServiceTest {
    private String user = "postgres";
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String password = "xxx";
    private Connection connection;

    @Before
    public void init() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    @Test
    public void getRandomQuestionTest() throws SQLException {
        QuestionService questionService = new QuestionService(new QuestionRepositoryImpl(connection));
        System.out.println(questionService.getRandomQuestionByTopic("incapsulation"));
    }
}
