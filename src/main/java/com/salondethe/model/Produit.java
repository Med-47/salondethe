package com.salondethe.model;

import org.bson.types.ObjectId;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Produit {

    @BsonId
    private ObjectId idProduit;

    @BsonProperty("idCategory")
    private ObjectId idCategory;

    @BsonProperty("nomProduit")
    private String nomProduit;

    @BsonProperty("descriptionProduit")
    private String descriptionProduit;

    @BsonProperty("prixProduit")
    private double prixProduit;

    // Constructors
    public Produit() {}

    public Produit(ObjectId idCategory, String nomProduit, String descriptionProduit, double prixProduit) {
        this.idCategory = idCategory;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
    }

    // Getters and Setters
    public ObjectId getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(ObjectId idProduit) {
        this.idProduit = idProduit;
    }

    public ObjectId getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(ObjectId idCategory) {
        this.idCategory = idCategory;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }
}
