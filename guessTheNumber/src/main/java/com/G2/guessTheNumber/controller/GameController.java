package com.G2.guessTheNumber.controller;

import com.G2.guessTheNumber.dto.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {
    @GetMapping("/game")
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }
}
