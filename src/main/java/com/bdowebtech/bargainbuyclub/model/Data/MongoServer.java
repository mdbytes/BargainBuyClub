package com.bdowebtech.bargainbuyclub.model.Data;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.util.List;

public class MongoServer {

    MongoClient mongoClient;

    public MongoServer() {
        this.mongoClient = new MongoClient("localhost", 27017);
    }


}
