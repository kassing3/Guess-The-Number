package com.G2.guessTheNumber.service;

import com.G2.guessTheNumber.dto.Game;
import com.G2.guessTheNumber.dto.Round;

public interface GameServiceInterface {
    Game newGame();

    Round addNewRound(Round round);

}