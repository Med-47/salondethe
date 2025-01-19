package com.salondethe.resources;

import java.util.ArrayList;
import java.util.List;

import com.salondethe.model.Produit;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/produits")
public class ProduitResource {
    private static List<Produit> produits = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produit> getProduits() {
        return produits;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produit getProduitById(@PathParam("id") String idProduit) {
        return produits.stream()
                .filter(produit -> produit.getIdProduit().toString().equals(idProduit))
                .findFirst()
                .orElse(null);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProduit(Produit produit) {
        produits.add(produit);
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editProduit(@PathParam("id") String idProduit, Produit updatedProduit) {
        produits.replaceAll(produit -> {
            if (produit.getIdProduit().toString().equals(idProduit)) {
                return updatedProduit;
            }
            return produit;
        });
    }

    @DELETE
    @Path("/remove/{id}")
    public void removeProduit(@PathParam("id") String idProduit) {
        produits.removeIf(produit -> produit.getIdProduit().toString().equals(idProduit));
    }
}