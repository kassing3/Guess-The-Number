package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.dao.mappers.GameMapper;
import com.G2.guessTheNumber.dto.Game;

import java.util.List;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.G2.guessTheNumber.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;

    @Repository
    public class GameDaoDB implements GameDao {
        private final JdbcTemplate jdbcTemplate;

        @Autowired
        public GameDaoDB(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
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
            String sql = "SELECT * FROM game";
            return jdbcTemplate.query(sql, new com.G2.guessTheNumber.dao.mappers.GameMapper());
        }

//            @Override
//            public void updateGame (Game game){

        @Override
        @Transactional
        public void deleteGameById(int gameId){
            final String DELETE_IN_ROUND = "DELETE FROM round WHERE gameId = ?";
            jdbcTemplate.update(DELETE_IN_ROUND, gameId);
            final String DELETE_IN_GAME = "DELETE FROM game WHERE gameId = ?";
            jdbcTemplate.update(DELETE_IN_GAME, gameId);
        }
    }
