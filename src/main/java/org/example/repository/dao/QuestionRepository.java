package org.example.repository.dao;

import org.example.model.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionRepository {
    Question get(int id) throws SQLException;

    void save(Question question);

    void update(int id, String topic);

    void delete(int id);

    List<Question> getByTopic(String topic) throws SQLException;
}
