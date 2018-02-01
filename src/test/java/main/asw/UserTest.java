package main.asw;

import main.asw.user.User;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nicolas on 18/02/17 for citizensLoader0.
 */
public class UserTest {

    private Date parseDate(String birthDateString) throws ParseException {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date;
        df.setLenient(false);
        date = df.parse(birthDateString);
        return date;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDateAfterToday() throws ParseException {
        new User("P01", "P01", "p01@p01.com", parseDate("12/12/3823"), "P01", "P01", "71735454H");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadEmail() throws ParseException {
        new User("P01", "P01", "badEmailWhitoutDot", parseDate("12/12/1985"), "P01", "P01", "71735454H");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadNif() throws ParseException {
        new User("P01", "P01", "p01@email.com", parseDate("12/12/1985"), "P01", "P01", "71735454J");
    }
}
