package com.salondethe.resources;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.salondethe.model.Table;
import com.salondethe.utils.MongoDBConnection;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Path("/tables")
public class TableResource {

    private final MongoCollection<Table> tableCollection;

    public TableResource() {
        this.tableCollection = MongoDBConnection.getDatabase().getCollection("tables", Table.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTables() {
        List<Table> tables = tableCollection.find().into(new ArrayList<>());
        return Response.ok(tables).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTableById(@PathParam("id") String idTable) {
        Table table = tableCollection.find(eq("_id", new ObjectId(idTable))).first();
        if (table != null) {
            return Response.ok(table).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Table not found").build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTable(Table table) {
        table.setIdTable(new ObjectId());
        tableCollection.insertOne(table);
        return Response.status(Response.Status.CREATED).entity(table).build();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editTable(@PathParam("id") String idTable, Table updatedTable) {
        Bson filter = eq("_id", new ObjectId(idTable));
        Bson update = new org.bson.Document("$set", updatedTable);
        UpdateResult result = tableCollection.updateOne(filter, update);
        if (result.getModifiedCount() > 0) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Table not found").build();
        }
    }

    @DELETE
    @Path("/remove/{id}")
    public Response removeTable(@PathParam("id") String idTable) {
        Bson filter = eq("_id", new ObjectId(idTable));
        DeleteResult result = tableCollection.deleteOne(filter);
        if (result.getDeletedCount() > 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Table not found").build();
        }
    }
}