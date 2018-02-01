package main.asw.repository;

import main.asw.user.User;

/**
 * Created by MIGUEL on 16/02/2017.
 */
public interface UserDao {

    /**
     * Saves a given user in the database
     *
     * @param u User to be saved
     * @return  true if the user could be saved
     *          false otherwise (if the user already exists in the DB)
     */
    boolean saveUser(User u);

    void setMongoHost(String arg);
}
