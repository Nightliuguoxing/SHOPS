package top.lgx.test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-13 16:21
 * @Description:
 */
public class MongoDB {

    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase spitdb = client.getDatabase("spitdb");
        MongoCollection<Document> spit = spitdb.getCollection("spit");

        Map<String, Object> map = new HashMap();
        map.put("content", "我是一个好人");
        map.put("userId", "9999");
        map.put("visits", 1000);
        map.put("time", new Date());
        Document document = new Document(map);
        spit.insertOne(document);
        client.close();
    }
}
