package com.G2.guessTheNumber.service;

import com.G2.guessTheNumber.dao.RoundDao;
import com.G2.guessTheNumber.dto.Round;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameServiceInterface{
    @Autowired
    RoundDao roundDao;
    
    public GameServiceImpl(RoundDao roundDao) {
        this.roundDao = roundDao;
    }
    
    public List<Round> getAllRoundsById(int gameId) {
        return roundDao.getAllRoundsById(gameId);
    }

}
