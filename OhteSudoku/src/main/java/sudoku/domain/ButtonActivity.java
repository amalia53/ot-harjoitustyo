
package sudoku.domain;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ButtonActivity {
    
    public static void pushButton(Button button, Stage window, Scene scene) {
        button.setOnAction((event) -> {
           window.setScene(scene); 
        });
    }
    
    public static void selectField(Button button) {
        //save selected field
        button.setOnAction((event)-> {
            //
        });
    }
    
    public static void pushNumberButton(Button button, int number) {
        //check selected field
        button.setOnAction((event)-> {
           //add number to selected field
        });
    }
    
    public static void pushEraseButton(Button button, int number) {
        //check selected field
        button.setOnAction((event)-> {
           //Erase selevted field
        });
    }
    

}
