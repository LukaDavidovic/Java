import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    static AukcijskaKuca aukcijskaKuca = new AukcijskaKuca();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox hBoxRoot = new HBox(10);
        hBoxRoot.setPadding(new Insets(10, 10, 10, 10));

        VBox vBoxLevi = new VBox(10);
        VBox vBoxDesni = new VBox(10);

        hBoxRoot.getChildren().addAll(vBoxLevi, vBoxDesni);

        //----------------------------------------------------------------------------

        Button buttonUcitaj = new Button("Ucitaj");
        TextArea textArea = new TextArea();
        textArea.setMinSize(580,190);
        HBox hBoxZarada = new HBox(10);

        vBoxLevi.getChildren().addAll(buttonUcitaj, textArea, hBoxZarada);

        //----------------------------------------------------------------------------

        Label labelZarada = new Label("Zarada kuce:");
        labelZarada.setMinHeight(25);
        TextField textFieldZarada = new TextField("0");

        hBoxZarada.getChildren().addAll(labelZarada, textFieldZarada);

        //---------------------------------------------------------------------------

        Label labelID = new Label("ID predmeta za licitaciju:");
        TextField textFieldID = new TextField();
        Label labelCena = new Label("Ponudjena cena:");
        TextField textFieldCena = new TextField();
        Button buttonLicitiraj = new Button("Licitiraj");
        Label labelUspesnostLicitacije = new Label();

        vBoxDesni.setPadding(new Insets(40, 0, 0, 0));
        vBoxDesni.getChildren().addAll(labelID, textFieldID, labelCena, textFieldCena, buttonLicitiraj, labelUspesnostLicitacije);

        //---------------------------------------------------------------------------

        buttonUcitaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                aukcijskaKuca.ucitajUmetnickaDelaIzFajla("src/za_prodaju.txt");
                textArea.setText(aukcijskaKuca.toString());
                buttonUcitaj.setDisable(true);
            }
        });

        buttonLicitiraj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String ID = textFieldID.getText().trim();
                int ponudjenaCena = Integer.parseInt(textFieldCena.getText().trim());

                boolean uspesnost = aukcijskaKuca.licitacija(ID, ponudjenaCena);
                if (uspesnost) {
                    labelUspesnostLicitacije.setTextFill(Color.BLUE);
                    labelUspesnostLicitacije.setText("Uspesna licitacija!");
                    textArea.setText(aukcijskaKuca.toString());
                    textFieldZarada.setText("" + aukcijskaKuca.getZarada());
                }
                else {
                    labelUspesnostLicitacije.setTextFill(Color.RED);
                    labelUspesnostLicitacije.setText("Neuspesna licitacija!");
                }
            }
        });

        //---------------------------------------------------------------------------

        Scene scene = new Scene(hBoxRoot, 800, 280);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Aukcijska kuca");
        primaryStage.show();
    }
}
