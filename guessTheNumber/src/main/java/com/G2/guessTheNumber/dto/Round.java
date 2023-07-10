package com.G2.guessTheNumber.dto;

import java.time.LocalDateTime;

public class Round {
    private int roundId;
    private int gameId;
    private String guess;
    private String resultGuess;
    private LocalDateTime roundTime;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResultGuess() {
        return resultGuess;
    }

    public void setResultGuess(String resultGuess) {
        this.resultGuess = resultGuess;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}
