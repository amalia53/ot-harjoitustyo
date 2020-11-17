
package sudoku.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SudokuUi extends Application {
    
    public int selectedNumber;
    
    private Scene menuScene;
    private Scene gameScene;
    private Scene gameMenuScene;
    private Scene winScene;
    
    
    // Menu
    private BorderPane menuPane;
    private Label menuLabel;
    private Button newGameButton;
    private Button resumeButton;
    private VBox menuBox;
    
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
        sudoku.domain.ButtonActivity.pushButton(newGameButton, window, gameScene);
        
        //Game Scene
        gamePane = new BorderPane();
        sudokuGrid = new GridPane();
        sudokuGrid.setPadding(new Insets(10));
        sudokuGrid.setHgap(3);
        sudokuGrid.setVgap(3);
        for (int x=0; x<9; x++) {
            for (int y=0; y<9; y++) {
                Button button = new Button(" ");
                sudokuGrid.add(button, x, y);
            }
        }
        sudokuGrid.setScaleX(2);
        sudokuGrid.setScaleY(2);
        numbersBox = new HBox();
        for (int x=1; x<=9; x++) {
            Button button = new Button("" + x);
            button.setScaleX(2);
            button.setScaleY(2);
            numbersBox.getChildren().add(button);
            sudoku.domain.ButtonActivity.pushNumberButton(button, x);
        }
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
        
        //Game Menu Scene
        
        //sudoku.domain.ButtonActivity.pushButton(resumeButton, window, );
        sudoku.domain.ButtonActivity.pushButton(newGameButton, window, gameScene);
        
        
        //Go to menu
        sudoku.domain.ButtonActivity.pushButton(gameMenuButton, window, menuScene);
        
        
        
        
        
        window.setScene(menuScene);
        window.show();
    }
    
    
    
    
}
