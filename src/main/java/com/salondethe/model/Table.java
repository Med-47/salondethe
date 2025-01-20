package com.salondethe.model;

import org.bson.types.ObjectId;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Table {

    @BsonId
    private ObjectId idTable;

    @BsonProperty("idCommande")
    private ObjectId idCommande;

    @BsonProperty("nomTable")
    private String nomTable;

    // Constructors
    public Table() {}

    public Table(ObjectId idCommande, String nomTable) {
        this.idCommande = idCommande;
        this.nomTable = nomTable;
    }

    // Getters and Setters
    public ObjectId getIdTable() {
        return idTable;
    }

    public void setIdTable(ObjectId idTable) {
        this.idTable = idTable;
    }

    public ObjectId getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(ObjectId idCommande) {
        this.idCommande = idCommande;
    }

    public String getNomTable() {
        return nomTable;
    }

    public void setNomTable(String nomTable) {
        this.nomTable = nomTable;
    }
}
