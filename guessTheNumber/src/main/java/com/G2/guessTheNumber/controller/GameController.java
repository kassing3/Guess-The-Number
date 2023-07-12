package com.G2.guessTheNumber.controller;

import com.G2.guessTheNumber.dto.Round;
import com.G2.guessTheNumber.service.GameServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameController {
    @Autowired
    GameServiceImpl gameServiceImpl;
    
    @GetMapping("/round/{id}")
    public List<Round> getAllRoundsById(@PathVariable int id) {
        //YOUR CODE STARTS HERE
        return gameServiceImpl.getAllRoundsById(id);
        //YOUR CODE ENDS HERE
    }
    
}