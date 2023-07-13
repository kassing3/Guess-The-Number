package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.dto.Game;

import java.util.List;

public interface GameDao {
    Game getGameById(int id);
    Game createGame(Game game);
    List<Game> getAllGames();
    void updateGame(Game game);
    void deleteGameById(int id);


}
