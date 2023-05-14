package org.example.service;


import org.example.model.Question;
import org.example.repository.dao.QuestionRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionService {

    private final QuestionRepository questionRepository;

    private final Map<String, List<Question>> questionsByTopic = new HashMap<>();

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question getRandomQuestionByTopic(String topic) throws SQLException {
        List<Question> questions = questionsByTopic.containsKey(topic) ? questionsByTopic.get(topic) : questionRepository.getByTopic(topic);
        questionsByTopic.put(topic, questions);
        int randomNum = ThreadLocalRandom.current().nextInt(0, questions.size());
        return questions.get(randomNum);
    }

    public Question addQuestion(Question question) {
        questionRepository.save(question);
        return questionRepository.getLastQuestion(question.getText(), question.getTopic());
    }

    public void deleteQuestion(int id) {
        questionRepository.delete(id);
    }

    public Question getRandomQuestion() throws SQLException {
        List<Question> questions = questionRepository.getAllQuestions();
        int randomNum = ThreadLocalRandom.current().nextInt(0, questions.size());
        return questions.get(randomNum);
    }

    public Question getRandomQuestionImpr() {
        return questionRepository.getRndQuestImpr();
    }


}
