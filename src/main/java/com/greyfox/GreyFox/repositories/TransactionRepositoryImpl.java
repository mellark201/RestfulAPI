package com.greyfox.GreyFox.repositories;

import com.greyfox.GreyFox.domain.Transaction;
import com.greyfox.GreyFox.exceptions.ETBadRequestException;
import com.greyfox.GreyFox.exceptions.ETResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository

public class TransactionRepositoryImpl implements TransactionRepository {

    private final static String SQL_CREATE = "INSERT INTO ET_TRANSACTIONS (TRANSACTION_ID, CATEGORY_ID, USER_ID, " +
            "AMOUNT, NOTE, TRANSACTION_DATE) VALUES(NEXTVAL('ET_TRANSACTIONS_SEQ'), ?, ?, ?, ?, ?";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM ET_TRANSACTIONS WHERE USER_ID = ? AND CATEGORY_ID = ?" +
            " AND TRANSACTION_ID = ?";
    private final static String SQL_FIND_ALL = "SELECT * FROM ET_TRANSACTIONS WHERE USER_ID = ? AND CATEGORY_ID + ?";
    private final static String SQL_UPDATE = "UPDATE ET_TRANSACTIONS SET AMOUNT=?, NOTE=?, TRANSACTION_DATE=? WHERE " +
            "USER_ID=? AND CATEGORY_ID=? AND TRANSACTION_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Transaction> findAll(Integer userId, Integer categoryId) {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{userId, categoryId}, transactionRowMapper);
        } catch (Exception e) {
            throw new ETBadRequestException("Incorrect Details");
        }
    }

    @Override
    public Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws ETResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, categoryId, transactionId},
                    transactionRowMapper);
        } catch(Exception e) {
            throw new ETBadRequestException("No resource found");
        }
    }

    @Override
    public Integer create(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws ETBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, categoryId);
                ps.setInt(2, userId);
                ps.setDouble(3, amount);
                ps.setString(4, note);
                ps.setLong(5, transactionDate);
                return ps;
            }, keyHolder);
            return (Integer)keyHolder.getKeys().get("TRANSACTION_ID");
        } catch(Exception e) {
            throw new ETBadRequestException("Invalid Transaction Details");
        }
    }

    @Override
    public void update(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws ETBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{transaction.getAmount(), transaction.getNote(),
                    transaction.getTransactionDate()}, userId, categoryId, transactionId);
        } catch (Exception e) {
            throw new ETBadRequestException("Incorrect Request");
        }
    }

    @Override
    public void removeById(Integer userId, Integer categoryId, Integer transactionId) throws ETResourceNotFoundException {

    }

    private RowMapper<Transaction> transactionRowMapper = ((rs, rowNum) -> {
        return new Transaction(rs.getInt("TRANSACTION_ID"),
                        rs.getInt("USER_ID"),
                        rs.getInt("CATEGORY_ID"),
                        rs.getDouble("AMOUNT"),
                        rs.getString("NOTE"),
                        rs.getLong("TIMESTAMP"));
    });
}
