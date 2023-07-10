package com.G2.guessTheNumber.dto;

import java.util.List;
import java.util.Objects;

public class Game {
    private int gameId;
    private String answer;
    private Status status;
    private List<Round> rounds;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return gameId == game.gameId && Objects.equals(answer, game.answer) && status == game.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, answer, status);
    }
}
