package com.salondethe.model;

import org.bson.types.ObjectId;

public class Produit {
   private ObjectId idProduit;
   private ObjectId idCategory;
   private String nomProduit;
   private String descriptionProduit;
   private double prixProduit;

   public Produit() {
   }

   public Produit(ObjectId idCategory, String nomProduit, String descriptionProduit, double prixProduit) {
      this.idCategory = idCategory;
      this.nomProduit = nomProduit;
      this.descriptionProduit = descriptionProduit;
      this.prixProduit = prixProduit;
   }

   public ObjectId getIdProduit() {
      return this.idProduit;
   }

   public void setIdProduit(ObjectId idProduit) {
      this.idProduit = idProduit;
   }

   public ObjectId getIdCategory() {
      return this.idCategory;
   }

   public void setIdCategory(ObjectId idCategory) {
      this.idCategory = idCategory;
   }

   public String getNomProduit() {
      return this.nomProduit;
   }

   public void setNomProduit(String nomProduit) {
      this.nomProduit = nomProduit;
   }

   public String getDescriptionProduit() {
      return this.descriptionProduit;
   }

   public void setDescriptionProduit(String descriptionProduit) {
      this.descriptionProduit = descriptionProduit;
   }

   public double getPrixProduit() {
      return this.prixProduit;
   }

   public void setPrixProduit(double prixProduit) {
      this.prixProduit = prixProduit;
   }
}