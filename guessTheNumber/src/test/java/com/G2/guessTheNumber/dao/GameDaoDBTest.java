package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.dto.Game;
import com.G2.guessTheNumber.dto.Round;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GameDaoDBTest {
    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public GameDaoDBTest(){
    }
    //TODO creating round object so we can access getGameId from round
    Round round = new Round();

    @BeforeEach
    void setUp() {
        List<Game> games = gameDao.getAllGames();
        for(Game game : games) {
            gameDao.deleteGameById(game.getGameId());
        }
        //TODO not sure what to put inside of the getALlRoundsById
        List<Round> rounds = roundDao.getAllRoundsById(round.getGameId());
        for(Round round : rounds) {
            roundDao.deleteRoundById(round.getRoundId());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getGameById() {
    }

    @Test
    void createGame() {
    }

    @Test
    void getAllGames() {
    }
}