	package vue;

import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import modele.ModeleBateau;

import java.sql.*;

public class PanneauListe extends Region
{
	private ListView<PanneauItemListe> panneauListeItem;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/portmatane?autoReconnect=true&useSSL=false";

	static final String USER = "root";
	static final String PASS = "";
	
	public PanneauListe()
	{
		super();
		
		ConstruirePanneau();
	}

	private void ConstruirePanneau() 	
	{
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10));
		vBox.setPrefSize(400, (600-30));
		
		Button btnActionAjouterItem = new Button("Ajouter");
		btnActionAjouterItem.setPrefSize(200, 15);
		btnActionAjouterItem.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				ControleurVue.getInstance().actionAjouterItem();
			}
		});
		vBox.getChildren().add(btnActionAjouterItem);


		panneauListeItem = new ListView<PanneauItemListe>();
		panneauListeItem.setPrefSize(400, 600 - 30);

		construireVueListeItem();
		vBox.getChildren().add(panneauListeItem);
		this.getChildren().add(vBox);
	}
	
	private void construireVueListeItem()
	{

		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT idBateau, nom, marque, modele, annee, longueur, largeur, hauteur FROM bateau";
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				int idBateau = rs.getInt("idBateau");
				String nom = rs.getString("nom");
				String marque = rs.getString("marque");
				String modele = rs.getString("modele");
				int annee = rs.getInt("annee");
				float longueur = rs.getFloat("longueur");
				float largeur = rs.getFloat("largeur");
				float hauteur = rs.getFloat("hauteur");
				
			
				panneauListeItem.getItems().add(new PanneauItemListe(new ModeleBateau(idBateau, nom, marque, modele, annee, longueur, largeur, hauteur)));

			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
	}
}
