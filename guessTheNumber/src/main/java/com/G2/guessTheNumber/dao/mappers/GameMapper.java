package com.G2.guessTheNumber.dao.mappers;

import com.G2.guessTheNumber.dto.Game;
import com.G2.guessTheNumber.dto.Status;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper implements RowMapper<Game> {
    @Override
    public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
        Game game = new Game();

        game.setGameId(rs.getInt("gameId"));
        game.setAnswer(rs.getString("answer"));
        game.setStatus(Status.valueOf(rs.getString("status")));

        return game;
    }
}
