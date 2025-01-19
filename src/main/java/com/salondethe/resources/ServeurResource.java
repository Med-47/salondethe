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

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Path("/serveurs")
public class ServeurResource {

    private final MongoCollection<Serveur> serveurCollection;

    public ServeurResource() {
        this.serveurCollection = MongoDBConnection.getDatabase().getCollection("serveurs", Serveur.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getServeurs() {
        List<Serveur> serveurs = serveurCollection.find().into(new ArrayList<>());
        return Response.ok(serveurs).build();
    }

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

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createServeur(Serveur serveur) {
        serveur.setIdServeur(new ObjectId());
        serveurCollection.insertOne(serveur);
        return Response.status(Response.Status.CREATED).entity(serveur).build();
    }

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