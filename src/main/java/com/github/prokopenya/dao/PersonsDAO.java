package com.github.prokopenya.dao;

import com.github.prokopenya.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonsDAO implements DAO<Person> {
    private static final Logger logger = LogManager.getLogger();

    private final Connection conn;
    private final PreparedStatement removePrepStatement;
    private final PreparedStatement addPrepStatement;
    private final PreparedStatement getByIdPrepStatement;
    private final PreparedStatement getAllPrepStatement;

    public PersonsDAO(Connection conn) {
        this.conn = conn;

        try {
            this.removePrepStatement  = conn.prepareStatement(Queries.StudentTable.REMOVE_BY_ID);
            this.addPrepStatement     = conn.prepareStatement(Queries.StudentTable.ADD);
            this.getByIdPrepStatement = conn.prepareStatement(Queries.StudentTable.GET_BY_ID);
            this.getAllPrepStatement  = conn.prepareStatement(Queries.StudentTable.GET_ALL);
        } catch (SQLException e) {
            logger.error("Can't create prepared statement");
            throw new IllegalStateException(e);
        }
    }

    public void createTable() throws SQLException {
        logger.info("Trying to create table {}", Queries.StudentTable.TABLE_NAME);

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(Queries.StudentTable.CREATE);
            logger.info("Table {} created.", Queries.StudentTable.TABLE_NAME);
        }
        catch (SQLException e) {
            logger.error("Can't create table {}", Queries.StudentTable.TABLE_NAME);
            throw new SQLException(e);
        }
    }

    @Override
    public void add(Person person) throws SQLException {
        logger.info("Adding {}", person);

        addPrepStatement.setLong(1, person.getId());
        addPrepStatement.setString(2, person.getName());
        addPrepStatement.setString(3, person.getPhone());

        logger.info("Element {} added to {} table", person, Queries.StudentTable.TABLE_NAME);
    }

    @Override
    public void remove(int id) throws SQLException {
        logger.info("Remove person by id: {}", id);

        removePrepStatement.setLong(1, id);

        if (removePrepStatement.executeUpdate() > 0) {
            logger.info("Element with id {} removed from {} table", id, Queries.StudentTable.TABLE_NAME);
        } else {
            logger.error("Can't remove element with id {} from {} table", id, Queries.StudentTable.TABLE_NAME);
        }

        logger.info("Person with id: {} removed", id);
    }

    @Override
    public Optional<Person> get(int id) throws SQLException {
        logger.info("Get person by id: {}", id);

        getByIdPrepStatement.setLong(1, id);

        ResultSet res = getByIdPrepStatement.executeQuery();

        if (res.next()) {
            final String name = res.getString(2);
            final String phone = res.getString(3);

            logger.info("Person with id: {} returned", id);

            return Optional.of(new Person(id, name, phone));
        } else {
            logger.info("Person with id: {} doesn't exist", id);
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Person>> getAll() throws SQLException {
        logger.info("get all persons ");

        ResultSet res = getAllPrepStatement.executeQuery();
        List<Person> persons = new ArrayList<>();

        if (!res.next()){
            logger.info("table is empty");
            return Optional.empty();
        }
        //rewrite
        while (res.next()) {
            logger.info("person id =", res.getLong(1));
            Person p = new Person(res.getLong(1), res.getString(2),res.getString(3));
            persons.add(p);
        }
        return Optional.of(persons);
    }

    @Override
    public void clear() throws SQLException {
        logger.info("Trying to clear table {}", Queries.StudentTable.TABLE_NAME);

        try (Statement stmt = conn.createStatement()) {
            final int numOfRows = stmt.executeUpdate(Queries.StudentTable.CLEAR_TABLE);

            logger.info("Table {} cleared, removed {} rows.", Queries.StudentTable.TABLE_NAME, numOfRows);
        }

    }

    @Override
    public void addAll(List<Person> elements) throws SQLException {

    }
}


