package vue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import modele.ModeleBateau;

public class PanneauModifierItem extends Region
{
	private ModeleBateau bateau;
	private TextField nomBateau;
	private TextField marqueBateau;
	private TextField modeleBateau;
    static final String DB_URL = "jdbc:mysql://localhost:3306/portmatane?autoReconnect=true&useSSL=false";

    static final String USER = "root";
    static final String PASS = "";
	
	public PanneauModifierItem(ModeleBateau bateau)
	{
		super();
		this.bateau = bateau;
		
		ConstruirePanneau();
	}

	private void ConstruirePanneau() 
	{
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		nomBateau = new TextField();
		nomBateau.setText(this.bateau.getNom());
		
		marqueBateau = new TextField();
		marqueBateau.setText(bateau.getMarque());
		
		modeleBateau = new TextField();
		modeleBateau.setText(bateau.getModele());
		
		
		Label labelNom = new Label("Nom : ");
		Label labelMarque = new Label("Marque : ");
		Label labelModele = new Label("Modele : ");
		
		Label labelTitreModifierItem = new Label("Modifier");
		
		Button btnActionRetourEnArriere = new Button("Retour");
		btnActionRetourEnArriere.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				ControleurVue.getInstance().actionRetourEnArriere();
			}
		});
		
		Button BtnActionSauvegardeeModification = new Button("Sauvegarde");
		BtnActionSauvegardeeModification.setOnAction(new EventHandler<ActionEvent>() 
		{
			//TODO: Mettre ds DAO
			
			@Override
			public void handle(ActionEvent event) 
			{
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
                    String sqlModifier;
                    
                    sqlModifier = "UPDATE bateau SET nom='" + nomBateau.getText() + "', marque='" + marqueBateau.getText() + "', modele='" + modeleBateau.getText() + "' WHERE nom= '" + bateau.getNom() + "'";
                    System.out.println(sqlModifier);
                    stmt.executeUpdate(sqlModifier); //updateQuery

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
		
		grid.add(labelTitreModifierItem, 0, 0);
		grid.add(labelNom, 0, 2);
		grid.add(labelMarque, 0, 3);
		grid.add(labelModele, 0, 4);
		grid.add(nomBateau, 1, 2);
		grid.add(marqueBateau, 1, 3);
		grid.add(modeleBateau, 1, 4);
		grid.add(btnActionRetourEnArriere, 0, 6);
		grid.add(BtnActionSauvegardeeModification, 2, 6);
		
		this.getChildren().add(grid);
	}
}
