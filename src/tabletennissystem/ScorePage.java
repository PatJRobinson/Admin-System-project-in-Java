package tabletennissystem;

import dataClasses.TeamsList;
import dataClasses.Player;
import dataClasses.Match;
import dataClasses.Fixtures;
import dataClasses.SingleSet;
import dataClasses.Team;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScorePage {

	String players[];
	String matchPlayers[][];
        Team MatchTeams;
        TeamsList teamList;
        Fixtures fixtures;    
        
        Stage gameSelect = new Stage();
        Stage scorePageSelect = new Stage();
        Stage scores = new Stage();
        
        
        Stage warning = new Stage();
        Text error = new Text();
        Button btGoBack = new Button("Go Back");
        
        GridPane warningGrid = new GridPane();

        
        TextField gameScores[][] = new TextField[4][3];
        TextField doubleScore[] = new TextField[3];
        
        ComboBox<String> team1Player1 = new ComboBox();
        ComboBox<String> team1Player2 = new ComboBox();
        ComboBox<String> team2Player1 = new ComboBox();
        ComboBox<String> team2Player2 = new ComboBox();
        
        public ScorePage(TeamsList teams) {
            teamList = teams;
        }
        
        public ScorePage(TeamsList newTeamList, Fixtures newFixtures) {
            teamList = newTeamList;
            // at a given time, this class will be considering 2 players from 2 teams
            matchPlayers = new String[2][2];
            //
            fixtures = newFixtures;
        }
	
	public void selectPlayers(int team) {
		
	}
	
	public String selectMatchPlayers(Player players[]) {
		
		String s = null;
		return s;
	}
        

        
        public void start() {
            Button btAdd = new Button("Create Score Sheet");
        Button btModify = new Button("Modify Score Sheet");
        GridPane addModify = new GridPane();
        addModify.add(btAdd, 1, 3);
        addModify.add(btModify, 2, 3);

      
        
        
        
        ObservableList<String> options = FXCollections.observableArrayList(teamList.getTeamNameList());
        

        ComboBox<String> teams1 = new ComboBox(options);
        ComboBox<String> teams2 = new ComboBox(options);    // 
        Button btCancel = new Button("Cancel");

        
        addModify.add(new Text("Home Team"), 1, 1);
        addModify.add(new Text("Away Team"), 1, 2); // remove selected team from optionslist
        addModify.add(teams1, 2, 1);
        addModify.add(teams2, 2, 2);
        addModify.add(btCancel, 1, 4); 

    
        Scene addModifyWindow = new Scene(addModify, 300, 250);
        
        addModify.setHgap(20);
        addModify.setVgap(25);
        

        scorePageSelect.setTitle("Score Page");
        scorePageSelect.setScene(addModifyWindow);
        scorePageSelect.show();
        
        btCancel.setOnAction(e -> closeWindow(scorePageSelect));
        btAdd.setOnAction(e -> createScoreSheet(true, teams1.getValue(), teams2.getValue()));
        btModify.setOnAction(e -> createScoreSheet(false, teams1.getValue(), teams2.getValue()));
        
        

        }
        
    public void closeWindow(Stage window) {
        
        window.close();
    }
    
    

    
    public void createScoreSheet(boolean addModify, String homeTeam, String awayTeam) {


        
        gameSelect.close();
        scorePageSelect.close();
               
        Team team1 = teamList.getTeam(homeTeam);
        Team team2 = teamList.getTeam(awayTeam);
        
        //System.out.println("DEBUG CreateScoreSheet " + homeTeam + awayTeam);
                
        ObservableList<String> team1Players = FXCollections.observableArrayList(team1.getPlayers());
        ObservableList<String> team2Players = FXCollections.observableArrayList(team2.getPlayers());
        
        String team1Name = new String(team1.getName());
        String team2Name = new String(team2.getName());
                
        BorderPane scoreSheetBorder = new BorderPane();
        
        Button btCancel3 = new Button("Cancel");      
    
        Text enterSingles = new Text("Enter Single Set Winners:");
        Text enterDoubles = new Text("Select Double Set Winner:");
        
        Text teams = new Text("             " + team2.getName() + "\n" + team1.getName());
        
        
        //player selection gui
        team1Player1.setPromptText("(select player)");
        team1Player2.setPromptText("(select player)");
        team2Player1.setPromptText("(select player)");
        team2Player2.setPromptText("(select player)");
        
        team1Player1.setItems(team1Players);
        team1Player2.setItems(team1Players);
        team2Player1.setItems(team2Players);
        team2Player2.setItems(team2Players);
        
        // for displaying game score input gui
        GridPane games = new GridPane();
        
        //looping through each single set and applying formatting algorithm
        for (int i = 0; i < 4; i++) {
            for (int x = 0; x < 3; x++) {
                    
                gameScores[i][x]= new TextField();
                //gameScores[i][x][y].setPromptText("Score");
                //gameScores[i][x].setItems(FXCollections.observableArrayList(team1.getName(), team2.getName()));
                gameScores[i][x].setMinWidth(35);
                gameScores[i][x].setMaxWidth(37);
                
                if (i%2 == 0) {
                    games.add(gameScores[i][x], x, i);
                    } else {
                    games.add(gameScores[i][x], x + 4 , i - 1);
                }

            }

        }
        
        
        games.add(new Text(" "), 0, 4);

        
        // adding the double set gui
        for (int x = 0; x < 3; x++) {
            
            doubleScore[x] = new TextField();

            doubleScore[x].setMinWidth(35);
            doubleScore[x].setMaxWidth(37);
            
            games.add(doubleScore[x], x, 4);
            
        }

        
        //new grid to be displayed on the left, for team1 player select
        GridPane selectTeam1Players = new GridPane();
        
        selectTeam1Players.add(team1Player1, 0, 0);
        selectTeam1Players.add(team1Player2, 0, 1);
        selectTeam1Players.add(new Text("Enter Double Wins:"), 0, 2);

        //new grid to be displayed on the top, for team2 player select
        GridPane selectTeam2Players = new GridPane();
        
        selectTeam2Players.add(new Text("Enter Single Wins: "), 0, 0);
        selectTeam2Players.add(teams, 0, 1);
        selectTeam2Players.add(team2Player1, 1, 1);
        selectTeam2Players.add(team2Player2, 2, 1);
        
        //grid for displaying submit and cancel buttons, to be added at the bottom of the borderpane
        GridPane cancelSubmit = new GridPane();
        Button btSubmit = new Button("Submit");
        
        cancelSubmit.add(btCancel3, 1, 1);
        cancelSubmit.add(btSubmit, 3, 1);
        cancelSubmit.add(new Text(" "), 1, 2);       
        
        selectTeam2Players.setHgap(75);
        selectTeam2Players.setVgap(5);
        
        selectTeam1Players.setHgap(10);
        selectTeam1Players.setVgap(15);
        
        games.setHgap(15);
        games.setVgap(8);
        games.setTranslateX(30);
        
        cancelSubmit.setHgap(95);
        cancelSubmit.setVgap(15);
        
        scoreSheetBorder.setTop(selectTeam2Players);
        scoreSheetBorder.setCenter(games);
        scoreSheetBorder.setLeft(selectTeam1Players);

        scoreSheetBorder.setBottom(cancelSubmit);
        
        scoreSheetBorder.setLayoutX(30);
        
        Scene scorePageWindow = new Scene(scoreSheetBorder, 600, 250);
        scores.setTitle("Enter Match Scores");
        scores.setScene(scorePageWindow);
        scores.show();
              
        btCancel3.setOnAction(e -> closeWindow(scores));
        btSubmit.setOnAction(e -> setScores(team1, team2));
        
        //Exception handling
        if (addModify && (fixtures.getMatch(teamList.getTeam(homeTeam), teamList.getTeam(awayTeam)).getMatchPlayed())) {
         
            error.setText("That match has already been played, \nPlease go back and click 'Modify' instead");
            warningGrid.add(error, 0, 0);
            warningGrid.add(btGoBack, 0, 1);
            warningGrid.setVgap(100);
            warningGrid.setHgap(140);
            Scene warningWindow = new Scene(warningGrid, 300, 300);
            warning.setTitle("Error");
            warning.setScene(warningWindow);
            warning.show();    
            
            btGoBack.setOnAction(e ->exception1());
        }
        if (!(addModify) && !(fixtures.getMatch(teamList.getTeam(homeTeam), teamList.getTeam(awayTeam)).getMatchPlayed())) {
         
            error.setText("That match has yet to be played, \nPlease go back and click 'Create Score Sheet' instead");
            warningGrid.add(error, 0, 0);
            warningGrid.add(btGoBack, 0, 1);
            warningGrid.setVgap(100);
            warningGrid.setHgap(140);
            Scene warningWindow = new Scene(warningGrid, 300, 300);
            warning.setTitle("Error");
            warning.setScene(warningWindow);
            warning.show();    
            
            btGoBack.setOnAction(e ->exception1());
        }
        
        if (homeTeam.equals(awayTeam)){
            
            error.setText("The teams cannot be the same, please try again");
            warningGrid.add(error, 0, 0);
            warningGrid.add(btGoBack, 0, 1);
            warningGrid.setVgap(100);
            warningGrid.setHgap(140);
            Scene warningWindow = new Scene(warningGrid, 300, 300);
            warning.setTitle("Error");
            warning.setScene(warningWindow);
            warning.show();    
            
            btGoBack.setOnAction(e ->exception1());
        }

    }
    
    public void exception1() {
        scores.close();
        warning.close();
    }
    
    public void setScores(Team homeTeam, Team awayTeam) {

        scores.close();
        

        
        Match match = fixtures.getMatch(homeTeam, awayTeam); //DUFHEF AWAYTEAM
        
               
        String[] arr = new String[3];
        String[] playerSelects = {team1Player1.getValue(), team1Player2.getValue(), team2Player1.getValue(), team2Player2.getValue()};
        int[][] indexes = {{0, 2}, {0, 3}, {1, 2}, {1, 3}};
        
        for (int i = 0; i < 4; i ++) {
            
            for (int x = 0; x < 3; x++) {

                arr[x] = gameScores[i][x].getText();
                
            }

            match.getSingleSet(i).setGameScores(arr);
            System.out.println("DEBUG scorepage :  /" + playerSelects[indexes[i][0]] + "/ /" + awayTeam.getPlayer(playerSelects[indexes[i][1]]));
            System.out.println(homeTeam.getName() + "/ /" + awayTeam.getName());
            match.getSingleSet(i).setPlayers(homeTeam.getPlayer(playerSelects[indexes[i][0]]), 
                                                                    awayTeam.getPlayer(playerSelects[indexes[i][1]]));

        }

        for (int x = 0; x < 3; x++) {
            arr[x] = doubleScore[x].getText();
        }
        match.getDoubleSet().setGameScores(arr);
        match.calculateMatchWin();
        

    }
}
