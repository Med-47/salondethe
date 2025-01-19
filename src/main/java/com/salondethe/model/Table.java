package com.salondethe.model;

import org.bson.types.ObjectId;

public class Table {
    private ObjectId idTable;
    private ObjectId idCommande;
    private String nomTable;

    // Constructeurs
    public Table() {}

    public Table(ObjectId idCommande, String nomTable) {
        this.idCommande = idCommande;
        this.nomTable = nomTable;
    }

    // Getters et Setters
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