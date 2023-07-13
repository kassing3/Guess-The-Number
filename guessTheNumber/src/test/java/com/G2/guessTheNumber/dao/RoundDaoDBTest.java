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
    //TODO creating round object so we can access getGameId from round, I'm not sure if this is correct
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
    void getRoundId() {
    }

    @Test
    void getAllRoundsById() {
    }

    @Test
    void createRound() {
    }
}