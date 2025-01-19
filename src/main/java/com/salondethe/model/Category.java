package com.salondethe.model;

import java.util.List;

import org.bson.types.ObjectId;

public class Category {
    private ObjectId idCategory;
    private String nomCategory;
    private List<ObjectId> produits; // Liste des IDs des produits

    // Constructeurs
    public Category() {}

    public Category(String nomCategory, List<ObjectId> produits) {
        this.nomCategory = nomCategory;
        this.produits = produits;
    }

    // Getters et Setters
    public ObjectId getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(ObjectId idCategory) {
        this.idCategory = idCategory;
    }

    public String getNomCategory() {
        return nomCategory;
    }

    public void setNomCategory(String nomCategory) {
        this.nomCategory = nomCategory;
    }

    public List<ObjectId> getProduits() {
        return produits;
    }

    public void setProduits(List<ObjectId> produits) {
        this.produits = produits;
    }
}