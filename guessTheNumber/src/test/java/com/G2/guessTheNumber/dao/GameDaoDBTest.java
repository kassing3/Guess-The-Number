package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.dto.Game;
import com.G2.guessTheNumber.dto.Round;
import com.G2.guessTheNumber.dto.Status;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    @DisplayName("Get game by ID")
    void getGameById() {
        //Create a new game object with full details
        Game gameTest = new Game();
        gameTest.setAnswer("1234");
        gameTest.setStatus(Status.IN_PROGRESS);
        gameTest.setRounds(new ArrayList<>());
        // Add the new game to the db
        gameTest = gameDao.createGame(gameTest);

        // Retrieve the game from the db
        Game game = gameDao.getGameById(gameTest.getGameId());

        assertNotNull(game);
        assertEquals("1234", game.getAnswer());

        // Assert round from db is equal to local round
        assertEquals(gameTest, game);
    }

    @Test
    @DisplayName("Create a new game")
    void createGameTest() {
        //new instance of Game class created with each detail
        Game game = new Game();
        game.setAnswer("7432");
        game.setStatus(Status.IN_PROGRESS);
        game.setRounds(new ArrayList<>());

        game = gameDao.createGame(game);
        //validates game object has been initialized correctly
        assertEquals("7432", game.getAnswer());
        assertEquals(Status.IN_PROGRESS, game.getStatus());
        assertTrue(game.getRounds().isEmpty());

        Game gameFromDao = gameDao.getGameById(game.getGameId());
        assertEquals(game, gameFromDao);
    }

    @Test
    @DisplayName("Get all games")
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


    @Test
    @DisplayName("Update game")
    void updateGame() {
        //Create a new game object with full details
        Game gameTest = new Game();
        gameTest.setAnswer("9876");
        gameTest.setStatus(Status.IN_PROGRESS);
        gameTest.setRounds(new ArrayList<>());
        gameTest = gameDao.createGame(gameTest);  // Add the new game to the db

        // Retrieve the game from the db
        Game gameFromDao = gameDao.getGameById(gameTest.getGameId());

        // Assert game from db is equal to local game
        assertEquals(gameTest, gameFromDao);

        //Update local object status
        gameTest.setStatus(Status.FINISHED);

        //Update the game in the db
        gameDao.updateGame(gameTest);

        //Retrieve the updated object
        gameFromDao = gameDao.getGameById(gameTest.getGameId());

        //Assert the updated object has status now as Finished
        assertEquals(Status.FINISHED, gameFromDao.getStatus());

        //Assert it is the same as updated local object
        assertEquals(gameTest, gameFromDao);

    }
}
