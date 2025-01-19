package com.salondethe.resources;

import java.util.ArrayList;
import java.util.List;

import com.salondethe.model.Table;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/tables")
public class TableResource {
    private static List<Table> tables = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Table> getTables() {
        return tables;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Table getTableById(@PathParam("id") String idTable) {
        return tables.stream()
                .filter(table -> table.getIdTable().toString().equals(idTable))
                .findFirst()
                .orElse(null);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createTable(Table table) {
        tables.add(table);
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editTable(@PathParam("id") String idTable, Table updatedTable) {
        tables.replaceAll(table -> {
            if (table.getIdTable().toString().equals(idTable)) {
                return updatedTable;
            }
            return table;
        });
    }

    @DELETE
    @Path("/remove/{id}")
    public void removeTable(@PathParam("id") String idTable) {
        tables.removeIf(table -> table.getIdTable().toString().equals(idTable));
    }
}