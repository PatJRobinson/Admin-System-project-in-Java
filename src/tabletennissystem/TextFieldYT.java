/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabletennissystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class TextFieldYT extends Application {
 
    @Override
    public void start(Stage stage) {
        VBox pane = new VBox();
        
        Text text = new Text("Hello, world!");
        text.setFont(Font.font("Sans serif", FontWeight.NORMAL, FontPosture.REGULAR, 32));
        text.setFill(Color.RED);
        
        pane.getChildren().add(text);
        
        Button button = new Button("Click me!");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Good job.");
            }
        });
        button.setOnAction(e -> System.out.println("Good job."));
        
        pane.getChildren().add(button);
        
        CheckBox checkBox = new CheckBox("Yes?");
        checkBox.isSelected();
        
        pane.getChildren().add(checkBox);
        
        TextField field = new TextField();
        field.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER) {
                    System.out.println(field.getText());
                }
            }
        });
        
        pane.getChildren().add(field);
        
        stage.setScene(new Scene(pane, 640, 480));
        stage.setTitle("JavaFX 101");
        stage.setResizable(false);
        stage.show();
    }
    
   // public static void main(String[] args) {
   //     launch(args);
   // }
}