package org.example;

import org.example.model.Question;
import org.example.repository.ConnectionSingleton;
import org.example.repository.QuestionRepositoryImpl;
import org.example.service.QuestionService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
/*
        Scanner scanner = new Scanner(System.in);
        QuestionService questionService = new QuestionService(new QuestionRepositoryImpl(ConnectionSingleton.getConnection()));

        scanner.next();
        System.out.println("Do you want a random question by topic?");
        while (scanner.next().equals("yes")){
            System.out.println("Topic name?");
            Question question = questionService.getRandomQuestionByTopic(scanner.next());
            System.out.println(question);
            System.out.println("Do you want the next question?");
        }


        System.out.println("Please type your question:");
        String text = scanner.nextLine();
        System.out.println("Please type question's topic:");
        String topic = scanner.nextLine();
        Question question = new Question(text, topic);
        Question newQuestion = questionService.addQuestion(question);
        System.out.println(newQuestion);


        System.out.println("Do you want to receive a question?");
        while (scanner.next().equals("yes")) {
            System.out.println("one more question?");
            Question question1 = questionService.getRandomQuestionImpr();
            System.out.println(question1.getText());
        }

 */
    }
}