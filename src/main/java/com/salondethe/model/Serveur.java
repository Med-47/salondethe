package com.salondethe.model;
import org.bson.types.ObjectId;

public class Serveur {
    private ObjectId idServeur;
    private String nom;
    private String numTelephone;
    private int nombreCommande;
    private double montantTotal;

    // Constructeurs
    public Serveur() {}

    public Serveur(String nom, String numTelephone, int nombreCommande, double montantTotal) {
        this.nom = nom;
        this.numTelephone = numTelephone;
        this.nombreCommande = nombreCommande;
        this.montantTotal = montantTotal;
    }

    // Getters et Setters
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