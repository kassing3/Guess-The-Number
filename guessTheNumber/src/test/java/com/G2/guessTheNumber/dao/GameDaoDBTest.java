package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.TestApplicationConfiguration;
import com.G2.guessTheNumber.dto.Game;
import com.G2.guessTheNumber.dto.Round;
import com.G2.guessTheNumber.dto.Status;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)

public class GameDaoDBTest {
    @Autowired

    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public GameDaoDBTest(){
    }
    //TODO creating round object so we can access getGameId from round
    Round round = new Round();
    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

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
    void testGetGameById() {
    }

    @Test
    void createGame() {
        Game game = new Game();
        game.setGameId(1);
        game.setAnswer("Test Answer");
        game.setStatus(Status.IN_PROGRESS);
        game.setRounds(new ArrayList<>());

        assertEquals(1, game.getGameId());
        assertEquals("Test Answer", game.getAnswer());
        assertEquals(Status.IN_PROGRESS, game.getStatus());
        assertTrue(game.getRounds().isEmpty());
    }

    @Test
    public void testGetAllGames() {
        Game game1 = new Game();
        game1.setGameId(1);
        game1.setAnswer("1423");
        game1.setStatus(Status.FINISHED);
        List<Round> roundTest1 = new ArrayList<>();
        Round round1 = new Round();
        round1.setRoundId(1);
        round1.setGameId(1);
        round1.setGuess("2314");
        round1.setResultGuess("e:0 p:4");
        round1.setTime(Timestamp.valueOf("2023-01-12 05:09:58"));
        roundTest1.add(round1);

        Round round2 = new Round();
        round2.setRoundId(2);
        round2.setGameId(1);
        round2.setGuess("1423");
        round2.setResultGuess("e:4 p:0");
        round2.setTime(Timestamp.valueOf("2023-01-12 20:25:09"));
        roundTest1.add(round2);

        game1.setRounds(roundTest1);
        game1 = gameDao.createGame(game1);

        Game game2 = new Game();
        game2.setGameId(2);
        game2.setAnswer("3412");
        game2.setStatus(Status.IN_PROGRESS);
        List<Round> roundTest2 = new ArrayList<>();
        Round round3 = new Round();
        round3.setRoundId(1);
        round3.setGameId(2);
        round3.setGuess("3214");
        round3.setResultGuess("e:2 p:2");
        round3.setTime(Timestamp.valueOf("2023-07-12 20:09:58"));
        roundTest2.add(round3);

        Round round4 = new Round();
        round4.setRoundId(2);
        round4.setGameId(2);
        round4.setGuess("3241");
        round4.setResultGuess("e:2 p:2");
        round4.setTime(Timestamp.valueOf("2023-07-12 20:10:09"));
        roundTest2.add(round4);

        game2.setRounds(roundTest2);
        game2 = gameDao.createGame(game2);

        List<Game> games = gameDao.getAllGames();
        assertEquals(2, games.size());
        assertTrue(games.contains(game1));
        assertTrue(games.contains(game2));

    }
}