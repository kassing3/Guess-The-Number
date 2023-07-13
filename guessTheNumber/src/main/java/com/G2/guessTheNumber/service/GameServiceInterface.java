package com.G2.guessTheNumber.service;

import com.G2.guessTheNumber.dto.Game;

import java.util.List;
import com.G2.guessTheNumber.dto.Round;

public interface GameServiceInterface {

    Game newGame();
    Game getGameById(int gameId);
    public List<Game> getAllGames();
    Round addNewRound(Round round);
}


