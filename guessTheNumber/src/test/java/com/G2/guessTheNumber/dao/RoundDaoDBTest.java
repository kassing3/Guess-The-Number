package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.TestApplicationConfiguration;
import com.G2.guessTheNumber.dto.Game;
import com.G2.guessTheNumber.dto.Round;
import com.G2.guessTheNumber.service.GameServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
//@SpringBootTest
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
        Round round = new Round();
        round.setRoundId(1);
        round.setGameId(2);
        round.setGuess("1234");
        round.setResultGuess("e:2:p:1");
        round.setTime(new Timestamp(System.currentTimeMillis()));

        assertEquals(1, round.getRoundId());
        assertEquals(2, round.getGameId());
        assertEquals("1234", round.getGuess());
        assertEquals("e:2:p:1", round.getResultGuess());
        assertNotNull(round.getTime());
    }

}