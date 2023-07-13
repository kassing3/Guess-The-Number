package com.G2.guessTheNumber.dao;

import com.G2.guessTheNumber.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import com.G2.guessTheNumber.dao.mappers.RoundMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class RoundDaoDB implements RoundDao {

    @Autowired
    JdbcTemplate jdbc;

    public RoundDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }


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
        final String SELECT_ALL_ROUNDS = "SELECT * FROM round where gameID = ? ORDER BY roundTime";
        return jdbc.query(SELECT_ALL_ROUNDS, new RoundMapper(), gameId);
    }


    @Override
    public Round createRound(Round round) {

        final String INSERT_ROUND = "INSERT INTO round(gameId, guess, resultGuess, roundTime) "
                + "VALUES(?,?,?,?)";

        // Creating a GeneratedKeyHolder to store the generated key (roundId)
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        // Executing the update using a lambda function with Connection and PreparedStatement
        jdbc.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(INSERT_ROUND, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, round.getGameId());
            statement.setString(2, round.getGuess());
            statement.setString(3, round.getResultGuess());
            statement.setTimestamp(4, round.getTime());
            return statement;
        }, keyHolder);

        // Retrieving the generated key (roundId) from the GeneratedKeyHolder and setting it on the round object
        round.setRoundId(keyHolder.getKey().intValue());
        return round;
    }
    @Override
    @Transactional
    public void deleteRoundById (int roundId){
        final String sql = "DELETE FROM round WHERE roundId = ?";
        jdbc.update(sql, roundId);
        }
}


