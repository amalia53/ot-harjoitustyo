
package sudoku.ui;

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
    
    //Game
    private BorderPane gamePane;
    private GridPane sudokuGrid;
    private HBox numbersBox;
    private HBox gameOptionsBox;
    private Button eraseButton;
    private Button gameMenuButton;
    
    //Win
    private Label congratsLabel;
    private Button winNewGame;
    private Button winMenu;
    
    @Override
    public void start(Stage window) throws Exception {
        
        //Start Menu Scene
        menuPane = new BorderPane();
        menuBox = new VBox();
        menuLabel = new Label("Valikko");
        newGameButton = new Button("Uusi peli");
        menuBox.getChildren().add(menuLabel);
        menuBox.getChildren().add(newGameButton);
        menuBox.setSpacing(100);
        menuBox.setAlignment(Pos.CENTER);
        menuPane.setCenter(menuBox);
        menuScene = new Scene(menuPane, 800, 900);
        
        //Start a new game
        this.game.createGame();
        createNewGame();
        pushNewGameButton(window, newGameButton);       

        //Game Menu Scene
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
        gameMenuScene = new Scene(gameMenuPane, 800, 900);
        pushButton(resumeButton, window, gameScene);
        //pushNewGameButton(window, gameMenuNewGameButton);

        
        //Game Options
        pushButton(gameMenuButton, window, gameMenuScene);
        pushEraseButton(eraseButton); 
        
        
        
        window.setScene(menuScene);
        window.show();
    }
    
    public void createGrid() {
        for (int x=0; x<9; x++) {
            for (int y=0; y<9; y++) {
                int number = game.getNumberOnField(x, y);
                int ordinal = (y)*9+x+1;
                createButton(ordinal, x, y, number);
            }
        }
    }
    
    //Buttons
    
    public void pushButton(Button button, Stage window, Scene scene) {
        button.setOnAction((event) -> {
           window.setScene(scene); 
        });
    }
    
    public void pushNewGameButton(Stage window, Button button) {
        button.setOnAction((event) -> {
           window.setScene(gameScene);
        });
    }
    
    public void selectField(Button button) {
        int id = Integer.valueOf(button.getId());
        int number = Integer.valueOf(button.getText());
        button.setOnAction((event)-> {
            game.setSelectedNumber(number);
            System.out.println(game.getSelectedNumber());
            game.setSelectedField(id);
        });
    }
    
    public void pushNumberButton(Button button, int number) {
        //check selected field
        button.setOnAction((event)-> {
            int id = game.getSelectedField();
            int row = game.getSelectedRow();
            int column = game.getSelectedColumn();
            System.out.println(game.checkIfOriginalNumber(row, column));
            if (game.checkIfOriginalNumber(row, column) == false) {
                sudokuGrid.getChildren().remove(getNodeByRowColumn(row, column));
                createButton(id, column, row, number);
                game.addToGame(row, column, number);
                boolean correct = game.checkInputNumber(number, row, column);
                System.out.println(correct);
            }
        });
    }
    
    public void pushEraseButton(Button button) {
        //check selected field
        //can't erase numbers set by game
        button.setOnAction((event)-> {
            int id = game.getSelectedField();
            int row = game.getSelectedRow();
            int column = game.getSelectedColumn();
            if (game.checkIfOriginalNumber(row, column) == false) {
                sudokuGrid.getChildren().remove(getNodeByRowColumn(row, column));
                createEmptyButton(id, column, row);
            }
        });
    }
    
    public Node getNodeByRowColumn (int row, int column) {
        Node result = null;
        ObservableList<Node> childrens = sudokuGrid.getChildren();

        for (Node node : childrens) {
            if(sudokuGrid.getRowIndex(node) == row && sudokuGrid.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }
    

    public void createButton(int id, int column, int row, int number) {
        Button button = new Button("" + number);
        button.setId("" + id);
        sudokuGrid.add(button, column, row);
        selectField(button);
    }
    
    public void createEmptyButton(int id, int column, int row) {
        Button newButton = new Button("0");
        newButton.setId("" + id);
        sudokuGrid.add(newButton, column, row);
        selectField(newButton);
    }
    
    
    public void createNumberButtons() {
        for (int x=1; x<=9; x++) {
            Button button = new Button("" + x);
            button.setScaleX(2);
            button.setScaleY(2);
            numbersBox.getChildren().add(button);
            pushNumberButton(button, x);
        }
    }
        
    public void createNewGame() {
        gamePane = new BorderPane();
        sudokuGrid = new GridPane();
        sudokuGrid.setPadding(new Insets(10));
        sudokuGrid.setHgap(3);
        sudokuGrid.setVgap(3);
        createGrid();     
        sudokuGrid.setScaleX(2);
        sudokuGrid.setScaleY(2);
        numbersBox = new HBox();
        createNumberButtons();
        
        numbersBox.setSpacing(40);
        gameOptionsBox = new HBox();
        eraseButton = new Button("Poista");
        gameMenuButton = new Button("Valikko");
        gameOptionsBox.getChildren().addAll(eraseButton, gameMenuButton);
        gameOptionsBox.setAlignment(Pos.CENTER);
        gameOptionsBox.setSpacing(100);
        numbersBox.setAlignment(Pos.CENTER);
        sudokuGrid.setAlignment(Pos.CENTER);        
        VBox gameBox = new VBox();
        gameBox.getChildren().addAll(sudokuGrid, numbersBox);
        gameBox.setAlignment(Pos.CENTER);
        gameBox.setSpacing(180);
        gamePane.setBottom(gameOptionsBox);
        gamePane.setCenter(gameBox);
        gameScene = new Scene(gamePane, 800, 900);
    }
    
}
