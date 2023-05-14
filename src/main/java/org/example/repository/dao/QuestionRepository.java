package org.example.repository.dao;

import org.example.model.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionRepository {
    int checkIfExistId(int id);

    Question get(int id) throws SQLException;

    void save(Question question);

    void update(int id, String topic);

    void delete(int id);

    Question getRndQuestImpr();

    List<Question> getByTopic(String topic) throws SQLException;

    Question getLastQuestion(String text, String topic);

    List<Question> getAllQuestions();

}
