package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.dto.Game;
import com.G2.guessTheNumber.dto.Round;
import com.G2.guessTheNumber.dto.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RoundDaoDBTest {
    private JdbcTemplate jdbcTemplate;

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
    @DisplayName("Create a new round")
    void createRoundTest() {
        //Create a new game object with full details
        Game game = new Game();
        game.setAnswer("1234");
        game.setRounds(new ArrayList<>());
        game.setStatus(Status.IN_PROGRESS);

        // Add the new game to the db
        game = gameDao.createGame(game);

        //Create a new round with full details
        Round round = new Round();
        round.setGuess("4321");
        round.setGameId(game.getGameId());
        round.setResultGuess("e:0:p:4");

        // Set the time using withNano(0) so that it matches what comes out of the db
        round.setTime(Timestamp.valueOf(LocalDateTime.now().withNano(0)));

        // Add the new round to the db
        round = roundDao.createRound(round);

        // Retrieve the round from the db
        Round roundFromDao = roundDao.getRoundId(round.getRoundId());

        // Assert round from db is equal to local round
        assertEquals(round, roundFromDao);
    }
}