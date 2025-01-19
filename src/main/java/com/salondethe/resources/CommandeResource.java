package com.salondethe.resources;

import java.util.ArrayList;
import java.util.List;

import com.salondethe.model.Commande;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/commandes")
public class CommandeResource {
    private static List<Commande> commandes = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commande> getCommandes() {
        return commandes;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Commande getCommandeById(@PathParam("id") String idCommande) {
        return commandes.stream()
                .filter(commande -> commande.getIdCommande().toString().equals(idCommande))
                .findFirst()
                .orElse(null);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCommande(Commande commande) {
        commandes.add(commande);
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editCommande(@PathParam("id") String idCommande, Commande updatedCommande) {
        commandes.replaceAll(commande -> {
            if (commande.getIdCommande().toString().equals(idCommande)) {
                return updatedCommande;
            }
            return commande;
        });
    }

    @DELETE
    @Path("/remove/{id}")
    public void removeCommande(@PathParam("id") String idCommande) {
        commandes.removeIf(commande -> commande.getIdCommande().toString().equals(idCommande));
    }
}