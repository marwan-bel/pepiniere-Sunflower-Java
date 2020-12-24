/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entities.Produit;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Maconnexion;

/**
 *
 * @author gazzah
 */
public class PDFApi {
    Connection conn=  Maconnexion.getinstance().getConn();
 Statement stm,stm2;
    
      public void Imprimer() throws IOException {
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\cmd2.pdf"));
            doc.open();
            
            Image image = Image.getInstance("C:\\test.png");
            image.scaleAbsoluteHeight(92);
            image.scaleAbsoluteWidth(600);
            image.setAlignment(Image.ALIGN_CENTER);
             
            doc.add(image);
            String req_conn="select * from Commande";
           
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("List des Commandes: "));
            doc.add(new Paragraph(" "));
            stm=conn.createStatement();
            ResultSet rs=stm.executeQuery(req_conn);
              
         PdfPTable table= new PdfPTable(7);
         table.setWidthPercentage(100);
         PdfPCell cell;
         cell= new PdfPCell(new Phrase("Identifiant du commande",FontFactory.getFont("Comic Sans MS", 10)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.GRAY);
         table.addCell(cell);
         
          cell= new PdfPCell(new Phrase("Identifiant du Client",FontFactory.getFont("Comic Sans MS", 10)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.GRAY);
         table.addCell(cell);
         
          cell= new PdfPCell(new Phrase("Etat du commande",FontFactory.getFont("Comic Sans MS", 10)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.GRAY);
         table.addCell(cell);
         
          cell= new PdfPCell(new Phrase("Date du commande",FontFactory.getFont("Comic Sans MS", 10)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.GRAY);
         table.addCell(cell);
         
          cell= new PdfPCell(new Phrase("Mode de paiment",FontFactory.getFont("Comic Sans MS", 10)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
          
        cell= new PdfPCell(new Phrase("Adresse de livraison",FontFactory.getFont("Comic Sans MS", 10)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
           cell= new PdfPCell(new Phrase("Prix",FontFactory.getFont("Comic Sans MS", 10)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
        
        //********************rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(6),rs.getString(5),rs.getFloat(6),rs.getTimestamp(7)
        while(rs.next()){
              cell= new PdfPCell(new Phrase(rs.getString("id_cmd"),FontFactory.getFont("Comic Sans MS", 9)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         
         table.addCell(cell);
         
          cell= new PdfPCell(new Phrase(rs.getString("id_client"),FontFactory.getFont("Comic Sans MS", 9)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  
         table.addCell(cell);
         
          cell= new PdfPCell(new Phrase(rs.getString("etat_cmd"),FontFactory.getFont("Comic Sans MS", 9)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
         table.addCell(cell);
         
          cell= new PdfPCell(new Phrase(rs.getString("date_cmd"),FontFactory.getFont("Comic Sans MS", 9)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       
         table.addCell(cell);
         
          cell= new PdfPCell(new Phrase(rs.getString("mode_p"),FontFactory.getFont("Comic Sans MS", 9)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
     
          table.addCell(cell);
          
          cell= new PdfPCell(new Phrase(rs.getString("lieu_liv"),FontFactory.getFont("Comic Sans MS", 9)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       
          table.addCell(cell);
          
          cell= new PdfPCell(new Phrase(rs.getString("montant"),FontFactory.getFont("Comic Sans MS", 9)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         
          table.addCell(cell);
                  }     
          
        doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File("C:\\cmd2.pdf"));
        } catch (DocumentException | SQLException ex) {
            
        }
    }
      
      
        public void ImprimerProduitacheter(Float price,Float remise) throws IOException {
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\cmd.pdf"));
            doc.open();
            
            Image image = Image.getInstance("C:\\test.png");
            image.scaleAbsoluteHeight(92);
            image.scaleAbsoluteWidth(600);
            image.setAlignment(Image.ALIGN_CENTER);
             DaoUsers user=new DaoUsers();
             DAOLigneCommande lcmd=new DAOLigneCommande();
             
            doc.add(image);
                String req_conn="select nom from user where connected=1";
            stm=conn.createStatement();
            ResultSet rs=stm.executeQuery(req_conn);
           String nom="";
            while(rs.next())
            {      nom=rs.getString(1);   
                            doc.add(new Paragraph("Cher(e) " +nom+",\n" +
"\n" +
"Merci d’avoir effectué vos achats sur SunFlower ! Votre commande est en attente de confirmation.\n" +
"\n" +
"Notez s'il vous plaît :\n" +
"\n" +
"       * Si vous ne répondez pas dans les 48 heures qui suivent , nous annulerons \n votre commande et vous en informerons par e-mail.\n" +
"\n" +
"       * Une fois la commande confirmée, vous ne serez pas en mesure de modifier \n les détails de votre commande par exemple (destinataire, adresse de livraison). Si nécessaire, veuillez-nous en informer au cours de l'appel de confirmation de votre commande. "));
            
            }

            
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Votre commande pour: "));
            doc.add(new Paragraph(" "));
           
              
         PdfPTable table= new PdfPTable(4);
         table.setWidthPercentage(100);
         PdfPCell cell;
         
         PdfPTable table2= new PdfPTable(1);
         table2.setWidthPercentage(100);
         PdfPCell cell2;
         
          PdfPTable table3= new PdfPTable(1);
         table3.setWidthPercentage(100);
         PdfPCell cell3;
         
           cell3= new PdfPCell(new Phrase("Remise",FontFactory.getFont("Comic Sans MS", 25)));
         cell3.setVerticalAlignment(Element.ALIGN_CENTER);
         cell3.setBackgroundColor(BaseColor.GRAY);
         table3.addCell(cell3);
         
         cell2= new PdfPCell(new Phrase("Montant totale",FontFactory.getFont("Comic Sans MS", 25)));
         cell2.setVerticalAlignment(Element.ALIGN_CENTER);
         cell2.setBackgroundColor(BaseColor.GRAY);
         table2.addCell(cell2);
         
         

         
         cell= new PdfPCell(new Phrase("      \n  ",FontFactory.getFont("Comic Sans MS", 25)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.GRAY);
         table.addCell(cell);
         
          cell= new PdfPCell(new Phrase("Produit",FontFactory.getFont("Comic Sans MS", 25)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.GRAY);
         table.addCell(cell);
         
          cell= new PdfPCell(new Phrase("Quantite",FontFactory.getFont("Comic Sans MS", 25)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.GRAY);
         table.addCell(cell);
         
          cell= new PdfPCell(new Phrase("Prix",FontFactory.getFont("Comic Sans MS", 25)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.GRAY);
         table.addCell(cell);
       
        int x=0;
        
        //********************rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(6),rs.getString(5),rs.getFloat(6),rs.getTimestamp(7)
        
        
       x=user.ConectedUser();
             String req2="SELECT nom_produit,prix,image,ligne_cmd.quantite,ligne_cmd.montant FROM produit,ligne_cmd WHERE ligne_cmd.id_client = "+x+" AND ligne_cmd.id_produit=produit.id_produit";
            stm2=conn.createStatement();
            ResultSet rs2=stm2.executeQuery(req2);
            while(rs2.next())
            {
                System.out.println("+++"+rs2.getString(1));
                //////IMAGE*****
                
            Image image2 = Image.getInstance("http://localhost/uploads/"+rs2.getString(3));
            image2.scaleAbsoluteHeight(10);
            image2.scaleAbsoluteWidth(10);
            image2.setAlignment(Image.ALIGN_CENTER);
         
         table.addCell(image2);
              
         
         ///*****NOM Produit*****
         System.out.println("+++"+rs2.getString(1));
          cell= new PdfPCell(new Phrase("\n \n \n"+rs2.getString("nom_produit"),FontFactory.getFont("Comic Sans MS", 15)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  
         table.addCell(cell);
          
         ////******QUANTITE*******
          cell= new PdfPCell(new Phrase("\n \n \n"+String.valueOf(rs2.getInt("quantite")),FontFactory.getFont("Comic Sans MS", 15)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
         table.addCell(cell);
         
         ////*****PRIX***
         
          cell= new PdfPCell(new Phrase("\n \n \n"+String.valueOf(rs2.getFloat("prix"))+"\n TND",FontFactory.getFont("Comic Sans MS", 15)));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       
         table.addCell(cell);
         
         
       
            }
            cell3= new PdfPCell(new Phrase("                                                                                                 "+String.valueOf(remise)+" TND",FontFactory.getFont("Comic Sans MS", 15)));
         cell3.setVerticalAlignment(Element.ALIGN_CENTER);
         table3.addCell(cell3);
            
               cell2= new PdfPCell(new Phrase("                                                                                                 "+String.valueOf(price)+" TND",FontFactory.getFont("Comic Sans MS", 15)));
         cell2.setVerticalAlignment(Element.ALIGN_CENTER);
         table2.addCell(cell2);
         
            doc.add(table);
            doc.add(new Paragraph(" "));
            doc.add(table3);
            doc.add(table2);
            doc.close();
            Desktop.getDesktop().open(new File("C:\\cmd.pdf"));
            
        } catch (DocumentException | SQLException ex) {
                System.out.println("sql exception: "+ex.getMessage());
        }
    }
}
