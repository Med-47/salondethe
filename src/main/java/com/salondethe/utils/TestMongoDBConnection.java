package com.salondethe.utils;

public class TestMongoDBConnection {
    public static void main(String[] args) {
        try {
            if (MongoDBConnection.getDatabase() != null) {
                System.out.println("Connected to MongoDB successfully!");
            } else {
                System.out.println("Failed to connect to MongoDB.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
