package com.salondethe;

import com.salondethe.resources.CategoryResource;
import com.salondethe.resources.CommandeResource;
import com.salondethe.resources.ProduitResource;
import com.salondethe.resources.ServeurResource;
import com.salondethe.resources.TableResource;

import jakarta.ws.rs.Path;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;

public class MyResource {
    public static void main(String[] args) {
        try {
            ResourceConfig config = new ResourceConfig()
                    .register(CategoryResource.class)
                    .register(CommandeResource.class)
                    .register(ProduitResource.class)
                    .register(ServeurResource.class)
                    .register(TableResource.class);

            URI baseUri = URI.create("http://localhost:8081/");
            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

            System.out.println("Server started at " + baseUri);
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace(); // Logs the stack trace for debugging
        }
    }
}
