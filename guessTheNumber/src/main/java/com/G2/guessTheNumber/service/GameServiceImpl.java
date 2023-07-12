package com.G2.guessTheNumber.service;

import com.G2.guessTheNumber.dto.Game;
import com.G2.guessTheNumber.dto.Status;

import java.util.List;

public class GameServiceImpl implements GameServiceInterface{
    @Override
    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            if (status != Status.FINISHED) {
                game.setAnswer("****");
            }
        }
        return games;
    }

}
