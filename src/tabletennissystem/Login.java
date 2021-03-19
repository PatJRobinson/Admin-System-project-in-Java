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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author paddy
 */
public class Login extends Application {
    
    ViewPage viewPage;
    AdminPage adminPage;
    ScorePage scorePage;
    
    TextField userName = new TextField();
    TextField password = new TextField();
    Text notRecognized = new Text("\n");
    //global stage
    Stage selectUser = new Stage();
    
    @Override
    public void start(Stage primaryStage) {
        
 
        AdminPage newAdminPage = new AdminPage(this);
        adminPage = newAdminPage;

        ViewPage newViewPage = new ViewPage(this);
        viewPage = newViewPage;      

        userLogin();

    }
    
    public void userLogin() {
        

        Button login = new Button("Submit");
        
        GridPane userLogin = new GridPane();
        userLogin.add(new Text("Username:"), 1, 2);
        userLogin.add(userName, 2, 2);
        userLogin.add(new Text("Password:"), 1, 3);
        userLogin.add(password, 2, 3);
        
        
        GridPane submit = new GridPane();
        submit.add(notRecognized, 1, 0);
        submit.add(login, 1, 1);
        
        
        BorderPane loginBorder = new BorderPane();
        loginBorder.setTop(userLogin);
        loginBorder.setCenter(submit);
        
        userLogin.setHgap(30);
        userLogin.setVgap(15);
        
        submit.setHgap(155);
        submit.setVgap(35);
        
        Scene loginWindow = new Scene(loginBorder, 330, 200);
        

        selectUser.setTitle("Login");
        selectUser.setScene(loginWindow);
        selectUser.show();

        // Process events
        login.setOnAction(e -> checkLogin(userName.getText()));
        
        
    }
    
    
    public void checkLogin(String username) {
                
        if (username.equals("admin")) {
            selectUser.close();
            adminPage.start();
        }
        
        else if (username.equals("viewer")) {
            selectUser.close();
            viewPage.start(adminPage.getTeamList(), adminPage.getFixtures());
        }
        
        else {
            notRecognized.setText("Username not recognized \n please try again");
        }

    }
          
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
