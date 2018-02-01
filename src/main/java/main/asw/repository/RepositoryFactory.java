package main.asw.repository;

/**
 * @author nokutu
 * @since 19/02/2017.
 */
public class RepositoryFactory {

    public static DBUpdate getDBUpdate() {
        return new DBUpdateImpl();
    }
}
