package dataClasses;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class TeamsList {
        
        //list of all the teams, which can be appended - more suitable than array
	List<Team> teams;
	
        public TeamsList() {
            List<Team> newTeamList = new ArrayList<Team>();
            teams = newTeamList;
        }
        


        
	public List<Team> rankTeams() {
            
            List<Team> rankedTeams = new ArrayList<Team>();
            List<Team> copy = new ArrayList<Team>();
            copy.addAll(teams);

            while (copy.size() > 0) {
                
                int bestSoFar = 0;
                Team bestTeamSoFar = new Team("");
                
                for (int i = 0; i < copy.size(); i++) {
                  

                    
                    if (copy.get(i).getTeamStats().getMatchesWon() >= bestSoFar) {
                        bestTeamSoFar = copy.get(i);
                        bestSoFar = copy.get(i).getTeamStats().getMatchesWon();

                    }
                    

            
                }
                rankedTeams.add(bestTeamSoFar);
                copy.remove(bestTeamSoFar);
            }
            
            return rankedTeams;
	}
        
        public boolean alreadyExists(String name) {
            
            for (int i = 0; i < teams.size(); i ++) {
                if (name.equals(teams.get(i).getName())) {
                    return true;
                }
            }
            return false;
        }
        
        public void resetAllStats() {
            
            //Loop through each team and re-initialize all stats
            for (int i = 0; i < teams.size(); i++ ) {
                teams.get(i).teamStats.resetStats();
            }
        }
        
	public void removeTeam() {
		
		
	}
	
	public Team getTeam(String teamName) {
				
            // search for team
            for (int i = 0; i < teams.size(); i++) {
                    //Compare the names
                    if (teams.get(i).getName().equals(teamName)) {
                            return teams.get(i);
                    }
            }

            // Not found
            System.out.println("Team Not Found");
            return null;
		
	}
	
	public void newTeam(String teamName) {
		
		Team newTeam = new Team(teamName, this);
		teams.add(newTeam);
                
                System.out.println("Team added " + getTeam(teamName).name);
		
	}
        
        public List<String> getTeamNameList() {
            List<String> newTeamList = new ArrayList<String>();
            
            for (int i = 0; i < teams.size(); i ++) {
                
                newTeamList.add(teams.get(i).name);                
                
                }
            
            return newTeamList;
        }
        
        public List<Team> getTeamsList() {
            return teams;
        }
}
