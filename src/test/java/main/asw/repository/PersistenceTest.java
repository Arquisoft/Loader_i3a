package main.asw.repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import main.asw.user.User;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by MIGUEL on 21/02/2017.
 */
public class PersistenceTest {

    private DBUpdate dbUpdate;
    private static MongoClient mongoClient;
    private static MongoDatabase db;
    private static MongoCollection<Document> coll;
    private List<User> users;
    private long oldCount;
    private long newCount;

    @Before
    public void setUp(){
        dbUpdate = RepositoryFactory.getDBUpdate();
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("aswdb");
        coll = db.getCollection("users");
        users = new ArrayList<>();
    }

    @After
    public void tearDown(){
        for (int i = 0; i < users.size(); i++){
            Document query = new Document("userId", users.get(i).getNif());
            coll.deleteMany(query);
        }
    }

    /**
     * Tests the insertion of users in the DB by means of our persistence layer.
     * Checks that we cannot add two users with the same userId.
     */
    @Test
    public void testInsert(){
        insertUsers();
        //Only the non-repeated users should be in the database. We tried to insert one duplicated
        assertTrue(newCount == oldCount+users.size()-1);

        Document query = new Document("userId", users.get(3).getNif());
        assertEquals((coll.count(query)), 1);
    }

    /**
     * Adds users to the database by means of our persistence layer
     */
    private void insertUsers(){
        oldCount = coll.count();

        users.add(new User("Miguel",
                "García",
                "mg@email.com",
                new Date(),
                "c/ circus",
                "España",
                "66863955B")
        );

        users.add(new User("Jorge",
                "López",
                "jl@email.com",
                new Date(),
                "c/ road",
                "España",
                "37165071S")
        );

        users.add(new User("Nicolás",
                "Pascual",
                "np@email.com",
                new Date(),
                "c/ venue",
                "España",
                "94875755L")
        );

        users.add(new User("Pablo",
                "García",
                "pg@email.com",
                new Date(),
                "c/ street",
                "España",
                "46402573G")
        );

        //Same userId
        users.add(new User("Pablo",
                "García",
                "pg@email.com",
                new Date(),
                "c/ street",
                "España",
                "46402573G")
        );

        dbUpdate.insert(users);

        newCount = coll.count();
    }
}
