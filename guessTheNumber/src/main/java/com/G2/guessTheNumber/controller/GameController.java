package com.G2.guessTheNumber.controller;

import com.G2.guessTheNumber.dto.Round;
import com.G2.guessTheNumber.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    GameServiceImpl gameService;

    @PostMapping("/guess")
    public Round addGuess(@RequestBody Round round)  {

       return gameService.addNewRound(round);
    }
}
