
package sudoku.ui;

import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SudokuUi extends Application {
    
    sudoku.domain.SudokuGame game = new sudoku.domain.SudokuGame();
    
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
    
    @Override
    public void start(Stage window) throws Exception {
        
        //Start Menu Scene
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
        menuScene = new Scene(menuPane, 800, 900);
        
        //Start a new game     
        pushNewGameButton(window, newGameButton); 
        
        
        window.setScene(menuScene);
        window.show();
    }
    
    public void createGrid(GridPane sudokuGrid) {
        for (int x=0; x<9; x++) {
            for (int y=0; y<9; y++) {
                int number = game.getNumberOnField(x, y);
                int id = (y)*9+x+1;
                if (number == 0) {
                    createEmptyButton(sudokuGrid, id, x, y);
                } else {
                    Button button = createButton(sudokuGrid, id, x, y, number);
                    button.setStyle("-fx-font-weight: bold; -fx-background-color: white; -fx-border-color: black");
                }
            }
        }
    }
    
    //Buttons
    
    public void pushButton(Button button, Stage window, Scene scene) {
        button.setOnAction((event) -> {
           window.setScene(scene); 
        });
    }
    
    public void pushGameMenuButton(Button gameMenuButton, Stage window) {
        gameMenuButton.setOnAction((event) -> {
            createGameMenu(window);
            window.setScene(gameMenuScene);
        });
    }
    
    public void pushNewGameButton(Stage window, Button button) { 
        button.setOnAction((event) -> {
            this.game.createGame();
            createNewGame(window);
            window.setScene(gameScene);
        });
    }
    
    public void selectField(Button button) {
        int id = Integer.valueOf(button.getId());
        button.setOnAction((event)-> {
            game.setSelectedField(id);
        });
    }
    
    public void pushNumberButton(Stage window, GridPane sudokuGrid, Button button, int number) {
        button.setOnAction((event)-> {
            //check selected field
            int id = game.getSelectedField();
            int row = game.getSelectedRow();
            int column = game.getSelectedColumn();
            //check if number is set by game, if not add number
            if (game.checkIfOriginalNumber(row, column) == false) {
                sudokuGrid.getChildren().remove(getNodeByRowColumn(sudokuGrid, row, column));
                createButton(sudokuGrid, id, column, row, number);
                game.addToGame(row, column, number);
            }
            boolean win = game.checkIfDone();
            if (win == true) {
                createWinScene(window, sudokuGrid);
                window.setScene(winScene);
            }
        });
    }
    
    public void pushEraseButton(Button eraseButton, GridPane sudokuGrid) {
        eraseButton.setOnAction((event)-> {
            int id = game.getSelectedField();
            int row = game.getSelectedRow();
            int column = game.getSelectedColumn();
            if (game.checkIfOriginalNumber(row, column) == false) {
                sudokuGrid.getChildren().remove(getNodeByRowColumn(sudokuGrid,row, column));
                game.addToGame(row, column, 0);
                createEmptyButton(sudokuGrid, id, column, row);
            }
        });
    }
    
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
    

    public Button createButton(GridPane sudokuGrid, int id, int column, int row, int number) {
        Button button = new Button("" + number);
        button.setStyle("-fx-background-color: white; -fx-border-color: black");
        button.setId("" + id);
        sudokuGrid.add(button, column, row);
        selectField(button);
        return button;
        
    }
    
    public void createEmptyButton(GridPane sudokuGrid, int id, int column, int row) {
        Button newButton = new Button("  ");
        newButton.setStyle("-fx-background-color: white; -fx-border-color: black");
        newButton.setId("" + id);
        sudokuGrid.add(newButton, column, row);
        selectField(newButton);
    }
    
    
    public void createNumberButtons(Stage window, GridPane sudokuGrid, HBox numbersBox) {
        for (int x=1; x<=9; x++) {
            Button button = new Button("" + x);
            button.setScaleX(2);
            button.setScaleY(2);
            numbersBox.getChildren().add(button);
            pushNumberButton(window, sudokuGrid, button, x);
        }
    }
    
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
        gameMenuScene = new Scene(gameMenuPane, 800, 900);
    }
    
    public void createWinScene(Stage window, GridPane sudokuGrid) {
        winPane = new BorderPane();
        winBox = new VBox();
        winLabel = new Label("Voitit pelin! Onneksi olkoon!");
        winNewGame = new Button("Uusi peli");
        winMenu = new Button("Valikko");
        winMenu.setScaleX(1.8);
        winMenu.setScaleY(1.8);
        winNewGame.setScaleX(1.8);
        winNewGame.setScaleY(1.8);
        HBox winLabelBox = new HBox();
        winLabelBox.getChildren().add(winLabel);
        winBox.getChildren().add(winNewGame);
        winBox.getChildren().add(winMenu);
        winPane.setTop(winLabelBox);
        winLabelBox.setAlignment(Pos.CENTER);
        winLabelBox.setPadding(new Insets(50, 50, 50, 50));
        winLabel.setFont(Font.font("Arial", 40));
        winPane.setCenter(sudokuGrid);
        winPane.setBottom(winBox);
        winBox.setSpacing(50);
        winBox.setPadding(new Insets(70, 70, 70, 70));
        winBox.setAlignment(Pos.CENTER);
        pushNewGameButton(window, winNewGame);
        pushButton(winMenu, window, menuScene);
        winScene = new Scene(winPane, 800, 900);
    }
        
    public void createNewGame(Stage window) {
        BorderPane gamePane = new BorderPane();
        GridPane sudokuGrid = new GridPane();
        //sudokuGrid.setPadding(new Insets(10));
        sudokuGrid.setHgap(3);
        sudokuGrid.setVgap(3);
        createGrid(sudokuGrid);     
        sudokuGrid.setScaleX(1.8);
        sudokuGrid.setScaleY(1.8);
        HBox numbersBox = new HBox();
        createNumberButtons(window, sudokuGrid, numbersBox);
        
        numbersBox.setSpacing(40);
        VBox gameOptionsBox = new VBox();
        Button eraseButton = new Button("Poista");
        eraseButton.setScaleX(1.4);
        eraseButton.setScaleY(1.4);
        Button gameMenuButton = new Button("Valikko");
        gameMenuButton.setScaleX(1.8);
        gameMenuButton.setScaleY(1.8);
        gameOptionsBox.getChildren().addAll(gameMenuButton);
        gameOptionsBox.setPadding(new Insets(0, 0, 50, 0));
        sudokuGrid.setAlignment(Pos.CENTER);     
        numbersBox.setAlignment(Pos.CENTER);
        VBox gameBox = new VBox();
        VBox box = new VBox();
        gameBox.getChildren().addAll(sudokuGrid, numbersBox);
        gameBox.setPadding(new Insets(60, 0, 0, 0));
        box.getChildren().addAll(gameBox, eraseButton);
        gameBox.setAlignment(Pos.BOTTOM_CENTER);
        gameBox.setSpacing(180);
        gamePane.setCenter(box);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(50);
        gamePane.setBottom(gameOptionsBox);
        gameOptionsBox.setAlignment(Pos.CENTER);
        pushEraseButton(eraseButton, sudokuGrid);
        pushGameMenuButton(gameMenuButton, window);
        gameScene = new Scene(gamePane, 800, 900);
    }
    
}
