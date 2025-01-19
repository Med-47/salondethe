package com.salondethe;

import com.salondethe.resources.CategoryResource;
import com.salondethe.resources.CommandeResource;
import com.salondethe.resources.ProduitResource;
import com.salondethe.resources.ServeurResource;
import com.salondethe.resources.TableResource;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class MyResource {

    public static void main(String[] args) {
        // Create a ResourceConfig and register all resource classes
        ResourceConfig config = new ResourceConfig()
                .register(CategoryResource.class)
                .register(CommandeResource.class)
                .register(ProduitResource.class)
                .register(ServeurResource.class)
                .register(TableResource.class);

        // Start the Grizzly HTTP server
        URI baseUri = URI.create("http://localhost:8080/");
        GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

        System.out.println("Server started at " + baseUri);
    }
}