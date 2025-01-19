package com.salondethe.resources;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.salondethe.model.Produit;
import com.salondethe.utils.MongoDBConnection;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Path("/produits")
public class ProduitResource {

    private final MongoCollection<Produit> produitCollection;

    public ProduitResource() {
        this.produitCollection = MongoDBConnection.getDatabase().getCollection("produits", Produit.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduits() {
        List<Produit> produits = produitCollection.find().into(new ArrayList<>());
        return Response.ok(produits).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduitById(@PathParam("id") String idProduit) {
        Produit produit = produitCollection.find(eq("_id", new ObjectId(idProduit))).first();
        if (produit != null) {
            return Response.ok(produit).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Produit not found").build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduit(Produit produit) {
        produit.setIdProduit(new ObjectId());
        produitCollection.insertOne(produit);
        return Response.status(Response.Status.CREATED).entity(produit).build();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editProduit(@PathParam("id") String idProduit, Produit updatedProduit) {
        Bson filter = eq("_id", new ObjectId(idProduit));
        Bson update = new org.bson.Document("$set", updatedProduit);
        UpdateResult result = produitCollection.updateOne(filter, update);
        if (result.getModifiedCount() > 0) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Produit not found").build();
        }
    }

    @DELETE
    @Path("/remove/{id}")
    public Response removeProduit(@PathParam("id") String idProduit) {
        Bson filter = eq("_id", new ObjectId(idProduit));
        DeleteResult result = produitCollection.deleteOne(filter);
        if (result.getDeletedCount() > 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Produit not found").build();
        }
    }
}