package com.salondethe.resources;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.salondethe.model.Commande;
import com.salondethe.utils.MongoDBConnection;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static com.mongodb.client.model.Filters.eq;

@Path("/commandes")
public class CommandeResource {

    private final MongoCollection<Commande> commandeCollection;

    public CommandeResource() {
        this.commandeCollection = MongoDBConnection.getDatabase().getCollection("commandes", Commande.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommandes() {
        List<Commande> commandes = commandeCollection.find().into(new ArrayList<>());
        return Response.ok(commandes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommandeById(@PathParam("id") String idCommande) {
        Commande commande = commandeCollection.find(eq("_id", new ObjectId(idCommande))).first();
        if (commande != null) {
            return Response.ok(commande).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Commande not found").build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCommande(Commande commande) {
        commande.setIdCommande(new ObjectId());
        commandeCollection.insertOne(commande);
        return Response.status(Response.Status.CREATED).entity(commande).build();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editCommande(@PathParam("id") String idCommande, Commande updatedCommande) {
        Bson filter = eq("_id", new ObjectId(idCommande));
        Bson update = new org.bson.Document("$set", updatedCommande);
        UpdateResult result = commandeCollection.updateOne(filter, update);
        if (result.getModifiedCount() > 0) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Commande not found").build();
        }
    }

    @DELETE
    @Path("/remove/{id}")
    public Response removeCommande(@PathParam("id") String idCommande) {
        Bson filter = eq("_id", new ObjectId(idCommande));
        DeleteResult result = commandeCollection.deleteOne(filter);
        if (result.getDeletedCount() > 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Commande not found").build();
        }
    }
}