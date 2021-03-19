package tabletennissystem;

import dataClasses.*;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import java.util.ArrayList;
import java.util.List;

public class ViewPage {
	
    // NO LOCAL DATA - get fixtures from admin page
	//Fixtures fixtures;
    Login loginPage;
    Stage viewWindow = new Stage();
    Button switchUser = new Button("Switch User");
    TextArea tf = new TextArea();
    
    Stage viewScoresSelectMatch = new Stage();
    
        public ViewPage() {
            
        }
        public ViewPage(Login login) {
            this.loginPage = login;
        }
	
	public void viewTeamStats(TeamsList teamList) {
                
                
		tf.setText("Team Stats:");
                tf.appendText("\n");
                
                for (int i = 0; i < teamList.getTeamsList().size(); i++) {
                    Team curTeam = teamList.getTeamsList().get(i);
                    tf.appendText(curTeam.getName() + ":     Matches played: " + curTeam.getTeamStats().getMatchesPlayed() + 
                            "   Matches Won: " + curTeam.getTeamStats().getMatchesWon() + "     Sets Won: "+ curTeam.getTeamStats().getSetsWon() + "\n");
                }
	}
        
        public void viewRanking(TeamsList teamList) {
            
            List<Team> rankedTeams = teamList.rankTeams();
            
            tf.setText(
                                             "Team Rankings:\n");
            for (int i = 0; i < rankedTeams.size(); i++ ) {
                
                tf.appendText(String.format(i + ": " + rankedTeams.get(i).getName()) + ", Matches Won: " + 
                        rankedTeams.get(i).getTeamStats().getMatchesWon() + "\n");
            }
            
            
        }
        
        public void viewMatchScores(Fixtures fixtures, TeamsList teamList) {
            
            tf.setText(String.format("%1$-15s",
                                 "Match Scores:\n"));
            
            Button btSubmit = new Button("Submit");
            Button btCancel = new Button("Cancel");
            Text selectMatch = new Text("Please select a match to view:");
            ComboBox<String> matchOptions = new ComboBox(FXCollections.observableArrayList(fixtures.getPlayedMatches()));
            
            GridPane chooseMatch = new GridPane();
            chooseMatch.add(selectMatch,1, 0);
            chooseMatch.add(matchOptions, 1, 1);
            chooseMatch.add(btCancel, 1, 2);
            chooseMatch.add(btSubmit, 2, 2);
            
            Scene selectMatchWindow = new Scene(chooseMatch, 300, 250);
            
            chooseMatch.setHgap(20);
            chooseMatch.setVgap(25);
            
            viewScoresSelectMatch.setTitle("Select Match");
            viewScoresSelectMatch.setScene(selectMatchWindow);
            viewScoresSelectMatch.show(); 
            
            
            btSubmit.setOnAction(e -> displayMatchScores(matchOptions.getValue(), fixtures, teamList));
            btCancel.setOnAction(e -> closeWindow(viewScoresSelectMatch));
        }
        
        public void closeWindow(Stage window) {
            window.close();
        }
        
        public void displayMatchScores(String matchDescriptor, Fixtures fixtures, TeamsList teamList) {
                    
            String delims = "[ ]";
            String[] tokens = new String[3];
            tokens = matchDescriptor.split(delims);
            

            
            Match match = fixtures.getMatch(teamList.getTeam(tokens[0]), teamList.getTeam(tokens[2]));
            
            closeWindow(viewScoresSelectMatch);
            tf.setText(
                                             "Match Info:\n");

                tf.appendText(String.format("Home Team:" + match.getHomeTeam().getName() + "            " + " Away Team: " + match.getAwayTeam().getName() + "\n"));
                tf.appendText(String.format("Winner: " + match.getWinner().getName() + "\n"));
                tf.appendText(String.format("\n"));
                tf.appendText(String.format("Single Sets: \n"));
                
                for (int i = 0; i < 4; i ++) {
                    String s = match.getSingleSet(i).getPrintInfo();
                    System.out.println(s);
                    tf.appendText(String.format(s));
                }
                
                tf.appendText("\n");
                String s = match.getDoubleSet().getPrintInfo();
                tf.appendText(String.format("Double Set:" + s + " \n"));
                
        }

	public void viewFixtures(Fixtures fixtures) {
            
            tf.setText(String.format(
                                     "Fixtures:\n"));
            tf.appendText("\n");
            for (int i = 0; i < fixtures.getFixtures().size(); i++) {

                Match curMatch = fixtures.getMatchByIndex(i);
                tf.appendText(String.format(curMatch.getMatchInfo() + "\n"));
            }
		
            
	}
        
        public void start(TeamsList teamList, Fixtures fixtures) {
            
                    GridPane viewButtons = new GridPane();
        GridPane textWindow = new GridPane();
        GridPane bottomControls = new GridPane();


        
        tf.setPrefWidth(420);
        tf.setPrefHeight(350);
        
        //tf.setAlignment(Pos.BOTTOM_RIGHT);
        textWindow.add(tf, 2, 1);
        textWindow.setVgap(50);

        Button btViewFixtures = new Button("View Fixture and Result Chart");
        Button btShowStats = new Button("Show all Team Stats");
        Button btShowRanking = new Button("Show all Team Ranking");
        Button btViewMatchScores = new Button("View a Match Score");

        
        btViewFixtures.setPrefWidth(190);
        btShowStats.setPrefWidth(190);
        btShowRanking.setPrefWidth(190);
        btViewMatchScores.setPrefWidth(190);
        switchUser.setPrefWidth(190);
        
        viewButtons.add(btViewFixtures, 1, 3);
        viewButtons.add(btShowStats, 1, 4);
        viewButtons.add(btShowRanking, 1, 5);
        viewButtons.add(btViewMatchScores, 1, 6);
        viewButtons.add(switchUser, 1, 11);
                                    
        BorderPane viewBorder = new BorderPane();
        viewBorder.setLeft(viewButtons);
        viewBorder.setCenter(textWindow);
        viewBorder.setBottom(bottomControls);
        
        textWindow.setVgap(50);
        textWindow.setHgap(40);
        viewButtons.setVgap(25);
        viewButtons.setHgap(30);
        bottomControls.setHgap(30);
        bottomControls.setVgap(10);
        
        Scene viewPage = new Scene(viewBorder, 750, 500); 
                
                

        viewWindow.setTitle("Viewer Window");
        viewWindow.setScene(viewPage);
        viewWindow.show();
        
        
        
        
        
                // Process events
        switchUser.setOnAction(e -> switchUser());
        btViewFixtures.setOnAction(e ->viewFixtures(fixtures));
        btShowStats.setOnAction(e -> viewTeamStats(teamList));
        btShowRanking.setOnAction(e -> viewRanking(teamList));
        btViewMatchScores.setOnAction(e -> viewMatchScores(fixtures, teamList));
    }   
        
        
    
    public void switchUser() {
        viewWindow.close();
        loginPage.userLogin();
    }
}

