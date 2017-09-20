package vue;

import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import modele.ModeleBateau;

public class PanneauItemListe extends Region
{
	public int id;
	private HBox itemBoite;
	private ModeleBateau bateau;
	
	
	public PanneauItemListe(ModeleBateau bateau)
	{
		super();
		this.bateau = bateau;
		this.id = bateau.getId();
		System.out.println(this.id);
		ConstruirePanneau();
	}

	private void ConstruirePanneau() 
	{
		itemBoite = new HBox();
		itemBoite.setSpacing(15);
		
		Label labelNom = new Label(this.bateau.getNom());
		itemBoite.getChildren().add(labelNom);
		Label labelMarque = new Label(this.bateau.getMarque());
		itemBoite.getChildren().add(labelMarque);
		Label labelModele = new Label(this.bateau.getModele());
		itemBoite.getChildren().add(labelModele);
		Label labelAnnee = new Label (Integer.toString(this.bateau.getAnnee()));
		itemBoite.getChildren().add(labelAnnee);
		
		Button btnActionModifier = new Button("Modifier");
		btnActionModifier.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				ControleurVue.getInstance().actionModifierItem((ModeleBateau)bateau);
			}
		});
		itemBoite.getChildren().add(btnActionModifier);


		//Bouton pour supprimer un bateau contenue dans la liste
		Button btnActionSupprimer = new Button("Supprimer");
		btnActionSupprimer.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				ControleurVue.getInstance().actionSupprimerItem((ModeleBateau)bateau);
			}
		});
		itemBoite.getChildren().add(btnActionSupprimer);
		
		this.getChildren().add(itemBoite);
	}

}
