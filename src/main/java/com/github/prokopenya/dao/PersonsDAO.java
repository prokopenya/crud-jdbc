package com.github.prokopenya.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonsDAO {
    public void createTablePersons(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        // create a new table
        stmt.execute("CREATE TABLE IF NOT EXISTS PERSONS(ID LongPRIMARY KEY, NAME TEXT, PHONE TEXT);");
    }
}
