	package vue;

import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import donnees.BateauDAO;
import modele.ModeleBateau;

public class PanneauListe extends Region
{
	private ListView<PanneauItemListe> panneauListeItem;
	
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
		vBox.setStyle("-fx-background-color: #0698D7");
		ImageView imageView = new ImageView(new Image(PanneauListe.class.getResourceAsStream("images/logo-traversiers.png")));
		imageView.setFitWidth(400);
		imageView.setFitHeight(100);
		vBox.getChildren().add(imageView);
		
		Button btnActionAjouterItem = new Button("Ajouter");
		btnActionAjouterItem.setStyle("-fx-background-color: #003399");
		btnActionAjouterItem.getStyleClass().add("custom-text");
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
		for(ModeleBateau bateau : BateauDAO.getInstance().recupererListeBateaux())
		{
			panneauListeItem.getItems().add(new PanneauItemListe(bateau));
			//panneauListeItem.getItems().add(new PanneauItemListe(new ModeleBateau(idBateau, nom, marque, modele, annee, longueur, largeur, hauteur)));
		}
	}
}
