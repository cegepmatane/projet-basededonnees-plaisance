package vue;

import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import donnees.BateauDAO;
import modele.ModeleBateau;

public class PanneauModifierItem extends Region
{
	private ModeleBateau bateau;
	private TextField nomBateau;
	private TextField marqueBateau;
	private TextField modeleBateau;
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false"; //A changer selon table

    static final String USER = "root";
    static final String PASS = "maxime";
	
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
		grid.setStyle("-fx-background-color: #0698D7");
		this.setStyle("-fx-background-color: #0698D7");
		
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
		btnActionRetourEnArriere.setStyle("-fx-background-color: #003399");
		btnActionRetourEnArriere.getStyleClass().add("custom-text");
		btnActionRetourEnArriere.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				ControleurVue.getInstance().actionRetourEnArriere();
			}
		});
		
		Button BtnActionSauvegardeeModification = new Button("Sauvegarde");
		BtnActionSauvegardeeModification.setStyle("-fx-background-color: #003399");
		BtnActionSauvegardeeModification.getStyleClass().add("custom-text");
		BtnActionSauvegardeeModification.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				//"UPDATE bateau SET nom='" + nomBateau.getText() + "', marque='" + marqueBateau.getText() + "', modele='" + modeleBateau.getText() + "' WHERE nom= '" + bateau.getNom() + "'";
				
				bateau.setNom(nomBateau.getText());
				bateau.setMarque(marqueBateau.getText());
				bateau.setModele(modeleBateau.getText());
				
				BateauDAO.getInstance().modifierBateau(bateau);
				
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
