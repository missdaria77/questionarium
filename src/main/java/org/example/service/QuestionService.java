package org.example.service;


import org.example.model.Question;
import org.example.repository.dao.QuestionRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionService {

    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question getRandomQuestionByTopic(String topic) throws SQLException {
        List<Question> questions = questionRepository.getByTopic(topic);
        int randomNum = ThreadLocalRandom.current().nextInt(0, questions.size());
        return questions.get(randomNum);
    }

}
