package main.asw.parser;

import main.asw.user.User;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by nicolas on 15/02/17.
 */
public class ParserTest {

    private final static String BASE_PATH = "src/test/resources/";

    private final static String TEST_OK_FILE_NAME = "pruebaUsuarios.xls";
    private final static String TEST_BAD_DATE_AFTER_TODAY = "badDateAfterToday.xls";
    private final static String TEST_BAD_DATE_FORMAT = "badDateFormat.xls";
    private final static String TEST_NO_FOUND_FILE = "noExiste";
    private static final String TEST_LESS_LINES = "lessLines.xls";
    private static final String TEST_MORE_LINES = "moreLines.xls";


    private ParserImpl parser;

//    private static final String MONGO_HOST = "localhost";
//    private static final int MONGO_PORT = 27017;
//
//    private MongodExecutable mongodExe;
//    private MongodProcess mongod;
//    private MongoClient mongoClient;
//
//    /**
//     * Deploys an in-memory database for simple testing
//     *
//     * @throws Exception if any problem occurs trying to launch the DB
//     */
//    private void setupDb() throws Exception {
//        MongodStarter runtime = MongodStarter.getDefaultInstance();
//        mongodExe = runtime.prepare(new MongodConfig(Version.V2_0_5, MONGO_PORT, Network.localhostIsIPv6()));
//        mongod = mongodExe.start();
//        mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
//    }
//
//    @After
//    public void tearDownDb() throws Exception {
//        if (mongod != null) {
//            mongod.stop();
//            mongodExe.stop();
//        }
//    }


    @Test
    public void testAllOKFile() throws IOException, ParseException {
        try {
            parser = ParserFactory.getParser(BASE_PATH + TEST_OK_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String baseName = "Prueba";
        String baseSurname = "Apellido";
        String baseEmail = "prueba";
        String baseStreet = "c/Prueba n0 1a";
        String baseCountry = "España";
        parser.readList();
        assertEquals(20, parser.getUsers().size());
        for (int i = 0; i < parser.getUsers().size(); i++) {
            String index = (i + 1 < 10) ? "0" + (i + 1) : (i + 1) + "";
            User user = parser.getUsers().get(i);
            assertEquals(baseName + index, user.getFirstName());
            assertEquals(baseSurname + index, user.getLastName());
            assertEquals(baseEmail + index + "@prueba.es", user.getEmail());
            assertEquals(baseStreet, user.getAddress());
            assertEquals(baseCountry, user.getNationality());
        }
    }

    @Test(expected = IOException.class)
    public void testNoFoundFile() throws IOException {
        parser = ParserFactory.getParser(BASE_PATH + TEST_NO_FOUND_FILE);
    }

    @Test
    public void testMoreLines() throws IOException, ParseException {
        parser = ParserFactory.getParser(BASE_PATH + TEST_MORE_LINES);
        parser.readList();
        assertEquals(0, parser.getUsers().size());
    }

    @Test
    public void testLessLines() throws IOException, ParseException {
        parser = ParserFactory.getParser(BASE_PATH + TEST_LESS_LINES);
        parser.readList();
        assertEquals(0, parser.getUsers().size());
    }

/* Not needed. It's being tested on PersistenceTest class */
//    @Test
//    public void testArchieveInsertion() throws Exception {
//        setupDb();
//        parser = ParserFactory.getParser(BASE_PATH + TEST_OK_FILE_NAME);
//        parser.readList();
//        parser.insert();
//        //We expect 1 because in that file all the rows have the same userId
//        assertEquals(1, mongoClient.getDatabase("aswdb").getCollection("users").count());
//        tearDownDb();
//    }

}
