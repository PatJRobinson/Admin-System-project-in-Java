package dataClasses;

import java.util.List;
import java.util.ArrayList;

public class Fixtures {
	
	List<Match> matches;
	
        public Fixtures() {
            matches = new ArrayList<Match>();
        }
        
        public void createMatch(Team homeTeam, Team awayTeam){
            
            Match newMatch = new Match(homeTeam, awayTeam);
            matches.add(newMatch);
            System.out.println("Match made: " + homeTeam.getName() + " v " + awayTeam.getName());
               
        }
        
	public Match getMatch(Team homeTeam, Team awayTeam) {
		
                
                for (int i = 0; i < matches.size(); i ++) {
                    
                    if (matches.get(i).getHomeTeam().equals(homeTeam)&&(matches.get(i).getAwayTeam().equals(awayTeam))) {
                        return matches.get(i);
                    }
                }
                System.out.println("F");
                Match nullMatch = new Match();
		return nullMatch;
	}
        
        public Match getMatchByIndex(int index) {
            
            return matches.get(index);
        }
	
	public List<Match> getFixtures() {
		
		return matches;
	}
        
        public List<String> getPlayedMatches() {
            
            List<String> playedMatches = new ArrayList<String>();
            System.out.println("DEBUG fixtures: getting played matches");
            for (int i = 0; i < matches.size(); i ++) {
                if (matches.get(i).getMatchPlayed()) {
                    String s = matches.get(i).getHomeTeam().getName() + " vs " + matches.get(i).getAwayTeam().getName();
                    playedMatches.add(s);
                }
            }
            
            return playedMatches;
        }
        
	public void createFixtures(List<Team> list) {
            
            // reset fixtures
            matches = matches = new ArrayList<Match>();
            
            for (int i = 0; i < list.size(); i++) {
                for (int x = i+1; x < list.size(); x++) {
                    
                  createMatch(list.get(i), list.get(x));
                  createMatch(list.get(x), list.get(i));
                  
                }
                
            }
                 
            
		
	}

        //This should only be used after resetting all stats. Counts wins and matches played 
        //based on saved scores
        public void updateTeamStats() {
            
            // looping through all of the matches, we will update each team's player stats according to the 
            // scores of the match 
            for (int i = 0; i < matches.size(); i++) {
                
                //get the match 
                Match curMatch = getMatchByIndex(i);
                if (curMatch.matchPlayed) {
                
                    // 4 single sets
                    for (int x = 0; x < 4; x++) {

                        //get the winning team
                        Team singleWin = curMatch.getSingleSet(x).getWinner();
                        //update number of sets won
                        singleWin.teamStats.incrementSetsWon();
                    }

                    // The double set
                    Team doubleWin = curMatch.getDoubleSet().getWinner();
                    //update number of sets won
                    doubleWin.teamStats.incrementSetsWon();

                    // update the match winner's stats 
                    Team matchWin = curMatch.getWinner();
                    matchWin.teamStats.incrementMatchesWon();
                    matchWin.teamStats.incrementMatchesPlayed();

                    // update match played stats for other team
                    if (matchWin.equals(curMatch.getHomeTeam())) {
                        curMatch.getAwayTeam().teamStats.incrementMatchesPlayed();
                    } else if (matchWin.equals(curMatch.getAwayTeam())){
                        curMatch.getHomeTeam().teamStats.incrementMatchesPlayed();
                    }
                    //System.out.println("DEBUG Gen TeamStats" + matchWin.getName() + " teams " + curMatch.getAwayTeam().getName() + " " + curMatch.getHomeTeam().getName());
                }
            }
        }
	
	public void printFixtures() {
		
		for (int i = 0; i < matches.size(); i ++) {
			
			//Formatted output according to example in spec	
			System.out.println(i+1 + ": ");
			// print team names
			System.out.println(matches.get(i).teams[0].name + matches.get(i).teams[1].name);
			// if match is played
				// return score / winner
			// else
				//System.out.println("Score / Winner: NA);
			System.out.println(" ");
		}
	}

}
