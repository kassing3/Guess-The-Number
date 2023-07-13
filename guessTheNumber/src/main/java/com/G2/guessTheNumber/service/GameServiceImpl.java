package com.G2.guessTheNumber.service;

import com.G2.guessTheNumber.dto.Game;
import com.G2.guessTheNumber.dto.Status;

import java.util.*;

import com.G2.guessTheNumber.dao.GameDao;
import com.G2.guessTheNumber.dao.RoundDao;
import com.G2.guessTheNumber.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class GameServiceImpl implements GameServiceInterface {

    @Autowired
    GameDao gameDao;
    @Autowired
    RoundDao roundDao;

    public List<Round> getAllRoundsById(int gameId) {
        return roundDao.getAllRoundsById(gameId);
    }

    public Game newGame() {
        Game game = new Game();
        Random random = new Random();
        //stores digits of the answer
        StringBuilder answer = new StringBuilder();

        //generates 4-digit answer for game
        for (int i = 0; i < 4; i++) {
            int num = random.nextInt(10);
            answer.append(num);
        }
        //sets answer and status of game
        game.setAnswer(answer.toString());
        game.setStatus(Status.IN_PROGRESS);
        return game;
    }

    @Override
    public Round addNewRound(Round round) {
        //Get and set the current timestamp
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        round.setTime(currentTimestamp);

        // Add new guess error handling
        // Game id empty or nonexistent game
        if (round.getGameId() == 0
            //TODO  || gameDao.getGameById(round.getRoundId()) == null
        ) {
            round.setResultGuess("ERROR: Please enter valid game id.");
            return round;
        }
        //TODO
//        else if(gameDao.getGameById(round.getGameId()).getStatus().equals(Status.FINISHED)) {
//            round.setResultGuess("ERROR: Game is already finished.");
//            return round;
//        }
        // Guess is empty or does not have 4 char
        else if (round.getGuess().toString().isEmpty()
                || round.getGuess().toString().length() != 4) {
            round.setResultGuess("ERROR: Guess should be 4 distinct numbers");
            return round;
        } else if (hasDuplicates(round.getGuess())) {
            round.setResultGuess("ERROR: No duplicate numbers allowed");
            return round;
        }

        // Set the result guess
        String resultGuess = calculateResultGuess(round);
        round.setResultGuess(resultGuess);

        //Mark the game finished if the result has 4 exact matches
        if (resultGuess.equals("e:4:p:0")) {
            gameDao.getGameById(round.getGameId()).setStatus(Status.FINISHED);
        }

        return roundDao.createRound(round);
    }

    // PRIVATE HELPER FUNCTIONS
    private String calculateResultGuess(Round round) {
        int exactMatches = 0;
        int partialMatches = 0;

        int gameId = round.getGameId();
//        TODO : String gameResult = gameDao.getGameById(gameId).getAnswer();
        String gameResult = "4321"; //sample game result

        String userGuess = round.getGuess();
        // Calculate exact matches
        for (int i = 0; i < 4; i++) {
            char guessDigit = userGuess.charAt(i);
            char secretDigit = gameResult.charAt(i);

            if (guessDigit == secretDigit) {
                exactMatches++;
            } else if (gameResult.contains(String.valueOf(guessDigit))) {
                partialMatches++;
            }
        }

        return "e:" + exactMatches + ":p:" + partialMatches;
    }

    private boolean hasDuplicates(String sequence) {
        Set<Character> charSet = new HashSet<>();

        for (char c : sequence.toCharArray()) {
            if (charSet.contains(c)) {
                return true;
            }
            charSet.add(c);
        }
        //No duplicates were found
        return false;
    }
    @Override
    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            if (game.getStatus().equals(Status.IN_PROGRESS)) {
                game.setAnswer("****");
            }
        }
        return games;
    }
    @Override
    public void setTime(Round round) {
        Calendar calendar = Calendar.getInstance();
        Timestamp guessTime = new Timestamp(calendar.getTime().getTime());

        round.setTime(guessTime);
    }


}

