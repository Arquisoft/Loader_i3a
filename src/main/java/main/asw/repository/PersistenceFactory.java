package main.asw.repository;

/**
 * Created by MIGUEL on 16/02/2017.
 */
public class PersistenceFactory {

    private static UserDao userDao = new UserDaoImpl();

    public static UserDao getUserDAO() {
        return userDao;
    }

}
