package com.github.prokopenya.dao;

final class Queries {
    private Queries() {
        throw new AssertionError("class can't have instances!");
    }

    static final class StudentTable {
        private StudentTable() {
            throw new AssertionError("class can't have instances!");
        }

        static final String TABLE_NAME = "PERSONS";

        static final String CREATE = String.format(
                "CREATE TABLE IF NOT EXISTS %s(ID LONG PRIMARY KEY, NAME TEXT, PHONE TEXT);", TABLE_NAME
        );

        static final String ADD = String.format("INSERT INTO %s(ID, NAME, PHONE) VALUES(?, ?, ?);", TABLE_NAME);

        static final String REMOVE_BY_ID = String.format("DELETE FROM %s WHERE ID=?;", TABLE_NAME);

        static final String GET_BY_ID = String.format("SELECT * FROM %s WHERE ID=?;", TABLE_NAME);
    }
}
