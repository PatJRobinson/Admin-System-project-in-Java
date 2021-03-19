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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import dataClasses.Match;
import dataClasses.Fixtures;
import dataClasses.Team;
import dataClasses.TeamsList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author paddy
 */
public class AdminPage{
    
    TeamsList teamList;
    Fixtures fixtures;
    Login loginPage;
    Stage adminStage = new Stage();
    Stage teamSelectForNewPlayer = new Stage();
    TextField playerName = new TextField();
    TextField teamName = new TextField();
    
    Stage warning = new Stage();
    Text error = new Text();
    Button btGoBack = new Button("Go Back");


    
    //constructor
    public AdminPage(Login loginPage) {
        this.loginPage = loginPage;
        this.teamList = new TeamsList();
        this.fixtures = new Fixtures();
        
                //--------------------------------TEST DATA-------------------------------------------//
                
        teamList.newTeam("filton");
        teamList.getTeam("filton").addPlayer("alex");
        teamList.getTeam("filton").addPlayer("brian");   
        
        teamList.newTeam("uwe");        
        teamList.getTeam("uwe").addPlayer("jin");
        teamList.getTeam("uwe").addPlayer("julia"); 
        teamList.getTeam("uwe").addPlayer("stewart");   
        
        teamList.newTeam("kcc");        
        teamList.getTeam("kcc").addPlayer("chris"); 
        teamList.getTeam("kcc").addPlayer("ryan");     
        
        teamList.newTeam("page");  
        teamList.getTeam("page").addPlayer("peter"); 
        teamList.getTeam("page").addPlayer("phil");
        
        fixtures.createFixtures(this.teamList.getTeamsList());
        
        fixtures.createMatch(teamList.getTeam("filton"), teamList.getTeam("uwe"));
        
        Match match = fixtures.getMatch(teamList.getTeam("filton"), teamList.getTeam("uwe"));
        String[] scores1 = {"11:2", "3:11", "11:5"};
        match.getSingleSet(0).setGameScores(scores1);
        match.getSingleSet(0).setPlayers(match.getHomeTeam().getPlayer("alex"),
                match.getAwayTeam().getPlayer("jin"));
                
        String[] scores2 = {"1:11", "5:11", "11:6"};
        match.getSingleSet(1).setGameScores(scores2);
        match.getSingleSet(1).setPlayers(match.getHomeTeam().getPlayer("alex"),
                  match.getAwayTeam().getPlayer("julia"));
                        
        String[] scores3 = {"11:9", "11:1", "11:1"};
        match.getSingleSet(2).setGameScores(scores3);
        match.getSingleSet(2).setPlayers(match.getHomeTeam().getPlayer("brian"),
                 match.getAwayTeam().getPlayer("jin"));
                
        String[] scores4 = {"11:2", "3:11", "11:5"};
        match.getSingleSet(3).setGameScores(scores4);
        match.getSingleSet(3).setPlayers(match.getHomeTeam().getPlayer("brian"),
                match.getAwayTeam().getPlayer("julia"));
        
        String[] scores5 = {"0:11", "1:11", "11:5"};
        match.getDoubleSet().setGameScores(scores5);
        

        match.calculateMatchWin();
        
        fixtures.createMatch(teamList.getTeam("uwe"), teamList.getTeam("page"));
        
        Match match1 = fixtures.getMatch(teamList.getTeam("uwe"), teamList.getTeam("page"));

        match1.getSingleSet(0).setGameScores(scores1);
        match1.getSingleSet(0).setPlayers(match1.getHomeTeam().getPlayer("jin"),
                match1.getAwayTeam().getPlayer("peter"));
                

        match1.getSingleSet(1).setGameScores(scores2);
        match1.getSingleSet(1).setPlayers(match1.getHomeTeam().getPlayer("jin"),
                  match1.getAwayTeam().getPlayer("phil"));
                        

        match1.getSingleSet(2).setGameScores(scores3);
        match1.getSingleSet(2).setPlayers(match1.getHomeTeam().getPlayer("julia"),
                 match1.getAwayTeam().getPlayer("peter"));
                

        match1.getSingleSet(3).setGameScores(scores4);
        match1.getSingleSet(3).setPlayers(match1.getHomeTeam().getPlayer("julia"),
                match1.getAwayTeam().getPlayer("phil"));
        

        match1.getDoubleSet().setGameScores(scores5);
        

        match1.calculateMatchWin();


        
    }
        

    public void addTeam(String name) {
        
        //exception handling
        boolean duplicate = teamList.alreadyExists(name);
        if (duplicate) {
            
            error.setText("There is already a team by that name");
            GridPane warningGrid = new GridPane();
            warningGrid.add(error, 0, 0);
            warningGrid.add(btGoBack, 0, 1);
            warningGrid.setVgap(100);
            warningGrid.setHgap(140);
            Scene warningWindow = new Scene(warningGrid, 300, 300);
            warning.setTitle("Error");
            warning.setScene(warningWindow);
            warning.show();    
            
            btGoBack.setOnAction(e ->exception3());
            
            
        } else {
            //create new teams list if none exists
            if (teamList == null) {
                TeamsList newTeamList = new TeamsList();
                teamList = newTeamList;
                System.out.println("created new team list");
            }



            teamList.newTeam(name);
            teamName.setText("");
        }
    }

    public void addPlayer(String name, String team) {

        boolean duplicate = teamList.getTeam(team).alreadyExists(name);
        if (duplicate) {
            
            error.setText("There is already a player by that name in this team");
            GridPane warningGrid = new GridPane();
            warningGrid.add(error, 0, 0);
            warningGrid.add(btGoBack, 0, 1);
            warningGrid.setVgap(100);
            warningGrid.setHgap(140);
            Scene warningWindow = new Scene(warningGrid, 300, 300);
            warning.setTitle("Error");
            warning.setScene(warningWindow);
            warning.show();    
            
            btGoBack.setOnAction(e ->exception2());
            
            
        } else {
            teamList.getTeam(team).addPlayer(name);

            //close UI window
            closeWindow(teamSelectForNewPlayer);
            playerName.setText("");
        }
    }
    
    public void exception2() {
        warning.close();
        teamSelectForNewPlayer.close();
        playerName.setText("");
    }
    
    public void exception3() {
        warning.close();
        teamName.setText("");
    }

        public void closeWindow(Stage window) {
        
        window.close();
    }
    
    public void generateTeamStats() {


        // reset every team's stats - we are going to count all of the sets
        //
        teamList.resetAllStats();

        fixtures.updateTeamStats();

    }

    //@Override // Override the start method in the Application class
    public void start() {
        // Create UI

        
        GridPane adminPlayerWindow = new GridPane();
        GridPane adminTeamWindow = new GridPane();
        GridPane generateWindow = new GridPane();

        Button switchUser = new Button("Log out");
        Button scoreSheet = new Button("Add/Modify Score Sheet");
        Button viewPage = new Button("View match info, team stats and fixtures");
        
        adminTeamWindow.add(switchUser, 1, 1);
        adminTeamWindow.add(viewPage, 2, 1);
        adminTeamWindow.add(scoreSheet, 3, 1);
        
        StackPane columnPane = new StackPane();
        columnPane.setMinWidth(250);	
        adminTeamWindow.add(columnPane,2, 0);
  
        
        Button btAddTeam = new Button("Add Team");
        Text enterTeam = new Text("Enter a new team:");
        
        adminTeamWindow.add(enterTeam, 1, 4);
        adminTeamWindow.add(teamName, 2, 4);
        adminTeamWindow.setHalignment(teamName, HPos.CENTER);
        adminTeamWindow.add(btAddTeam, 3, 5);
        adminTeamWindow.setHalignment(btAddTeam, HPos.CENTER);
        
  
        Text enterPlayer = new Text("Enter the name of the new player:");

        Button btAddPlayer = new Button("Add Player");
        
        adminPlayerWindow.add(enterPlayer, 1, 3);
        adminPlayerWindow.add(playerName, 2, 3);
        adminPlayerWindow.setHalignment(playerName, HPos.CENTER);
        adminPlayerWindow.add(btAddPlayer, 3, 4);
        adminPlayerWindow.setHalignment(btAddPlayer, HPos.CENTER);
        
        StackPane columnPane2 = new StackPane();
        columnPane2.setMinWidth(167);
        
        StackPane columnPane3 = new StackPane();
        columnPane3.setMinWidth(140);
        
        adminPlayerWindow.add(columnPane2,2, 0);
        adminPlayerWindow.add(columnPane3,3, 0);

        
        Text generateText = new Text("This will generate a match betwen each team.");
        Text generateWarningText = new Text("Warning: all previous match information will be deleted");
        Button btGenerateFixtures = new Button("Generate fixtures");
       

        generateWindow.add(generateText, 1, 1);
        generateWindow.add(generateWarningText, 1, 2);
        generateWindow.add(btGenerateFixtures, 3, 3);
        generateWindow.setHalignment(btGenerateFixtures, HPos.CENTER);
        
        Text statsText = new Text("This will generate stats for all of the teams.");
        Text statsWarningText = new Text("Warning: ");
        Button btGenerateStats = new Button("Generate Team Stats");
        
        generateWindow.add(new Text(" "), 1, 5);
        generateWindow.add(new Text(" "), 1, 6);
        generateWindow.add(statsText, 1, 7);
        generateWindow.add(statsWarningText, 1, 8);

        generateWindow.add(new Text("               "), 2, 9);
        generateWindow.add(btGenerateStats, 3, 9);
        generateWindow.setHalignment(btGenerateStats, HPos.CENTER);
        generateWindow.add(new Text(" "), 1, 10);
        generateWindow.add(new Text(" "), 1, 11);
        

        
        adminPlayerWindow.setVgap(25);
        adminPlayerWindow.setHgap(30);
        adminTeamWindow.setVgap(25);
        adminTeamWindow.setHgap(30);
        generateWindow.setHgap(30);
        generateWindow.setVgap(10);
 
        BorderPane border = new BorderPane();

        //formatting the 3 grid panes to add to scene
        border.setTop(adminTeamWindow);
        border.setLeft(adminPlayerWindow);
        border.setBottom(generateWindow);
  
        // Create a scene and place it in the stage
        Scene scene = new Scene(border, 600, 700 );

        
        adminStage.setTitle("Admin Window"); // Set title
        adminStage.setScene(scene); // Place the scene in the stage
        adminStage.show(); // Display the stage
        
        // Process events
        switchUser.setOnAction(e -> switchUser());
        viewPage.setOnAction(e -> showViewPage());
        scoreSheet.setOnAction(e -> showScorePage());
        btAddPlayer.setOnAction(e ->newPlayer(playerName.getText()));
        btAddTeam.setOnAction(e ->addTeam(teamName.getText()));
        btGenerateStats.setOnAction(e -> generateTeamStats());
        btGenerateFixtures.setOnAction(e -> fixtures.createFixtures(teamList.getTeamsList()));
        
        
        //start the timer
        Runnable runnable = ()-> {
            try{
                while(true) {
                    TimeUnit.SECONDS.sleep(3);
                    this.teamStatsTimerStart();
                }
            } catch(InterruptedException e) {
                System.out.println(e);
            }
        };
        
        Thread teamstatstimer = new Thread(runnable);
        teamstatstimer.setDaemon(true);
        teamstatstimer.start();
    }   
    
    public void teamStatsTimerStart() {
        System.out.println("Automatically generated team stats");
        this.generateTeamStats();
    }
    
    public void switchUser() {
        adminStage.close();
        loginPage.userLogin();
    }
    
    public void showViewPage() {
        AdminViewPage view = new AdminViewPage();
        view.start(teamList, fixtures);
    }
    
    public void showScorePage() {
        ScorePage scores = new ScorePage(teamList, fixtures);
        scores.start();
    }
    
    //This method is for the viewer class to be able to access teamstats data
    public TeamsList getTeamList() {
        return teamList;
    }
    
    //This method is for the viewer class to be able to access fixtures data
    public Fixtures getFixtures() {
        return fixtures;
    }

    public void newPlayer(String playerName) {

        
        //-----------------------------------------------------page 2 =-------------------------------------------//
        
        ObservableList<String> options = FXCollections.observableArrayList(teamList.getTeamNameList());
        
        ComboBox<String> teamOptions = new ComboBox(options);
        Button btCancel2 = new Button("Cancel");
        Button btSubmit = new Button("Submit");
        
        GridPane selectGame = new GridPane();  
        selectGame.add(new Text("Select player's team:"), 1, 1); // only show games which have been played
        selectGame.add(teamOptions,1, 2);
        
        selectGame.setHalignment(teamOptions, HPos.CENTER);
        
        GridPane selectBack = new GridPane();
        selectBack.add(btCancel2, 1, 1);
        selectBack.add(btSubmit, 2, 1);
        
        selectGame.setHgap(20);
        selectGame.setVgap(15);
        
        selectBack.setHgap(30);
        selectBack.setVgap(40);
        
        BorderPane gameSelectBorder = new BorderPane();
        gameSelectBorder.setTop(selectGame);
        gameSelectBorder.setCenter(selectBack);

        // either games which have been played (for modify) or games which haven't been played (for add)
        Scene gameSelectWindow = new Scene(gameSelectBorder, 200, 170);
        
        
        teamSelectForNewPlayer.setTitle("Select Team");
        teamSelectForNewPlayer.setScene(gameSelectWindow);
        teamSelectForNewPlayer.show();
        
        btCancel2.setOnAction(e -> teamSelectForNewPlayer.close());
        btSubmit.setOnAction(e -> addPlayer(playerName, teamOptions.getValue()));
        
    }
     
}
