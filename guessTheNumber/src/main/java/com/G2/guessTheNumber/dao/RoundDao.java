package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.dto.Round;

import java.util.List;

public interface RoundDao {
    Round getRoundId(int id);
    List<Round> getAllRoundsById(int gameId);
    Round createRound(Round round);

}
