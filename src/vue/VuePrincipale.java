package vue;

import controleur.ControleurVue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modele.ModeleBateau;

public class VuePrincipale extends Application
{
	private PanneauHeader panneauHeader;
	private PanneauListe panneauListe;
	private PanneauModifierItem panneauModifierItem;
	private BorderPane panneauPrincipale;
	private PanneauAjouterItem panneauAjouterItem;
	private PanneauSupprimerItem panneauSupprimerItem;
	private Test test;
	
	@Override
	public void start(Stage scenePrincipale)
	{
		ControleurVue.getInstance().setVuePrincipale(this);
		
		panneauHeader = new PanneauHeader();
		panneauHeader.setStyle("-fx-background-color: #003399");
		panneauListe = new PanneauListe();
		test = new Test();
		
		panneauPrincipale = new BorderPane();
		
		Scene scene = new Scene(panneauPrincipale, 400, 600);
		scene.getStylesheets().add("Style.css");
		
		panneauHeader.setPrefSize(scene.getWidth(), 30);
		panneauHeader.setStyle("-fx-background-color: #0698D7");
		panneauListe.setPrefSize(scene.getWidth(), (scene.getHeight() - 30));
		panneauListe.setStyle("-fx-background-color: #0698D7");
		
		panneauPrincipale.setPrefSize(scene.getWidth(), scene.getHeight());
		panneauPrincipale.setTop(panneauHeader);
		panneauPrincipale.setCenter(panneauListe);
		
		scenePrincipale.setScene(scene);
		scenePrincipale.show();
	}
	
	public void construirePanneauModifierListe(ModeleBateau bateau)
	{
		panneauModifierItem = new PanneauModifierItem(bateau);
		
		panneauPrincipale.setCenter(panneauModifierItem);
	}

	public void construirePanneauListe() 
	{
		panneauListe = new PanneauListe();
		
		panneauPrincipale.setCenter(panneauListe);
	}

	public void construirePanneauAjouterItem() 
	{
		panneauAjouterItem = new PanneauAjouterItem();
		
		panneauPrincipale.setCenter(panneauAjouterItem);
	}

	public void construirePanneauSupprimerItem(ModeleBateau bateau)
	{
		panneauSupprimerItem = new PanneauSupprimerItem(bateau);
		panneauPrincipale.setCenter(panneauSupprimerItem);
	}

}
