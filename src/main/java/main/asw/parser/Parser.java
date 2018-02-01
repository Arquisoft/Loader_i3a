package main.asw.parser;

/**
 * @author nokutu
 * @since 14/02/2017
 */
public interface Parser {

    /**
     * Reads and parses the data from the excel file.
     */
    void readList();

    /**
     * Inserts the data into the database.
     */
    void insert();

}
