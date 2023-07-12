package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.dto.Game;
import com.G2.guessTheNumber.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;

public class GameDaoDB implements GameDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public GameDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final class GameMapper implements RowMapper<Game> {
        //maps a single row of the result set to a Game object
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            //creates new game object
            Game game = new Game();

            //sets gameId, answer, and status from ResultSet
            game.setGameId(rs.getInt("gameId"));
            game.setAnswer(rs.getString("answer"));
            game.setStatus(Status.valueOf(rs.getString("status")));
            return game;
        }
    }

    @Override
    public Game getGameById(int id) {
        return null;
    }

    @Override
    public Game createGame(Game game) {
        final String sql = "INSERT INTO Game(answer, status) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        //use JdbcTemplate to execute SQL statement
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            //sets values for prepared statement
            statement.setString(1, game.getAnswer());
            statement.setString(2, game.getStatus().name());
            return statement;

        }, keyHolder);

        //retrieves generated game ID and sets it in Game object
        game.setGameId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public List<Game> getAllGames() {
        return null;
    }

    @Override
    public void updateGame(Game game) {

    }

    @Override
    public void deleteGameById(int id) {

    }
}
