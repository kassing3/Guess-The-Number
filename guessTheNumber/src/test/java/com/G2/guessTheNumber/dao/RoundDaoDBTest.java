package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.dto.Game;
import com.G2.guessTheNumber.dto.Round;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RoundDaoDBTest {

    @Autowired
    RoundDao roundDao;

    @Autowired
    GameDao gameDao;

    public RoundDaoDBTest(){
    }

    @BeforeEach
    void setUp() {
        // Get all the games
        List<Game> games = gameDao.getAllGames();
        for(Game game : games) {
            //First delete all the rounds of the current game
            List<Round> rounds = roundDao.getAllRoundsById(game.getGameId());
            for(Round round : rounds) {
                roundDao.deleteRoundById(round.getRoundId());
            }

            //Then delete the game
            gameDao.deleteGameById(game.getGameId());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRoundIdTest() {
    }

    @Test
    void getAllRoundsByIdTest() {
    }

    @Test
    void createRoundTest() {
    }
}