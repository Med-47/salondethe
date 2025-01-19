package com.salondethe.resources;

import java.util.ArrayList;
import java.util.List;

import com.salondethe.model.Serveur;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/serveurs")
public class ServeurResource {
    private static List<Serveur> serveurs = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Serveur> getServeurs() {
        return serveurs;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Serveur getServeurById(@PathParam("id") String idServeur) {
        return serveurs.stream()
                .filter(serveur -> serveur.getIdServeur().toString().equals(idServeur))
                .findFirst()
                .orElse(null);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createServeur(Serveur serveur) {
        serveurs.add(serveur);
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editServeur(@PathParam("id") String idServeur, Serveur updatedServeur) {
        serveurs.replaceAll(serveur -> {
            if (serveur.getIdServeur().toString().equals(idServeur)) {
                return updatedServeur;
            }
            return serveur;
        });
    }

    @DELETE
    @Path("/remove/{id}")
    public void removeServeur(@PathParam("id") String idServeur) {
        serveurs.removeIf(serveur -> serveur.getIdServeur().toString().equals(idServeur));
    }
}