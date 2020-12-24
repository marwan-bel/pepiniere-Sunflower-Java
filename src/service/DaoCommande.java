package service;
import Entities.Commande;
import interfaces.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import utils.Maconnexion;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gazzah
 */
public class DaoCommande implements IdaoCommande{
Connection conn=  Maconnexion.getinstance().getConn();
 Statement stm;
   
 @Override
    public void AjouterCmd(Commande cmd) {
       
        try {
             int idcmd = 0;
        
             ResultSet rs1 = conn.createStatement().executeQuery("SELECT MAX(id_cmd) FROM commande");
                         while (rs1.next()) {idcmd=rs1.getInt(1);}
            idcmd=idcmd+1;
        String req_conn="insert into commande(lieu_liv,id_cmd,id_produit,id_client,montant,qte)"
                +   " select ?,?,id_produit,id_client,montant,quantite from ligne_cmd l where l.id_client=1";
  
  PreparedStatement prt = conn.prepareStatement(req_conn);
            
           
            prt.setString(1, cmd.getLieu_liv());          
            prt.setInt(2, idcmd);   
         
            System.out.println("****"+prt);
            prt.executeUpdate();
               System.out.println("****"+prt);
                String req_conn2="delete from ligne_cmd where id_client=?";
       
  PreparedStatement prt2 = conn.prepareStatement(req_conn2);
            
            prt2.setInt(1,1);
            prt2.executeUpdate();
            {JOptionPane.showMessageDialog(null,"Commande Ajouter en succes");}
    } catch (SQLException s) {
        {JOptionPane.showMessageDialog(null,"Erreur!");}
        System.out.println("Erreur dans AjouterCmd"+s);
    }
    }

     @Override

    public void SupprimerCmd(int i) {
         try {
        String req_conn="delete from commande where id_cmd=?";
       
  PreparedStatement prt = conn.prepareStatement(req_conn);
            
            prt.setInt(1,i);
            prt.executeUpdate();
            {JOptionPane.showMessageDialog(null,"Commande annuler");}
    } catch (SQLException s) {
        {JOptionPane.showMessageDialog(null,"Verifier votre information!");}
        System.out.println("Erreur dans Supprim Commande");
    }
    }



     @Override

    public void ModifierCmd(Commande cmd) {
                try {
        String req_conn="update commande set etat_cmd=?, mode_p=?, lieu_liv=? ,montant=? where id_cmd="+cmd.getId_cmd();
                
       
  PreparedStatement prt = conn.prepareStatement(req_conn);
            
              
            prt.setString(1, cmd.getEtat_cmd());
            prt.setString(2, cmd.getMode_p());
            prt.setString(3, cmd.getLieu_liv());
            prt.setFloat(4, cmd.getMontant());
            prt.executeUpdate();
                    System.out.println("***"+prt);
            {JOptionPane.showMessageDialog(null,"Modifier en succes");}
    } catch (SQLException s) {
        {JOptionPane.showMessageDialog(null,"Erreur!");}
        System.out.println("Erreur dans ModifierCmd");
    }
    }

   

    @Override
    public void AfficherCmd(Commande cmd) {
         
    try {
        
        String req_conn="select * from commande ";
        //PreparedStatement prt = conn.prepareStatement(req_conn);
        ResultSet rs = stm.executeQuery(req_conn);
        while (rs.next())
        {//int id_cmd, String etat_cmd, String mode_p, String lieu_liv, float montant
            System.out.println("commande id: "+rs.getInt(1)
                                +" id client: "+ rs.getString(2)
                                +" etat cmd: "+rs.getString(3)
                                +" date cmd: "+rs.getString(4)
                                +" mode paiement: "+rs.getString(5)
                                +" lieu de livraison: "+rs.getString(6)
                                +" montant : "+rs.getString(7)
                                );
        }
    } catch (SQLException ex) {
        System.out.println("erreur dafichage du commande"+ ex);
    }
 }

  public void Imprimer() throws IOException {
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\List des commandes.pdf"));
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
            Desktop.getDesktop().open(new File("C:\\List des commandes.pdf"));
        } catch (DocumentException | SQLException ex) {
            
        }
    }
     
}
