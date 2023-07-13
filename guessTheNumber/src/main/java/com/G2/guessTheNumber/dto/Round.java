package com.G2.guessTheNumber.dto;

import java.sql.Timestamp;
import java.util.Objects;


public class Round {
    private int roundId;
    private int gameId;
    private String guess;
    private String resultGuess;
    private Timestamp roundTime;

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

    public Timestamp getTime() {
        return roundTime;
    }

    public void setTime(Timestamp time) {
        this.roundTime = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Round)) return false;
        Round round = (Round) o;
        return getRoundId() == round.getRoundId() && getGameId() == round.getGameId() && Objects.equals(getGuess(), round.getGuess()) && Objects.equals(getResultGuess(), round.getResultGuess()) && Objects.equals(roundTime, round.roundTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoundId(), getGameId(), getGuess(), getResultGuess(), roundTime);
    }
}
