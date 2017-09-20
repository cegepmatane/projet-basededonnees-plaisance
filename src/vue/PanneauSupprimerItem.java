package vue;

import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import modele.ModeleBateau;

import java.sql.*;

public class PanneauSupprimerItem extends Region 
{

    private ModeleBateau bateau;
    static final String DB_URL = "jdbc:mysql://localhost:3306/portmatane?autoReconnect=true&useSSL=false";

    static final String USER = "root";
    static final String PASS = "";

    public PanneauSupprimerItem(ModeleBateau bateau) 
    {
        super();
        this.bateau = bateau;
        
        construirePanneau();
    }

    private void construirePanneau()
    {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label labelTitreSupprimerItem = new Label("Supprimer bateau " + bateau.getNom());

        Button btnActionRetourEnArriere = new Button("Annuler");
        btnActionRetourEnArriere.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                ControleurVue.getInstance().actionRetourEnArriere();
            }
        });

        Button BtnActionSauvegardeeModification = new Button("Supprimer");
        BtnActionSauvegardeeModification.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //TODO: a faire Sauvegarde;

                //Supprimer ici
                Connection conn = null;
                Statement stmt = null;
                try {
                    //STEP 2: Register JDBC driver
                    Class.forName("com.mysql.jdbc.Driver");

                    //STEP 3: Open a connection
                    System.out.println("Connecting to database...");
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);

                    //STEP 4: Execute a query
                    System.out.println("Creating statement...");
                    stmt = conn.createStatement();
                    String sqlSupprimer;
                    sqlSupprimer = "DELETE FROM bateau WHERE idBateau= " + bateau.getId();
                    stmt.executeUpdate(sqlSupprimer); //updateQuery

                    //STEP 6: Clean-up environment
                    //rs.close();
                    stmt.close();
                    conn.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ControleurVue.getInstance().actionRetourEnArriere();
            }
        });

        grid.add(labelTitreSupprimerItem, 0, 0);
        grid.add(btnActionRetourEnArriere, 0, 1);
        grid.add(BtnActionSauvegardeeModification, 2, 1);

        this.getChildren().add(grid);
    }
}
