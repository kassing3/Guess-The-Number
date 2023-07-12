package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.dao.mappers.RoundMapper;
import com.G2.guessTheNumber.dto.Round;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoundDaoDB implements RoundDao{
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Round getRoundId(int id) {
        try {
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE roundId = ?";
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Round> getAllRoundsById(int gameId) {
        final String SELECT_ALL_ROUNDS = "SELECT * FROM round where gameID = ?";
        return jdbc.query(SELECT_ALL_ROUNDS, new RoundMapper(), gameId);
    }


    @Override
    public Round createRound(Round round) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
