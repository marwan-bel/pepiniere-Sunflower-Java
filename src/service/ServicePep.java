/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.*;
import Entities.*;
import utils.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shmhi
 */
public class ServicePep {

Connection c= Maconnexion.getinstance().getConn();
    public ServicePep() {
        Connection c= Maconnexion.getinstance().getConn();
      
    }

    public void ajouter(GestionServices gs) {

        try {
            Statement st = c.createStatement();

            Statement st2 = c.createStatement();
            String req = "select ' from user where id_user=" + gs.getId_agent() + "'";
            ResultSet rs = st2.executeQuery(req);
            String nom = "";
            while (rs.next()) {
                System.out.println(rs.getString(1));
                nom = rs.getString(1);
            }

            String req2 = "insert into service  (nom_service,prix,etat,type ,Region,description , contact ,Image) values ('" + gs.getNom_service() + "','" + gs.getPrix() + "','" + gs.getEtat() + "','" + gs.getType() + "','" + gs.getRegion() + "','" + gs.getDescription() + "','" + gs.getContact() + "','" + gs.getImage() + "')";

            //  PreparedStatement ps = c.prepareStatement(req2, Statement.RETURN_GENERATED_KEYS);
            //  ps.addBatch();
            st.executeUpdate(req2);
            //  ResultSet rt = ps.getGeneratedKeys();
            // while (rt.next()){
            //  System.out.println("Genrated ID_user:"+rt.getInt(nom));
            //  }
            System.out.println("Add Done");
        } catch (SQLException ex) {
            Logger.getLogger(GestionServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //************************************************************//
    public int get_id_user() {
        int id_user = 0;
        try {
            Statement st1 = c.createStatement();
            String req1 = "select id_user from user where connected=1 "; //+ true;
            ResultSet rs1 = st1.executeQuery(req1);

            while (rs1.next()) {
                id_user = rs1.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("errrr " + e.getMessage());
        }
        return id_user;
    }

    public List<GestionServices> Afficher_service() {
        List<GestionServices> Lservice = new ArrayList<>();

        try {
            Statement st1 = c.createStatement();
            String req1 = "select role from user where id_user= " + get_id_user();
            ResultSet rs1 = st1.executeQuery(req1);

            String role_usr = "";
            while (rs1.next()) {
                role_usr = rs1.getString(1);
            }

            Statement st = c.createStatement();
            String q;
            if (role_usr.equals("agent")) {
                q = "select id_service,nom_service,prix,etat,type ,Region,description , contact ,Image from service where id_agent=" + get_id_user();
            } else {
                q = "select id_service,nom_service,prix,etat,type ,Region,description , contact ,Image from service";
            }

            ResultSet rs = st.executeQuery(q);

            while (rs.next()) {
                GestionServices p;
                Statement st11 = c.createStatement();
                String req = "select * from avis_service where id_service = " + rs.getInt(1);
                ResultSet rs2 = st11.executeQuery(req);
                float k= 0;
                int i = 0 ;
                while (rs2.next()) {
                       k = k + rs2.getInt("rating");
                       i++ ;
                }
                p = new GestionServices(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));               
                p.setGlobal_rating(k/i);
                Lservice.add(p);
            }
        } catch (SQLException ex) {
            System.err.println("err" + ex.getMessage());
        }
        return Lservice;
    }

        public List<GestionServices> Afficher_servicef() {
        List<GestionServices> Lservice = new ArrayList<>();

        try {
           

            Statement st = c.createStatement();
            String q;
            
                q = "select id_service,nom_service,prix,etat,type ,Region,description , contact ,Image from service";
            

            ResultSet rs = st.executeQuery(q);

            while (rs.next()) {
                GestionServices p;
                Statement st11 = c.createStatement();
                String req = "select * from avis_service where id_service = " + rs.getInt(1);
                ResultSet rs2 = st11.executeQuery(req);
                float k= 0;
                int i = 0 ;
                while (rs2.next()) {
                       k = k + rs2.getInt("rating");
                       i++ ;
                }
                p = new GestionServices(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));               
                p.setGlobal_rating(k/i);
                Lservice.add(p);
            }
        } catch (SQLException ex) {
            System.err.println("err" + ex.getMessage());
        }
        return Lservice;
    }
    public void afficher() {
        try {
            Statement st = c.createStatement();
            String req = "select * from service ";
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getString(4) + rs.getString(5) + rs.getString(6) + rs.getString(7) + rs.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //***************************************************************//
    public void modifier(int id_service, String nom_service, double Prix, String Etat, String type, String region, String description, String contact, String image) {
        try {
            PreparedStatement pt = c.prepareStatement("update service set nom_service=?,prix=?,etat=?, type=? ,Region=?,description=?, contact=? ,Image=? where id_service=?");

            pt.setString(1, nom_service);
            pt.setDouble(2, Prix);
            pt.setString(3, Etat);
            pt.setString(4, type);
            pt.setString(5, region);
            pt.setString(6, description);
            pt.setString(7, contact);
            pt.setString(8, image);

            pt.setInt(9, id_service);

            pt.executeUpdate();

            System.out.println("change Done");
        } catch (SQLException ex) {
            Logger.getLogger(GestionServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //*************************************************************************//     
    public void supprimer(int id) {
        try {
            PreparedStatement pt = c.prepareStatement("delete from service where id_service= ? ");

            pt.setInt(1, id);
            pt.executeUpdate();

            System.out.println("delete Done");
        } catch (SQLException ex) {
            Logger.getLogger(GestionServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String get_nom_pep(GestionServices p) {
        String n = "";
        try {
            Statement st1 = c.createStatement();
            String req1 = "select nom_pepiniere from user where id_user= " + p.getId_agent();
            ResultSet rs1 = st1.executeQuery(req1);

            while (rs1.next()) {
                n = rs1.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("errrr " + e.getMessage());
        }
        return n;
    }

}
