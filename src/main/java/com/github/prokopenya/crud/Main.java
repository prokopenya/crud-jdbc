package com.github.prokopenya.crud;

import com.github.prokopenya.dao.PersonsDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DbConnection dbConnection = DbConnection.getInstance();
        Connection connection = dbConnection.getConnection();

        PersonsDAO person = new PersonsDAO();
        person.createTablePersons(connection);
    }
}
