package com.salondethe.resources;

import com.salondethe.model.Category;
import com.salondethe.utils.MongoDBConnection;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;

@Path("/categories")
public class CategoryResource {

    private final MongoCollection<Category> categoryCollection;

    public CategoryResource() {
        this.categoryCollection = MongoDBConnection.getDatabase().getCollection("categories", Category.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() {
        return Response.ok(categoryCollection.find().into(new ArrayList<>())).build();
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
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(Category category) {
        category.setIdCategory(new ObjectId());
        categoryCollection.insertOne(category);
        return Response.status(Response.Status.CREATED).entity(category).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("id") String idCategory, Category updatedCategory) {
        var result = categoryCollection.replaceOne(eq("_id", new ObjectId(idCategory)), updatedCategory);
        if (result.getModifiedCount() > 0) {
            return Response.ok(updatedCategory).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Category not found").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") String idCategory) {
        var result = categoryCollection.deleteOne(eq("_id", new ObjectId(idCategory)));
        if (result.getDeletedCount() > 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Category not found").build();
        }
    }
}
