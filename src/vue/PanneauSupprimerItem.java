package vue;

import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import donnees.BateauDAO;
import modele.ModeleBateau;

public class PanneauSupprimerItem extends Region 
{

    private ModeleBateau bateau;

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
        grid.setStyle("-fx-background-color: #0698D7");
        this.setStyle("-fx-background-color: #0698D7");

        Label labelTitreSupprimerItem = new Label("Supprimer bateau " + bateau.getNom());

        Button btnActionRetourEnArriere = new Button("Annuler");
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

        Button BtnActionSauvegardeeModification = new Button("Supprimer");
        BtnActionSauvegardeeModification.setStyle("-fx-background-color: #003399");
        BtnActionSauvegardeeModification.getStyleClass().add("custom-text");
        BtnActionSauvegardeeModification.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
            	BateauDAO.getInstance().supprimerBateau(bateau.getId());
            	
                ControleurVue.getInstance().actionRetourEnArriere();
            }
        });

        grid.add(labelTitreSupprimerItem, 0, 0);
        grid.add(btnActionRetourEnArriere, 0, 1);
        grid.add(BtnActionSauvegardeeModification, 2, 1);

        this.getChildren().add(grid);
    }
}
