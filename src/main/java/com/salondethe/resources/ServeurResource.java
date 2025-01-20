package com.salondethe.resources;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.salondethe.model.Serveur;
import com.salondethe.utils.MongoDBConnection;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

@Path("/serveurs")
public class ServeurResource {
    private final MongoCollection<Serveur> serveurCollection;

    public ServeurResource() {
        this.serveurCollection = MongoDBConnection.getDatabase().getCollection("serveurs", Serveur.class);
    }

    // CREATE - Add a new serveur
    @POST
@Path("/create")
@Consumes(MediaType.APPLICATION_JSON)
public Response createServeur(Serveur serveur) {
    // Generate a new ObjectId before saving
    serveur.setIdServeur(new ObjectId());  
    
    try {
        // Insert the new serveur into the collection
        serveurCollection.insertOne(serveur);
        
        // Return the created serveur as a response with a 201 status
        return Response.status(Response.Status.CREATED)
                       .entity(serveur)  // Return the entity (created serveur)
                       .build();
    } catch (Exception e) {
        // Log and handle the exception
        e.printStackTrace(); // log exception
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity("An error occurred while creating the serveur")
                       .build();
    }
}


    // READ - Get all serveurs
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getServeurs() {
        return Response.ok(serveurCollection.find().into(new ArrayList<Serveur>())).build();
    }

    // READ - Get a specific serveur by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getServeurById(@PathParam("id") String idServeur) {
        Serveur serveur = serveurCollection.find(eq("_id", new ObjectId(idServeur))).first();
        if (serveur != null) {
            return Response.ok(serveur).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Serveur not found").build();
        }
    }

    // UPDATE - Update an existing serveur
    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editServeur(@PathParam("id") String idServeur, Serveur updatedServeur) {
        Bson filter = eq("_id", new ObjectId(idServeur));
        Bson update = new org.bson.Document("$set", updatedServeur);
        UpdateResult result = serveurCollection.updateOne(filter, update);

        if (result.getModifiedCount() > 0) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Serveur not found").build();
        }
    }

    // DELETE - Remove a serveur by id
    @DELETE
    @Path("/remove/{id}")
    public Response removeServeur(@PathParam("id") String idServeur) {
        Bson filter = eq("_id", new ObjectId(idServeur));
        DeleteResult result = serveurCollection.deleteOne(filter);
        if (result.getDeletedCount() > 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Serveur not found").build();
        }
    }
}
