package donnees;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.ModeleBateau;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BateauDAO {
    private static BateauDAO instance;

    private Connection conn;
    static final String JDBC_DRIVER = "org.gjt.mm.mysql.Driver"; //A changer selon driver
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false"; //A changer selon table

    static final String USER = "root";
    static final String PASS = "maxime";

    public static BateauDAO getInstance() {
        if (instance == null) instance = new BateauDAO();
        return instance;
    }

    private BateauDAO() {
        try {
            Class.forName(JDBC_DRIVER);

            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public List<ModeleBateau> recupererListeBateaux() {
//        List<ModeleBateau> listeBateaux = new ArrayList<>();
//
//        try {
//            //System.out.println("Creating statement...");
//            Statement stmt = conn.createStatement();
//            String sql = "SELECT idBateau, nom, marque, modele, annee, longueur, largeur, hauteur FROM bateau";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                int idBateau = rs.getInt("idBateau");
//                String nom = rs.getString("nom");
//                String marque = rs.getString("marque");
//                String modele = rs.getString("modele");
//                int annee = rs.getInt("annee");
//                float longueur = rs.getFloat("longueur");
//                float largeur = rs.getFloat("largeur");
//                float hauteur = rs.getFloat("hauteur");
//
//                listeBateaux.add(new ModeleBateau(idBateau, nom, marque, modele, annee, longueur, largeur, hauteur));
//            }
//
//            stmt.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return listeBateaux;
//    }

    public ObservableList<ModeleBateau> recupererListeBateaux() {
        ObservableList<ModeleBateau> listeBateaux = FXCollections.observableArrayList();

        try {
            //System.out.println("Creating statement...");
            Statement stmt = conn.createStatement();
            String sql = "SELECT idBateau, nom, marque, modele, annee, longueur, largeur, hauteur FROM bateau";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int idBateau = rs.getInt("idBateau");
                String nom = rs.getString("nom");
                String marque = rs.getString("marque");
                String modele = rs.getString("modele");
                int annee = rs.getInt("annee");
                float longueur = rs.getFloat("longueur");
                float largeur = rs.getFloat("largeur");
                float hauteur = rs.getFloat("hauteur");

                listeBateaux.add(new ModeleBateau(idBateau, nom, marque, modele, annee, longueur, largeur, hauteur));
            }

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listeBateaux;
    }

    public void supprimerBateau(int id) {
        try {
            Statement stmt = conn.createStatement();
            String sqlSupprimer = "DELETE FROM bateau WHERE idBateau = " + id;
            stmt.executeUpdate(sqlSupprimer);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifierBateau(ModeleBateau bateau) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE bateau SET nom='" + bateau.getNom() + "', marque='" + bateau.getMarque() + "', modele='" + bateau.getModele() + "' WHERE idBateau= '" + bateau.getId() + "'";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouterBateau(ModeleBateau bateau) {
        try {
            Statement stmt = conn.createStatement();
            String sqlAjouter = "INSERT INTO `bateau` (`idBateau`, `nom`, `marque`, `modele`, `annee`, `longueur`, `largeur`, `hauteur`) VALUES (NULL, '" + bateau.getNom() + "' , '" + bateau.getMarque() + "', '" + bateau.getModele() + "', '2017-09-11', '24', '24', '24');";
            stmt.executeUpdate(sqlAjouter);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
