package com.salondethe.model;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class Commande {
    private ObjectId idCommande;
    private ObjectId idTable;
    private List<ObjectId> produits; // Liste des IDs des produits
    private double prixTotal;
    private boolean etatPayement; // true = validé, false = non validé
    private Date dateCommande;

    // Constructeurs
    public Commande() {}

    public Commande(ObjectId idTable, List<ObjectId> produits, double prixTotal, boolean etatPayement, Date dateCommande) {
        this.idTable = idTable;
        this.produits = produits;
        this.prixTotal = prixTotal;
        this.etatPayement = etatPayement;
        this.dateCommande = dateCommande;
    }

    // Getters et Setters
    public ObjectId getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(ObjectId idCommande) {
        this.idCommande = idCommande;
    }

    public ObjectId getIdTable() {
        return idTable;
    }

    public void setIdTable(ObjectId idTable) {
        this.idTable = idTable;
    }

    public List<ObjectId> getProduits() {
        return produits;
    }

    public void setProduits(List<ObjectId> produits) {
        this.produits = produits;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public boolean isEtatPayement() {
        return etatPayement;
    }

    public void setEtatPayement(boolean etatPayement) {
        this.etatPayement = etatPayement;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }
}