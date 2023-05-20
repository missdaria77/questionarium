package org.example.repository;

import org.example.exceptions.*;
import org.example.model.Question;
import org.example.repository.dao.QuestionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {

    private final Connection connection;

    public QuestionRepositoryImpl(Connection connection) throws SQLException {
        this.connection = ConnectionSingleton.getConnection();
    }

    private final String findByID = """ 
            select * from questions where id = ?
            """;

    private final String findByTopic = """
            select * from questions where topic = ?
            """;

    private final String add = """
            insert into questions(text, topic)
            values(? , ?);
            """;

    private final String removeById = """
            delete from questions where id = ? 
            """;

    private final String updateTopic = """
            update questions set topic = ? where id = ?
            """;

    private final String getAll = """
            select * from questions
            """;

    private final String getLastQuestion = """ 
            with questionRanks as (select row_number() over (partition by text, topic 
            order by id desc) rk, id, text, topic from questions where text=? and topic=?)
            select id, text, topic from questionRanks where rk = 1 
            """;

    private final String getRndQuestion = """ 
               with rndQuestionId as (
               select row_number() over (order by random()) rk,
               id, text, topic from questions)
            select id, text, topic from rndQuestionId where rk = 1
            """;

    private final String checkIfExistId = """
            SELECT COUNT(1) FROM questions WHERE id = ?
            """;

    @Override
    public int checkIfExistId(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(checkIfExistId);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException e) {
            throw new SQLGetException(e.getMessage());
        }
    }

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
            throw new SQLGetException(e.getMessage());
        }
    }

    @Override
    public Question getRndQuestImpr() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getRndQuestion);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return Question.builder()
                    .id(resultSet.getInt("id"))
                    .text(resultSet.getString("text"))
                    .topic(resultSet.getString("topic"))
                    .build();
        } catch (SQLException e) {
            throw new SQLGetException(e.getMessage());
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
            throw new SQLGetByTopicException(e.getMessage());
        }
    }

    @Override
    public Question getLastQuestion(String text, String topic) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getLastQuestion);
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, topic);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return Question.builder()
                    .id(resultSet.getInt("id"))
                    .text(resultSet.getString("text"))
                    .topic(resultSet.getString("topic"))
                    .build();
        } catch (SQLException e) {
            throw new SQLGetByTopicException(e.getMessage());
        }
    }

    @Override
    public List<Question> getAllQuestions() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Question> allQuestions = new ArrayList<>();
            while (resultSet.next()) {
                allQuestions.add(Question.builder()
                        .id(resultSet.getInt("id"))
                        .text(resultSet.getString("text"))
                        .topic(resultSet.getString("topic"))
                        .build());
            }
            return allQuestions;
        } catch (SQLException e) {
            throw new SQLGetByTopicException(e.getMessage());
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
            throw new SQLSaveException(e.getMessage());
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
            throw new SQLUpdateException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeById);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SQLDeleteException(e.getMessage());
        }
    }


}
