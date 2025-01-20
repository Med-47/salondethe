package com.salondethe.model;

import org.bson.types.ObjectId;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Serveur {
    @BsonId
    private ObjectId idServeur;

    @BsonProperty("nom")
    private String nom;

    @BsonProperty("numTelephone")
    private String numTelephone;

    @BsonProperty("nombreCommande")
    private int nombreCommande;

    @BsonProperty("montantTotal")
    private double montantTotal;

    // Constructors
    public Serveur() {}

    public Serveur(String nom, String numTelephone, int nombreCommande, double montantTotal) {
        this.nom = nom;
        this.numTelephone = numTelephone;
        this.nombreCommande = nombreCommande;
        this.montantTotal = montantTotal;
    }

    // Getters and Setters
    public ObjectId getIdServeur() {
        return idServeur;
    }

    public void setIdServeur(ObjectId idServeur) {
        this.idServeur = idServeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public int getNombreCommande() {
        return nombreCommande;
    }

    public void setNombreCommande(int nombreCommande) {
        this.nombreCommande = nombreCommande;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }
}
