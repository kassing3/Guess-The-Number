package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.dao.mappers.GameMapper;
import com.G2.guessTheNumber.dto.Game;

import java.util.List;

public class GameDaoDB {
    @Override
    public List<Game> getAllGames() {
        String sql = "SELECT * FROM game";
        return jdbcTemplate.query(sql, new GameMapper());
    }
}
