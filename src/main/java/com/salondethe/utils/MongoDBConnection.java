package com.salondethe.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    // Méthode pour obtenir la base de données
    public static MongoDatabase getDatabase() {
        if (database == null) {
            // Connexion à MongoDB
            String connectionString = "mongodb://localhost:27017"; // URL de connexion
            mongoClient = MongoClients.create(connectionString);

            // Sélection de la base de données
            database = mongoClient.getDatabase("salondethe"); // Nom de la base de données
        }
        return database;
    }

    // Méthode pour fermer la connexion
    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}