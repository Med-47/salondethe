package com.salondethe.resources;

import com.salondethe.model.Commande;
import com.salondethe.model.Table;
import com.salondethe.utils.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Path("/commandes")
public class CommandeResource {
    private static MongoCollection<Document> tableCollection = MongoDBConnection.getDatabase().getCollection("tables");
    private static MongoCollection<Document> commandeCollection2 = MongoDBConnection.getDatabase().getCollection("commandes");


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
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCommande(Commande commande) {
        // Check if the idTable exists in the tables collection
        ObjectId idTable = commande.getIdTable();
        Document tableDoc = tableCollection.find(Filters.eq("_id", idTable)).first();

        if (tableDoc == null) {
            // Return a 404 response if the table doesn't exist
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Table with id " + idTable.toString() + " not found.")
                    .build();
        }

        // If table exists, create the commande
        Document newCommandeDoc = new Document("idTable", idTable)
                .append("produits", commande.getProduits())
                .append("prixTotal", commande.getPrixTotal())
                .append("etatPayement", commande.isEtatPayement())
                .append("dateCommande", commande.getDateCommande());

        // Insert the new commande into the commandes collection
        commandeCollection2.insertOne(newCommandeDoc);

        // Get the inserted commande's id
        ObjectId idCommande = newCommandeDoc.getObjectId("_id");

        // Update the corresponding table with the idCommande
        tableCollection.updateOne(
                Filters.eq("_id", idTable), // Find the table by idTable
                Updates.addToSet("commandes", idCommande) // Add the new idCommande to the "commandes" field
        );

        // Return the created commande
        return Response.status(Response.Status.CREATED)
                .entity(newCommandeDoc)
                .build();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editCommande(@PathParam("id") String idCommande, Commande updatedCommande) {
        updatedCommande.setIdCommande(new ObjectId(idCommande));
        UpdateResult result = commandeCollection.replaceOne(eq("_id", new ObjectId(idCommande)), updatedCommande);
        if (result.getModifiedCount() > 0) {
            return Response.ok(updatedCommande).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Commande not found").build();
        }
    }

    @DELETE
    @Path("/remove/{id}")
    public Response removeCommande(@PathParam("id") String idCommande) {
        DeleteResult result = commandeCollection.deleteOne(eq("_id", new ObjectId(idCommande)));
        if (result.getDeletedCount() > 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Commande not found").build();
        }
    }
}
