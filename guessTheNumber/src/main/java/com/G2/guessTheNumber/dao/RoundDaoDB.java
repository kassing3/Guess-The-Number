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

@Repository
public class RoundDaoDB implements RoundDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public RoundDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Round getRoundId(int id) {
        return null;
    }

    @Override
    public List<Round> getAllRounds(int gameId) {
        return null;
    }

    @Override
    public Round createRound(Round round) {

        final String INSERT_ROUND = "INSERT INTO round(gameId, guess, resultGuess, roundTime) "
                + "VALUES(?,?,?,?)";

        // Creating a GeneratedKeyHolder to store the generated key (roundId)
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        // Executing the update using a lambda function with Connection and PreparedStatement
        jdbcTemplate.update((Connection conn) -> {
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

}
