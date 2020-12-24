/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;

/**
 *
 * @author Marouan
 */
public class Claims {
    private  int Claims_id,claim_codepostal,phone,Claim_Iduser,Claim_Etat,Claim_Productid;
    private String claim_username,claim_lastname,claim_email,claim_country,Claim_Desc,Claim_Productref,eta;
    private  Timestamp Claim_Date;
    

    public Claims() {
    }

    public Claims(int Claims_id, int claim_codepostal, int phone, int Claim_Iduser, int Claim_Etat, int Claim_Productid, String claim_username, String claim_lastname, String claim_email, String claim_country, String Claim_Desc, String Claim_Productref, Timestamp Claim_Date) {
        this.Claims_id = Claims_id;
        this.claim_codepostal = claim_codepostal;
        this.phone = phone;
        this.Claim_Iduser = Claim_Iduser;
        this.Claim_Etat = Claim_Etat;
        this.Claim_Productid = Claim_Productid;
        this.claim_username = claim_username;
        this.claim_lastname = claim_lastname;
        this.claim_email = claim_email;
        this.claim_country = claim_country;
        this.Claim_Desc = Claim_Desc;
        this.Claim_Productref = Claim_Productref;
        this.Claim_Date = Claim_Date;
    }

    public Claims(int Claims_id,  String Claim_Productref,String Claim_Desc,Timestamp Claim_Date,String eta) {
        this.Claims_id = Claims_id;
        this.Claim_Productref = Claim_Productref;
        this.Claim_Desc = Claim_Desc;
        
        this.Claim_Date = Claim_Date;
        this.eta=eta;
       
    }

    public int getClaims_id() {
        return Claims_id;
    }

    public void setClaims_id(int Claims_id) {
        this.Claims_id = Claims_id;
    }

    public int getClaim_codepostal() {
        return claim_codepostal;
    }

    public void setClaim_codepostal(int claim_codepostal) {
        this.claim_codepostal = claim_codepostal;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getClaim_Iduser() {
        return Claim_Iduser;
    }

    public void setClaim_Iduser(int Claim_Iduser) {
        this.Claim_Iduser = Claim_Iduser;
    }

    public int getClaim_Etat() {
        return Claim_Etat;
    }

    public void setClaim_Etat(int Claim_Etat) {
        this.Claim_Etat = Claim_Etat;
    }

    public int getClaim_Productid() {
        return Claim_Productid;
    }

    public void setClaim_Productid(int Claim_Productid) {
        this.Claim_Productid = Claim_Productid;
    }

    public String getClaim_username() {
        return claim_username;
    }

    public void setClaim_username(String claim_username) {
        this.claim_username = claim_username;
    }

    public String getClaim_lastname() {
        return claim_lastname;
    }

    public void setClaim_lastname(String claim_lastname) {
        this.claim_lastname = claim_lastname;
    }

    public String getClaim_email() {
        return claim_email;
    }

    public void setClaim_email(String claim_email) {
        this.claim_email = claim_email;
    }

    public String getClaim_country() {
        return claim_country;
    }

    public void setClaim_country(String claim_country) {
        this.claim_country = claim_country;
    }

    public String getClaim_Desc() {
        return Claim_Desc;
    }

    public void setClaim_Desc(String Claim_Desc) {
        this.Claim_Desc = Claim_Desc;
    }

    public String getClaim_Productref() {
        return Claim_Productref;
    }

    public void setClaim_Productref(String Claim_Productref) {
        this.Claim_Productref = Claim_Productref;
    }

    public Timestamp getClaim_Date() {
        return Claim_Date;
    }

    public void setClaim_Date(Timestamp Claim_Date) {
        this.Claim_Date = Claim_Date;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }
    
    
    
}
