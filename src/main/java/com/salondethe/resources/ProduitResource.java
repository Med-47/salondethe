package com.salondethe.resources;

import com.salondethe.model.Produit;
import com.salondethe.model.Category;
import com.salondethe.utils.MongoDBConnection;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

@Path("/produits")
public class ProduitResource {

    private final MongoCollection<Produit> produitCollection;
    private final MongoCollection<Category> categoryCollection;
    private MongoDatabase database = MongoDBConnection.getDatabase();


    public ProduitResource() {
        this.produitCollection = MongoDBConnection.getDatabase().getCollection("produits", Produit.class);
        this.categoryCollection = MongoDBConnection.getDatabase().getCollection("categories", Category.class);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduit(Produit produit) {
        // Validate if the category exists
        Category category = categoryCollection.find(new Document("_id", produit.getIdCategory())).first();
        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Category not found").build();
        }

        // Insert the produit
        produitCollection.insertOne(produit);

        // Add the produit id to the category's produits list
        category.getProduits().add(produit.getIdProduit());

        // Update the category with the new produit id
        categoryCollection.replaceOne(new Document("_id", produit.getIdCategory()), category);

        return Response.status(Response.Status.CREATED).entity(produit).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduit(@PathParam("id") String idProduit, Produit updatedProduit) {
        // Check if the category exists
        if (categoryCollection.find(eq("_id", updatedProduit.getIdCategory())).first() == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Category not found").build();
        }

        var result = produitCollection.replaceOne(eq("_id", new ObjectId(idProduit)), updatedProduit);
        if (result.getModifiedCount() > 0) {
            return Response.ok(updatedProduit).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Produit not found").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduits() {
        return Response.ok(produitCollection.find().into(new ArrayList<>())).build();
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

    @DELETE
    @Path("/{id}")
    public Response deleteProduit(@PathParam("id") String idProduit) {
        var result = produitCollection.deleteOne(eq("_id", new ObjectId(idProduit)));
        if (result.getDeletedCount() > 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Produit not found").build();
        }
    }
}
