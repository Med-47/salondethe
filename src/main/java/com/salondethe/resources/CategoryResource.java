package com.salondethe.resources;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.salondethe.model.Category;
import com.salondethe.utils.MongoDBConnection;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;




import static com.mongodb.client.model.Filters.eq;

@Path("/categories")
public class CategoryResource {

    private final MongoCollection<Category> categoryCollection;

    public CategoryResource() {
        this.categoryCollection = MongoDBConnection.getDatabase().getCollection("categories", Category.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() {
        List<Category> categories = categoryCollection.find().into(new ArrayList<>());
        return Response.ok(categories).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryById(@PathParam("id") String idCategory) {
        Category category = categoryCollection.find(eq("_id", new ObjectId(idCategory))).first();
        if (category != null) {
            return Response.ok(category).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Category not found").build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(Category category) {
        category.setIdCategory(new ObjectId());
        categoryCollection.insertOne(category);
        return Response.status(Response.Status.CREATED).entity(category).build();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editCategory(@PathParam("id") String idCategory, Category updatedCategory) {
        Bson filter = eq("_id", new ObjectId(idCategory));
        Bson update = new org.bson.Document("$set", updatedCategory);
        UpdateResult result = categoryCollection.updateOne(filter, update);
        if (result.getModifiedCount() > 0) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Category not found").build();
        }
    }

    @DELETE
    @Path("/remove/{id}")
    public Response removeCategory(@PathParam("id") String idCategory) {
        Bson filter = eq("_id", new ObjectId(idCategory));
        DeleteResult result = categoryCollection.deleteOne(filter);
        if (result.getDeletedCount() > 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Category not found").build();
        }
    }
}