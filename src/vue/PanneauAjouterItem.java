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

public class PanneauAjouterItem extends Region
{
	private TextField nomBateau;
	private TextField marqueBateau;
	private TextField modeleBateau;
    static final String DB_URL = "jdbc:mysql://localhost/portmatane?autoReconnect=true&useSSL=false";
    static final String USER = "root";
    static final String PASS = "";
	
	public PanneauAjouterItem()
	{
		super();
		
		ConstruirePanneau();
	}

	private void ConstruirePanneau() 
	{
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		nomBateau = new TextField();
		marqueBateau = new TextField();
		modeleBateau = new TextField();
		
		
		Label labelNom = new Label("Nom : ");
		Label labelMarque = new Label("Marque : ");
		Label labelModele = new Label("Modele : ");
		
		Button btnActionRetourEnArriere = new Button("Retour");
		btnActionRetourEnArriere.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				ControleurVue.getInstance().actionRetourEnArriere();
			}
		});
		
		Button BtnActionSauvegardeeModification = new Button("Sauvegarder");
		BtnActionSauvegardeeModification.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				Connection conn = null;
                Statement stmt = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);

                    System.out.print(nomBateau.getText());
                    
                    stmt = conn.createStatement();
                    String sqlAjouter;
                    sqlAjouter = "INSERT INTO `bateau` (`idBateau`, `nom`, `marque`, `modele`, `annee`, `longueur`, `largeur`, `hauteur`) VALUES (NULL, '"+ nomBateau.getText() + "' , '"+ marqueBateau.getText() + "', '"+ modeleBateau.getText() + "', '2017-09-11', '24', '24', '24');";
                    stmt.executeUpdate(sqlAjouter); 
                    
                    stmt.close();
                    conn.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    ControleurVue.getInstance().actionRetourEnArriere();
                } catch (Exception e) {
                    e.printStackTrace();
                }
			}
		});
		
		grid.add(btnActionRetourEnArriere, 0, 6);
		grid.add(BtnActionSauvegardeeModification, 1, 6);
		grid.add(labelNom, 0, 0);
		grid.add(nomBateau,0,1);
		grid.add(labelMarque, 0, 2);
		grid.add(marqueBateau,0,3);
		grid.add(labelModele, 0, 4);
		grid.add(modeleBateau,0,5);
		
		this.getChildren().add(grid);
	}
}
