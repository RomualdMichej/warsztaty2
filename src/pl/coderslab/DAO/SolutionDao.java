package pl.coderslab.DAO;

import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionDao {

    private static final String CREATE_SOLTION_QUERY =
            "INSERT INTO solution(created, description, exercise_id, user_id)" +
                    " VALUES (NOW(), ?, ?, ?)";
    private static final String READ_SOLTION_QUERY =
            "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLTION_QUERY =
            "UPDATE solution SET updated = NOW(), description = ?, exercise_id = ?, user_id = ? where id = ?";
    private static final String DELETE_SOLTION_QUERY =
            "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLTION_QUERY =
            "SELECT * FROM solution";
    private static final String FIND_ALL_SOLTION_BY_USER_ID_QUERY =
            "SELECT * FROM solution where user_id = ?";
    private static final String FIND_ALL_SOLTION_BY_EXERCISE_ID_QUERY =
            "SELECT * FROM solution where exercise_id = ? ORDER BY updated DESC";
    
    public Solution create(Solution solution) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, solution.getDescription());
            statement.setInt(2, solution.getExercise_id());
            statement.setInt(3, solution.getUser_id());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution read(int solutionId) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLTION_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUser_id(resultSet.getInt("user_id"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(Solution solution) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLTION_QUERY);
            statement.setString(1, solution.getDescription());
            statement.setInt(2, solution.getExercise_id());
            statement.setInt(3, solution.getUser_id());
            statement.setInt(4, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int solutionId) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLTION_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Solution> findAll() {
        List<Solution> list = new ArrayList();
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLTION_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUser_id(resultSet.getInt("user_id"));
                list.add(solution);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

    public List<Solution> findAllByUserId(int userId) {
        List<Solution> list = new ArrayList();
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLTION_BY_USER_ID_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUser_id(resultSet.getInt("user_id"));
                list.add(solution);

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Solution> findAllByExerciseId(int exerciseId) {
        List<Solution> list = new ArrayList();
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLTION_BY_EXERCISE_ID_QUERY);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUser_id(resultSet.getInt("user_id"));
                list.add(solution);

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
