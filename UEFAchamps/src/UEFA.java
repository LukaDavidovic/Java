import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class UEFA extends Application {
    private UEFATakmicenje takmicenje = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox hBoxRoot = new HBox(10);
        hBoxRoot.setPadding(new Insets(10, 10, 10, 10));

        TextArea textArea = new TextArea("");
        VBox vBox = new VBox(30);
        vBox.setAlignment(Pos.CENTER_LEFT);

        hBoxRoot.getChildren().addAll(textArea, vBox);

        //---------------------------------------------------------------------------

        Button buttonUcitaj = new Button("Ucitaj");
        CheckBox checkBoxSortiraj = new CheckBox("Sortiraj");
        RadioButton radioButtonRastuce = new RadioButton("Rastuce");
        RadioButton radioButtonOpadajuce = new RadioButton("Opadajuce");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButtonRastuce.setToggleGroup(toggleGroup);
        radioButtonOpadajuce.setToggleGroup(toggleGroup);
        Button buttonTimovi = new Button("Timovi");
        Button buttonGrupe = new Button("Grupe");

        vBox.getChildren().addAll(buttonUcitaj, checkBoxSortiraj, radioButtonRastuce, radioButtonOpadajuce, buttonTimovi, buttonGrupe);

        //---------------------------------------------------------------------------

        buttonUcitaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    List<String> linije = Files.readAllLines(Paths.get("src/takmicenje.txt"));

                    String[] elem = linije.get(0).split(" ");
                    String sifraTakmicenja = elem[0].trim();
                    int koeficijentTakmicenja = Integer.parseInt(elem[1].trim());

                    List<Tim> timoviTakmicenja = new ArrayList<>();
                    List<Grupa> grupeTakmicenja = new ArrayList<>();

                    for(String linija : linije.subList(1, linije.size())) {
                        elem = linija.split(" ");

                        String nazivTima = elem[0].trim();

                        List<Double> bodovi = new ArrayList<>();
                        for (int i = 1; i <= 5; i++)
                            bodovi.add(Double.parseDouble(elem[i].trim()));

                        Tim tim = null;
                        String nazivGrupeTakmicenja;
                        if(elem[6].compareTo("Povlasceni") == 0) {
                            tim = new Nosilac(bodovi, nazivTima);
                            nazivGrupeTakmicenja = elem[7].trim();
                        }
                        else {
                            tim = new Nenosilac(bodovi, nazivTima, Double.parseDouble(elem[7].trim()));
                            nazivGrupeTakmicenja = elem[8].trim();
                        }

                        timoviTakmicenja.add(tim);
                        boolean ind = false;
                        for (Grupa grupa : grupeTakmicenja)
                            if(grupa.getNazivGrupe().compareTo(nazivGrupeTakmicenja) == 0){
                                ind = true;
                                grupa.dodajTim(tim);
                            }
                        if (ind == false) {
                            Grupa grupa = new Grupa(nazivGrupeTakmicenja);
                            grupa.dodajTim(tim);
                            grupeTakmicenja.add(grupa);
                        }
                    }


                    if (sifraTakmicenja.compareTo("LS") == 0)
                        takmicenje = new LigaSampiona(timoviTakmicenja, grupeTakmicenja, koeficijentTakmicenja);
                    else if(sifraTakmicenja.compareTo("LE") == 0)
                        takmicenje = new LigaEvrope(timoviTakmicenja, grupeTakmicenja, koeficijentTakmicenja);
                    else if(sifraTakmicenja.compareTo("LK") == 0)
                        takmicenje = new LigaKonferencija(timoviTakmicenja, grupeTakmicenja, koeficijentTakmicenja);
                    else
                        throw new IOException("Nedozvoljena sifra takmicenja.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonGrupe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Collections.sort(takmicenje.grupeTakmicenja, new Comparator<Grupa>() {
                    @Override
                    public int compare(Grupa g1, Grupa g2) {
                        return g1.getNazivGrupe().compareTo(g2.getNazivGrupe());
                    }
                });

                textArea.clear();
                textArea.appendText(takmicenje.toString());
                textArea.appendText("\n\n");
                textArea.appendText(takmicenje.prikaziGrupe());
            }
        });

        buttonTimovi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(checkBoxSortiraj.isSelected()){
                    if(radioButtonRastuce.isSelected())
                        Collections.sort(takmicenje.timoviTakmicenja, new Comparator<Tim>() {
                            @Override
                            public int compare(Tim t1, Tim t2) {
                                return t1.getNaziv().compareTo(t2.getNaziv());
                            }
                        });
                    else
                        Collections.sort(takmicenje.timoviTakmicenja, new Comparator<Tim>() {
                            @Override
                            public int compare(Tim t1, Tim t2) {
                                return -t1.getNaziv().compareTo(t2.getNaziv());
                            }
                        });
                }
                else
                    Collections.shuffle(takmicenje.timoviTakmicenja);

                textArea.clear();
                textArea.appendText(takmicenje.toString());
                textArea.appendText("\n\n");
                textArea.appendText(takmicenje.prikaziTimove());
            }
        });

        //---------------------------------------------------------------------------

        Scene scene = new Scene(hBoxRoot, 700, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("UEFA takmicenja");
        primaryStage.show();
    }
}
