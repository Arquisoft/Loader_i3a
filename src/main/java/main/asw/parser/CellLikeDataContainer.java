package main.asw.parser;

import java.io.IOException;

/**
 * Created by nicolas on 10/02/17.
 */
public interface CellLikeDataContainer {

    /**
     * Return the number of Rows in the working file
     *
     * @return number of rows of the file
     */
    int getNumberOfRows();

    /**
     * Return the number of columns in the current Row
     *
     * @return number of Columns of the Row
     */
    int getNumberOfColumns();


    /**
     * Jumps to the next row and in case it has no more rows it close the archieve
     *
     * @return if there was a new line
     * @throws IOException
     */
    boolean nextRow() throws IOException;


    /**
     * Returns the cell in a String format
     *
     * @param index the index of the cell, less than the maximun index
     * @return the cell in string format
     */
    String getCell(int index);

    /**
     * Returns the index of the current row
     *
     * @return the index of the current row
     */
    int getCurrentRow();
}
