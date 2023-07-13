package com.G2.guessTheNumber.service;

import com.G2.guessTheNumber.dto.Game;

import java.util.List;
import com.G2.guessTheNumber.dto.Round;

public interface GameServiceInterface {
    public List<Game> getAllGames();
    Game newGame();
    public void setTime(Round round);

    Round addNewRound(Round round);
}


