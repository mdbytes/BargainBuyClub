package com.bdowebtech.bargainbuyclub.model.Data;

import com.mongodb.Mongo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Iterator;

public class MongoBBC extends MongoServer {

    MongoDatabase database;

    public MongoBBC(String dbName) {
        this.database = super.mongoClient.getDatabase(dbName);
    }

    public MongoCollection<Document> getCollection(String collectionName) {

        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection(collectionName);
        System.out.println("Collection users selected successfully");

        // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();

        // Getting the iterator
        Iterator it = iterDoc.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        return collection;

    }



    public static void main(String[] args) {

        MongoBBC bbc = new MongoBBC("BBC");

        bbc.getCollection("users");

    }
}
