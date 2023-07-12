package com.G2.guessTheNumber.service;

import com.G2.guessTheNumber.dto.Game;
import com.G2.guessTheNumber.dto.Status;
import com.G2.guessTheNumber.dao.RoundDao;
import com.G2.guessTheNumber.dto.Round;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameServiceImpl implements GameServiceInterface {
    @Autowired
    RoundDao roundDao;

    public GameServiceImpl(RoundDao roundDao) {
        this.roundDao = roundDao;
    }

    public List<Round> getAllRoundsById(int gameId) {
        return roundDao.getAllRoundsById(gameId);
    }

    public Game newGame() {
        Game game = new Game();
        Random random = new Random();
        //stores digits of the answer
        StringBuilder answer = new StringBuilder();

        //generates 4-digit answer for game
        for (int i = 0; i < 4; i ++) {
            int num = random.nextInt(10);
            answer.append(num);
        }
        //sets answer and status of game
        game.setAnswer(answer.toString());
        game.setStatus(Status.IN_PROGRESS);
        return game;
    }

}
