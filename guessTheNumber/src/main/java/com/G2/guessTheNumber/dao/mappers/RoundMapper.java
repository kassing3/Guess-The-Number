package com.G2.guessTheNumber.dao.mappers;


import com.G2.guessTheNumber.dto.Round;
import com.G2.guessTheNumber.dto.Status;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundMapper implements RowMapper<Round> {
    @Override
    public Round mapRow(ResultSet rs, int rowNum) throws SQLException {
        Round round = new Round();

        round.setRoundId(rs.getInt("roundId"));
        round.setGameId(rs.getInt("gameId"));
        round.setGuess(rs.getString("guess"));
        round.setResultGuess(rs.getString("resultGuess"));
        round.setTime(rs.getTimestamp("roundTime"));

        return round;
    }
}
