package com.salondethe.utils;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBConnection {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017"; // Adjust as needed
    private static final String DATABASE_NAME = "salondethe"; // Adjust as needed
    private static MongoDatabase database;

    static {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(pojoCodecProvider)
        );

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new com.mongodb.ConnectionString(CONNECTION_STRING))
                .codecRegistry(pojoCodecRegistry)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(DATABASE_NAME);
    }

    public static MongoDatabase getDatabase() {
        return database;
    }
}
