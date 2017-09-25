package vue;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modele.BateauDAO;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        TableView table = new TableView();
        Scene scene = new Scene(new Group());
        stage.setTitle("Plaisanciers");
        table.resize(800,400);

        final Label label = new Label("Liste des navires");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn nameCol = new TableColumn("Nom");
        TableColumn marqueCol = new TableColumn("Marque");
        TableColumn modeleCol = new TableColumn("Modele");
        TableColumn anneeCol = new TableColumn("Annee");
        TableColumn longueurCol = new TableColumn("Longueur");
        TableColumn largeurCol = new TableColumn("Largeur");

        nameCol.setCellValueFactory(
                new PropertyValueFactory<BateauDAO,String>("nom")
        );
        marqueCol.setCellValueFactory(
                new PropertyValueFactory<Person,String>("marque")
        );
        modeleCol.setCellValueFactory(
                new PropertyValueFactory<Person,String>("modele")
        );
        anneeCol.setCellValueFactory(
                new PropertyValueFactory<Person,String>("annee")
        );
        longueurCol.setCellValueFactory(
                new PropertyValueFactory<Person,String>("longueur")
        );
        largeurCol.setCellValueFactory(
                new PropertyValueFactory<Person,String>("largeur")
        );

        table.setItems(BateauDAO.getInstance().recupererListeBateaux());

        table.getColumns().addAll(nameCol, marqueCol, modeleCol, anneeCol, longueurCol, largeurCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}