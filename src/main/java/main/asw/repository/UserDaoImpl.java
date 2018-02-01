package main.asw.repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import main.asw.user.User;
import org.bson.Document;
import org.slf4j.LoggerFactory;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by MIGUEL on 16/02/2017.
 */
class UserDaoImpl implements UserDao {

    private final static org.slf4j.Logger log = LoggerFactory.getLogger(UserDao.class);

    private MongoClient mongoClient = new MongoClient("localhost", 27017);
    private MongoDatabase db = mongoClient.getDatabase("aswdb");
    private MongoCollection<Document> coll = db.getCollection("users");


    public UserDaoImpl(){
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("aswdb");
        coll = db.getCollection("users");
    }

    /**
     * Saves a given user in the database if there ins't already one with the same userId
     *
     * @param u User to be saved
     *
     */
    @Override
    public boolean saveUser(User u) {
        if (coll.find(eq("userId", u.getNif())).first() == null) {
            Document doc = new Document("firstName", u.getFirstName())
                    .append("lastName", u.getLastName())
                    .append("email", u.getEmail())
                    .append("address", u.getAddress())
                    .append("nationality", u.getNationality())
                    .append("userId", u.getNif())
                    .append("dateOfBirth", u.getDateOfBirth())
                    .append("password", u.getPassword());
            coll.insertOne(doc);
            log.info("User with userId = " + u.getNif() + " added to the database");
            return true;
        }
        else{
            log.warn("A user with userId = " + u.getNif() + " is already in the database");
            return false;
        }
    }

    @Override
    public void setMongoHost(String host) {
        mongoClient = new MongoClient(host, 27017);
        db = mongoClient.getDatabase("aswdb");
        coll = db.getCollection("users");
    }

}
