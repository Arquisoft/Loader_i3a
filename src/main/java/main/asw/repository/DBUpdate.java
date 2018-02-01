package main.asw.repository;

import main.asw.user.User;

import java.util.List;

public interface DBUpdate {

    /**
     * Inserts each one of the given users into the database
     * @param users
     */
    void insert(List<User> users);

    /**
     * Generates the reports for the users
     */
    void writeReport();

}
