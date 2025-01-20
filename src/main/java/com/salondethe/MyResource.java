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

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    public static void main(String[] args) throws IOException {
        // Create a ResourceConfig and register all resource classes
        ResourceConfig config = new ResourceConfig()
                .register(CategoryResource.class)
                .register(CommandeResource.class)
                .register(ProduitResource.class)
                .register(ServeurResource.class)
                .register(TableResource.class);

        // Start the Grizzly HTTP server
        URI baseUri = URI.create("http://localhost:8081/");
        GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

        System.out.println("Server started at " + baseUri);

        // Block the main thread to keep the server running
        System.in.read(); // Wait for Enter key to stop the server
    }
}
