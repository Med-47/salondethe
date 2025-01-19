package com.salondethe.resources;

import java.util.ArrayList;
import java.util.List;

import com.salondethe.model.Category;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/categories")
public class CategoryResource {
    private static List<Category> categories = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getCategories() {
        return categories;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategoryById(@PathParam("id") String idCategory) {
        return categories.stream()
                .filter(category -> category.getIdCategory().toString().equals(idCategory))
                .findFirst()
                .orElse(null);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCategory(Category category) {
        categories.add(category);
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editCategory(@PathParam("id") String idCategory, Category updatedCategory) {
        categories.replaceAll(category -> {
            if (category.getIdCategory().toString().equals(idCategory)) {
                return updatedCategory;
            }
            return category;
        });
    }

    @DELETE
    @Path("/remove/{id}")
    public void removeCategory(@PathParam("id") String idCategory) {
        categories.removeIf(category -> category.getIdCategory().toString().equals(idCategory));
    }
}