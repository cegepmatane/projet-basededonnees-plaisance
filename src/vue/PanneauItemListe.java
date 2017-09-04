package vue;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class PanneauItemListe extends Region
{
	private HBox itemBoite;
	private String nomItem;
	
	public PanneauItemListe(String nomItem)
	{
		super();
		this.nomItem = nomItem;
		
		ConstruirePanneau();
	}

	private void ConstruirePanneau() 
	{
		itemBoite = new HBox();
		
		Label label = new Label(this.nomItem);
		itemBoite.getChildren().add(label);
		
		Button btnActionModifier = new Button("Modifier");
		itemBoite.getChildren().add(btnActionModifier);
		
		this.getChildren().add(itemBoite);
	}

}
