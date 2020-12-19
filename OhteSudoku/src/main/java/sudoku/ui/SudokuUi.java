package sudoku.ui;

import static java.lang.System.nanoTime;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sudoku.dao.TimeDao;
import sudoku.domain.SudokuGame;

public class SudokuUi extends Application {

    private TimeDao times;
    private SudokuGame game;
    private Scene menuScene;
    private Scene gameScene;
    private Scene gameMenuScene;
    private Scene winScene;
    
    // Menu
    private BorderPane menuPane;
    private Label menuLabel;
    private Button newGameButton;
    private VBox menuBox;
    
    //Gamemenu
    private BorderPane gameMenuPane;
    private Label gameMenuLabel;
    private Button gameMenuNewGameButton;
    private Button resumeButton;
    private VBox gameMenuBox;
    
    
    //Win
    private BorderPane winPane;
    private VBox winBox;
    private Label winLabel;
    private Button winNewGame;
    private Button winMenu;
    
    private Button selectedButton;
    private boolean notesOn; 
    private long startTime;
    private long endTime;
    private int duration;
    
    /**
     * Asettaa valikkonäkymän ikkunaan
     * @param window
     * @throws Exception 
     */
    
    public void init() throws Exception {
    }
    
    @Override
    public void start(Stage window) throws Exception {
        times = new sudoku.dao.TimeDao();
        game = new sudoku.domain.SudokuGame();
        createMenuScene(window); 
        window.setScene(menuScene);
        window.show();
    }
    
    //Scenes
    
    /**
     * Luo valikkonäkymän
     * @param window 
     */
        
    public void createMenuScene(Stage window) {
        menuPane = new BorderPane();
        Label sudokuLabel = new Label ("SUDOKU");
        sudokuLabel.setFont(Font.font("Arial", 70));
        menuBox = new VBox();
        menuLabel = new Label("Valikko");
        menuLabel.setFont(Font.font("Arial", 40));
        menuBox.getChildren().addAll(sudokuLabel, menuLabel);
        menuBox.setSpacing(50);
        newGameButton = new Button("Uusi peli");
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setPadding(new Insets (80, 0, 0, 0));
        menuBox.setAlignment(Pos.CENTER);
        menuPane.setCenter(newGameButton);
        menuPane.setTop(menuBox);
        newGameButton.setScaleX(2);
        newGameButton.setScaleY(2);
        pushNewGameButton(window, newGameButton);
        menuScene = new Scene(menuPane, 800, 1000);
    }

    /**
     * Luo pelinäkymän
     * @param window 
     */ 
    
    public void createNewGame(Stage window) {
        BorderPane gamePane = new BorderPane();
        GridPane sudokuGrid = new GridPane();
        GridPane textGrid = new GridPane();
        createGrid(sudokuGrid);     
        sudokuGrid.setScaleX(1.8);
        sudokuGrid.setScaleY(1.8);
        textGrid.setScaleX(1.8);
        textGrid.setScaleY(1.8);
        HBox numbersBox = new HBox();
        createNumberButtons(window, sudokuGrid, numbersBox);
        numbersBox.setSpacing(40);
        VBox gameOptionsBox = new VBox();
        ToggleButton noteButton = new ToggleButton("Muistiinpanot");
        Button eraseButton = new Button("Poista");
        eraseButton.setScaleX(1.4);
        eraseButton.setScaleY(1.4);
        Button gameMenuButton = new Button("Valikko");
        gameMenuButton.setScaleX(1.8);
        gameMenuButton.setScaleY(1.8);
        gameOptionsBox.getChildren().addAll(gameMenuButton);
        gameOptionsBox.setPadding(new Insets(0, 0, 50, 0));
        textGrid.setAlignment(Pos.CENTER);     
        sudokuGrid.setAlignment(Pos.CENTER);     
        numbersBox.setAlignment(Pos.CENTER);
        VBox gameBox = new VBox();
        VBox box = new VBox();
        gameBox.getChildren().addAll(sudokuGrid, numbersBox);
        gameBox.setPadding(new Insets(60, 0, 0, 0));
        box.getChildren().addAll(gameBox, noteButton, eraseButton);
        gameBox.setAlignment(Pos.BOTTOM_CENTER);
        gameBox.setSpacing(180);
        gamePane.setCenter(box);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(50);
        gamePane.setBottom(gameOptionsBox);
        gameOptionsBox.setAlignment(Pos.CENTER);
        pushEraseButton(eraseButton, sudokuGrid);
        pushGameMenuButton(gameMenuButton, window);
        pushNoteButton(noteButton);
        gameScene = new Scene(gamePane, 800, 1000);
        startTime = nanoTime();
    }
       
    /**
     * Luo pelivalikon
     * @param window 
     */
    
    public void createGameMenu(Stage window) {
        gameMenuPane = new BorderPane();
        gameMenuBox = new VBox();
        gameMenuLabel = new Label("Valikko");
        gameMenuNewGameButton = new Button("Uusi peli");
        resumeButton = new Button("Jatka peliä");
        gameMenuBox.getChildren().add(gameMenuLabel);
        gameMenuBox.getChildren().add(resumeButton);
        gameMenuBox.getChildren().add(gameMenuNewGameButton);
        gameMenuBox.setSpacing(100);
        gameMenuBox.setAlignment(Pos.CENTER);
        gameMenuPane.setCenter(gameMenuBox);
        pushButton(resumeButton, window, gameScene);
        pushNewGameButton(window, gameMenuNewGameButton);
        gameMenuScene = new Scene(gameMenuPane, 800, 1000);
    }

    /**
     * Luo voittonäkymän
     * @param window
     * @param sudokuGrid 
     */
    
    public void createWinScene(Stage window, GridPane sudokuGrid) {
        winPane = new BorderPane();
        winBox = new VBox();
        winLabel = new Label("Voitit pelin! Onneksi olkoon!");
        String time = setTime(duration);
        int bestDuration = 0;
        try {
            bestDuration = times.getBestTime();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String bestTime = setTime(bestDuration);
        Label timeLabel = new Label("Aikasi: " + time + "\n Paras aika: " + bestTime);
        winNewGame = new Button("Uusi peli");
        winMenu = new Button("Valikko");
        winMenu.setScaleX(1.8);
        winMenu.setScaleY(1.8);
        winNewGame.setScaleX(1.8);
        winNewGame.setScaleY(1.8);
        VBox winLabelBox = new VBox();
        winLabelBox.setSpacing(40);
        winLabelBox.getChildren().addAll(winLabel, timeLabel);
        winBox.getChildren().add(winNewGame);
        winBox.getChildren().add(winMenu);
        winPane.setTop(winLabelBox);
        winLabelBox.setAlignment(Pos.CENTER);
        winLabelBox.setPadding(new Insets(50, 50, 50, 50));
        winLabel.setFont(Font.font("Arial", 40));
        timeLabel.setFont(Font.font("Arial", 30));
        winPane.setCenter(sudokuGrid);
        winPane.setBottom(winBox);
        winBox.setSpacing(50);
        winBox.setPadding(new Insets(70, 70, 70, 70));
        winBox.setAlignment(Pos.CENTER);
        pushNewGameButton(window, winNewGame);
        pushButton(winMenu, window, menuScene);
        winScene = new Scene(winPane, 800, 1000);
    }
    
    /**
     * Luo sudokuruudukon SudokuGame-olion start-muutuujan pohjalta
     * @param sudokuGrid 
     */
    
    public void createGrid(GridPane sudokuGrid) {
        for (int x=0; x<9; x++) {
            for (int y=0; y<9; y++) {
                int number = game.getNumberOnField(x, y);
                int id = (y)*9+x+1;
                Button button = null;
                if (number == 0) {
                    button = createEmptyButton(sudokuGrid, id, x, y);
                } else {
                    button = createButton(sudokuGrid, id, x, y, number);
                }
                String border = setBorder(x, y);
                button.setStyle("-fx-font-weight: bold; -fx-background-color: black, white; -fx-background-radius: 0;" + border);
            }
        }
    }
    
    /**
     * Luodaan 3x3-ruuduikoita rajaavat reunat
     * @param button
     * @param y
     * @param x 
     */
    
    public String setBorder(int y, int x) {
        if (x == 3 || x == 6) {
            if (y == 3 || y == 6) {
                //x = 3 ja y = 3: TOP LEFT
                return "-fx-background-insets: 0, 2.5 1 1 2.5";
                //button.setStyle("-fx-font-weight: bold; -fx-background-color: black, white; -fx-background-radius: 0; -fx-background-insets: 0, 2.5 1 1 2.5;");
            } else if (y == 2 || y == 5) {
                //x = 3 ja y = 2: TOP RIGHT
                return "-fx-background-insets: 0, 2.5 2.5 1 1";
                //button.setStyle("-fx-font-weight: bold; -fx-background-color: black, white; -fx-background-radius: 0; -fx-background-insets: 0, 2.5 2.5 1 1;");
            } else {
                // x = 3: TOP
                return "-fx-background-insets: 0, 2.5 1 1 1";
                //button.setStyle("-fx-font-weight: bold; -fx-background-color: black, white; -fx-background-radius: 0; -fx-background-insets: 0, 2.5 1 1 1;");
            }
        } else if (x == 2 || x == 5) {
            if (y == 3 || y == 6) {
                // x = 2 ja y = 3: BOTTOM LEFT
                return "-fx-background-insets: 0, 1 1 2.5 2.5";
                //button.setStyle("-fx-font-weight: bold; -fx-background-color: black, white; -fx-background-radius: 0; -fx-background-insets: 0, 1 1 2.5 2.5;");
            } else if (y == 2 || y == 5) {
                // x = 2 ja y = 2 BOTTOM RIGHT
                return "-fx-background-insets: 0, 1 2.5 2.5 1";
                //button.setStyle("-fx-font-weight: bold; -fx-background-color: black, white; -fx-background-radius: 0; -fx-background-insets: 0, 1 2.5 2.5 1;");
            } else {
                // x = 2: BOTTOM
                return "-fx-background-insets: 0, 1 1 2.5 1";
                //button.setStyle("-fx-font-weight: bold; -fx-background-color: black, white; -fx-background-radius: 0; -fx-background-insets: 0, 1 1 2.5 1;");
            }
        } else if (y == 2 || y == 5) {
            return "-fx-background-insets: 0, 1 2.5 1 1";
            //button.setStyle("-fx-font-weight: bold; -fx-background-color: black, white; -fx-background-radius: 0; -fx-background-insets: 0, 1 2.5 1 1;");
        } else if (y == 3 || y == 6) {
            return "-fx-background-insets: 0, 1 1 1 2.5";
            //button.setStyle("-fx-font-weight: bold; -fx-background-color: black, white; -fx-background-radius: 0; -fx-background-insets: 0, 1 1 1 2.5;");
        } else {
            return "-fx-background-insets: 0, 1 1 1 1";
        }
    }
    
    
    //Buttons
    
    /**
     * Hakee ruudun napin rivin ja kolumnin perusteella
     * @param sudokuGrid
     * @param row
     * @param column
     * @return nappi kyseisessä ruudussa
     */
    
    public Node getNodeByRowColumn (GridPane sudokuGrid, int row, int column) {
        Node button = null;
        ObservableList<Node> childrens = sudokuGrid.getChildren();
        for (Node node : childrens) {
            if(sudokuGrid.getRowIndex(node) == row && sudokuGrid.getColumnIndex(node) == column) {
                button = node;
                break;
            }
        }
        return button;
    }
    
    /**
     * Luo numerollisen nappi haluttuun ruutuun ruudukossa
     * @param sudokuGrid
     * @param id         ruudun id eli järjestysnumero 
     * @param column
     * @param row
     * @param number     numero, joka halutaan nappiin
     * @return nappi, jossa haluttu id ja numero
     */
    
    public Button createButton(GridPane sudokuGrid, int id, int column, int row, int number) {
        Button button = new Button("" + number);
        String border = setBorder(column, row);
        button.setStyle("-fx-background-color: black, white; -fx-background-radius: 0;" + border);
        button.setPrefSize(30, 30);
        button.setMinSize(30, 30);
        button.setMaxSize(30, 30);
        button.setId("" + id);
        sudokuGrid.add(button, column, row);
        selectField(sudokuGrid, button);
        return button;
    }
    
    /**
     * Luo tyhjän napin haluttuun ruutuun ruudukossa
     * @param sudokuGrid
     * @param id        ruudun järjestysnumero
     * @param column
     * @param row 
     */
    
    public Button createEmptyButton(GridPane sudokuGrid, int id, int column, int row) {
        Button button = new Button("  ");
        String border = setBorder(column, row);
        button.setStyle("-fx-background-color: black, white; -fx-background-radius: 0;" + border);        button.setPrefSize(30, 30);
        button.setMinSize(30, 30);
        button.setMaxSize(30, 30);
        button.setId("" + id);
        sudokuGrid.add(button, column, row);
        selectField(sudokuGrid, button);
        return button;
    }  
        
    /**
     * Luodaan valittuun ruutuun nappi, jossa pieni muistiinpanonumero
     * @param sudokuGrid
     * @param id        ruudun järjestysnumero
     * @param column
     * @param row
     * @param number 
     */
    
    public void createNoteButton(GridPane sudokuGrid, int id, int column, int row) {
        String notes = game.getNotesOnField(id);
        Button button = new Button(notes);
        button.setPrefSize(30, 30);
        button.setMinSize(30, 30);
        button.setMaxSize(30, 30);
        String border = setBorder(column, row);
        button.setStyle("-fx-background-color: black, white; -fx-background-radius: 0; -fx-font-size: 5;" + border);
        button.setId("" + id);
        sudokuGrid.add(button, column, row);
        selectField(sudokuGrid, button);
    }

     /**
     * Luo 9 nappia, joissa numerot 1-9
     * @param window
     * @param sudokuGrid   
     * @param numbersBox   asettelu, johon napit lisätään näkymässä
     */
    
    public void createNumberButtons(Stage window, GridPane sudokuGrid, HBox numbersBox) {
        for (int x=1; x<=9; x++) {
            Button button = new Button("" + x);
            button.setScaleX(2);
            button.setScaleY(2);
            numbersBox.getChildren().add(button);
            pushNumberButton(window, sudokuGrid, button, x);
        }
    }  
    
    //Button activity
    
    /**
     * Halutulle napille luodaan toiminnallisuus, joka nappia painaessa vaihtaa näkymän halutuksi
     * @param button    nappi, jolle toiminnallisuus asetetaan
     * @param window   
     * @param scene     näkymä, johon napin painallus vaihtaa 
     */
    
    public void pushButton(Button button, Stage window, Scene scene) {
        button.setOnAction((event) -> {
           window.setScene(scene); 
        });
    }
    
    /**
     * Luodaan toiminnallisuus pelin aikaisen valikko -napin painallukselle, jossa luodaan pelivalikkonäkymä ja vaihdetaan tähän nä
     * @param gameMenuButton    luodun pelin pelivalikkonappula
     * @param window 
     */
    
    public void pushGameMenuButton(Button gameMenuButton, Stage window) {
        gameMenuButton.setOnAction((event) -> {
            createGameMenu(window);
            window.setScene(gameMenuScene);
        });
    }
    
    /**
     * Luodaan toiminnallisuus, kun uusi peli -painiketta painetaan, jossa luodaan uusi peli ja asetetaan uusi pelinäkymä
     * @param window
     * @param button 
     */
    
    public void pushNewGameButton(Stage window, Button button) { 
        button.setOnAction((event) -> {
            this.game.createGame();
            createNewGame(window);
            window.setScene(gameScene);
        });
    }
    
    /**
     * Luodaan toiminnallisuus, jossa sudokun ruutua painaessa asetetaan valittu ruutu oliomuuttujaan
     * @param button   painike valitussa ruudussa
     */
    
    public void selectField(GridPane sudokuGrid, Button button) {
        int id = Integer.valueOf(button.getId());
        int column = (id - 1) % 9;
        int row = (id - column) / 9;
        if (game.checkIfOriginalNumber(row, column) == false) {
            button.setOnAction((event)-> {
                if (selectedButton != null) {
                    int previousRow = game.getSelectedRow();
                    int previousCol = game.getSelectedColumn();
                    int previousId = game.getSelectedField();
                    int previousNumber = game.getNumberOnField(previousCol, previousRow);
                    selectedButton.setStyle("-fx--background-color: white; -fx-border-color: black");
                    sudokuGrid.getChildren().remove(getNodeByRowColumn(sudokuGrid, previousRow, previousCol));
                    if (previousNumber == 0) {
                        createEmptyButton(sudokuGrid, previousId, previousCol, previousRow);
                    } else if (previousNumber > 9) {
                        createNoteButton(sudokuGrid, previousId, previousCol, previousRow);
                    } else {
                        createButton(sudokuGrid, previousId, previousCol, previousRow, previousNumber);

                    }
                }
                String size = "";
                if (game.getNumberOnField(column, row) == 10) {
                    size = "-fx-font-size: 5";
                }
                button.setStyle("-fx--background-color: gray; -fx-border-color: blue;" + size);
                selectedButton = button;
                game.setSelectedField(id);
            });
        }
    }
    
    /**
     * Luodaan toiminnallisuus, jossa painaessa numeronvalintapainikietta, valittuun ruutuun asetetaan kyseinen numero
     * @param window 
     * @param sudokuGrid
     * @param button        Numeropainike
     * @param number        Painikkeen numero
     */
    
    public void pushNumberButton(Stage window, GridPane sudokuGrid, Button button, int number) {
        button.setOnAction((event)-> {
            //check selected field
            int id = game.getSelectedField();
            int row = game.getSelectedRow();
            int column = game.getSelectedColumn();
            //check if number is set by game, if not add number
            if (game.checkIfOriginalNumber(row, column) == false) {
                sudokuGrid.getChildren().remove(getNodeByRowColumn(sudokuGrid, row, column));
                if (notesOn) {
                    game.addNote(id, number);
                    game.addToGame(id, row, column, 10);
                    createNoteButton(sudokuGrid, id, column, row);
                } else {
                    createButton(sudokuGrid, id, column, row, number);
                    game.addToGame(id, row, column, number);
                }
            }
            boolean win = game.checkIfDone();
            if (win == true) {
                endTime = nanoTime();
                long elapsedTime = (endTime - startTime) / 1000000000;
                duration = (int) elapsedTime;
                try {
                    times.saveTime(duration);
                } catch (Exception ex) {
                    System.out.println("Error: " + ex);;
                }
                createWinScene(window, sudokuGrid);
                window.setScene(winScene);
            }
        });
    }
    
    /**
     * Luodaan toiminnallisuus, jossa painaessa poista numero -painiketta luodaan uusi tyhjä ruutu ja poistetaan numero ruutu valitusta ruudusta
     * @param eraseButton   poista numero -painike
     * @param sudokuGrid 
     */
    
    public void pushEraseButton(Button eraseButton, GridPane sudokuGrid) {
        eraseButton.setOnAction((event)-> {
            int id = game.getSelectedField();
            int row = game.getSelectedRow();
            int column = game.getSelectedColumn();
            if (game.checkIfOriginalNumber(row, column) == false) {
                sudokuGrid.getChildren().remove(getNodeByRowColumn(sudokuGrid,row, column));
                game.addToGame(id, row, column, 0);
                createEmptyButton(sudokuGrid, id, column, row);
            }
        });
    }
    
    /**
     * Luodaan toiminnallisuus, jossa painaessa Muistiinpanot-painike pohjaan, notesOn-muuttuja on true
     * @param button 
     */
    
    public void pushNoteButton(ToggleButton button) {
        button.setOnAction(e -> {
            if (button.isSelected()) {
                notesOn = true;
            } else {
                notesOn = false;
            }
        });
    }
    
    /**
     * Luo String-muuttujan, jossa aika tunteina, minuutteina ja sekunteina
     * @return aika tunteina, minuutteina ja sekunteina
     */
    
    public String setTime(int time) {
        int hours = time / 3600;
        int remainder = (int) time - hours * 3600;
        int minutes = remainder / 60;
        remainder = remainder - minutes * 60;
        int seconds = remainder;
        String hour = "00";
        String minute = "00";
        String second = "00";
        if (seconds > 0 && seconds < 10) {
            second = "0" + seconds;
        }
        if (seconds >= 10) {
            second = "" + seconds;
        }
        if (minutes > 0 && minutes < 10) {
            minute = "0" + minutes;
        }
        if (minutes >= 10) {
            minute = "" + minutes;
        }
        if (hours > 0 && hours < 10) {
            hour = "0" + hours;
        }
        if (hours >= 10) {
            hour = "" + hours;
        }
        if (hours > 0) {
            return hour + ":" + minute + ":" + second;
        } else {
            return minute + ":" + second;
        }
    }
}