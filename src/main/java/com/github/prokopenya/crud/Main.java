package com.github.prokopenya.crud;

import com.github.prokopenya.dao.PersonsDAO;
import com.github.prokopenya.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class Main {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws SQLException {
        DbConnection dbConnection = DbConnection.getInstance();
        Connection connection = dbConnection.getConnection();

        PersonsDAO personDAO = new PersonsDAO(connection);
        personDAO.createTable();

        Person vasya = new Person(1,"Vasya", "89351");
        Person petya = new Person( 2,"Petya", "89352");

        personDAO.add(vasya);
        personDAO.add(petya);

        Optional<Person> personPetya = personDAO.get(2);

        personDAO.remove(1);
    }

}
