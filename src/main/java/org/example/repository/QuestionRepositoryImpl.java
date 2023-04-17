package org.example.repository;

import org.example.model.Question;
import org.example.repository.dao.QuestionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {

    private Connection connection;

    public QuestionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private String findByID = """ 
            select * from questions where id = ?
            """;

    private String findByTopic = """
            select * from questions where topic = ?
            """;

    private String add = """
            insert into questions(text, topic)
            values(? , ?);
            """;

    private String removeById = """
            delete from questions where id = ? 
            """;

    private String updateTopic = """
            update questions set topic = ? where id = ?
            """;

    @Override
    public Question get(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findByID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return Question.builder()
                    .id(resultSet.getInt("id"))
                    .text(resultSet.getString("text"))
                    .topic(resultSet.getString("topic"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Question> getByTopic(String topic) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findByTopic);
            preparedStatement.setString(1, topic);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Question> questions = new ArrayList<>();
            while (resultSet.next()) {
                questions.add(Question.builder()
                        .id(resultSet.getInt("id"))
                        .text(resultSet.getString("text"))
                        .topic(resultSet.getString("topic"))
                        .build());
            }
            return questions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Question question) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1, question.getText());
            preparedStatement.setString(2, question.getTopic());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, String topic) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateTopic);
            preparedStatement.setString(1, topic);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeById);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
