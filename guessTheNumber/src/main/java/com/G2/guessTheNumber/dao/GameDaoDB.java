package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.dto.Game;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class GameDaoDB implements GameDao{
    @Override
    public Game getGameById(int id) {
        return null;
    }

    @Override
    public Game createGame(Game game) {
        return null;
    }

    @Override
    public List<Game> getAllGames() {
        return null;
    }

    @Override
    public void updateGame(Game game) {

    }

    @Override
    public void deleteGameById(int id) {

    }
}
