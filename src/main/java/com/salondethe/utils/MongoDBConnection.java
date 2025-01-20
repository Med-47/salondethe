package com.salondethe.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017"; // Adjust as needed
    private static final String DATABASE_NAME = "salondethe"; // Adjust as needed
    private static MongoDatabase database;

    static {
        MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
        database = mongoClient.getDatabase(DATABASE_NAME);
    }

    public static MongoDatabase getDatabase() {
        return database;
    }
}
