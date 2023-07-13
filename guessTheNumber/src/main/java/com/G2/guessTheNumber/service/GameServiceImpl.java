package com.G2.guessTheNumber.service;

import com.G2.guessTheNumber.dto.Game;
import com.G2.guessTheNumber.dto.Status;

import java.sql.Array;
import java.util.*;

import com.G2.guessTheNumber.dao.GameDao;
import com.G2.guessTheNumber.dao.RoundDao;
import com.G2.guessTheNumber.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

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
        StringBuilder answer = new StringBuilder();
//        Random random = new Random();
        //stores digits of the answer

        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        for ( int j = 0; j < 4; j++) {

            answer.append(numbers.get(j));

        }

        game.setAnswer(answer.toString());
        game.setStatus(Status.IN_PROGRESS);

        return game;
    }

    @Override
    public Game getGameById(int gameId) {
        try {

            Game game  = gameDao.getGameById(gameId);
            List rounds = roundDao.getAllRoundsById(gameId);

            game.setRounds(rounds);
            game.getRounds();

            if (game.getStatus() == Status.IN_PROGRESS) {
                game.setAnswer("xxxx");
            }

            return game;

        } catch (DataAccessException e) {

            System.out.println(e);

            return null;
        }
    }

    @Override
    public Round addNewRound(Round round) {
        //Get and set the current timestamp
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        round.setTime(currentTimestamp);

        // Add new guess error handling
        // Game id empty or nonexistent game
        if (round.getGameId() == 0
                || getGameById(round.getGameId()) == null
        ) {
            round.setResultGuess("ERROR: Please enter valid game id.");
            return round;
        }
        // If game is done
        else if(getGameById(round.getGameId()).getStatus().equals(Status.FINISHED)) {
            round.setResultGuess("ERROR: Game is already finished.");
            return round;
        } // Guess is empty or does not have 4 char
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
            System.out.println("Correct guess ");
            Game updatedGame = getGameById(round.getGameId());
            updatedGame.setStatus(Status.FINISHED);

            System.out.println(updatedGame.getStatus());
        }

        return roundDao.createRound(round);
    }

    // PRIVATE HELPER FUNCTIONS
    private String calculateResultGuess(Round round) {
        int exactMatches = 0;
        int partialMatches = 0;

        int gameId = round.getGameId();

        String gameResult = gameDao.getGameById(gameId).getAnswer();

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
            List rounds = roundDao.getAllRoundsById(game.getGameId());

            game.setRounds(rounds);

            if (game.getStatus().equals(Status.IN_PROGRESS)) {
                game.setAnswer("****");
            }
        }
        return games;
    }


}

