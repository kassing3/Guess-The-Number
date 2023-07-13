package com.G2.guessTheNumber.controller;

import com.G2.guessTheNumber.dto.Game;
import org.springframework.web.bind.annotation.GetMapping;
import com.G2.guessTheNumber.dto.Round;
import com.G2.guessTheNumber.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.G2.guessTheNumber.dao.GameDao;
import com.G2.guessTheNumber.dao.RoundDao;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {
    @Autowired
    GameServiceImpl gameServiceImpl;

    private final GameDao gameDao;
    private final RoundDao roundDao;

    public GameController(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED) //sets status to 201 when successfully created
    public Game create() {

        return gameServiceImpl.newGame();
    }

    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED) //sets status to 201 when successfully created
    public Round addGuess(@RequestBody Round round)  {

        return gameServiceImpl.addNewRound(round);
    }

    @GetMapping("/rounds/{id}")
    public List<Round> getAllRoundsById(@PathVariable int id) {
        //YOUR CODE STARTS HERE
        return gameServiceImpl.getAllRoundsById(id);
        //YOUR CODE ENDS HERE
    }
    @GetMapping("/games")
    public List<Game> getAllGames(){
        return gameServiceImpl.getAllGames();
    }

    @GetMapping("/game/{gameId}")
    public Game getGameById(@PathVariable int gameId) {

        return gameServiceImpl.getGameById(gameId);

    }

}
