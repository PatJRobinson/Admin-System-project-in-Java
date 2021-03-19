package dataClasses;

public class TeamStats {
	
	int matchesPlayed;
	int matchesWon;
	int setsWon;
	Team team;
	
	public void resetStats() {
		
		matchesPlayed = 0;
		matchesWon = 0;
		setsWon = 0;
	}
	
	public void incrementSetsWon() {
	
		setsWon++;
	}
	
	public void incrementMatchesWon() {
		
		matchesWon++;
	}
	
	public void incrementMatchesPlayed() {
		
		matchesPlayed++;
	}
	
	public void printStats() {
		
		System.out.println("Team: " + team.name + "Matches played: " + matchesPlayed + "Matches won: " + matchesWon + "Sets Won: " + setsWon);
	}
        
        public int getMatchesWon() {
            return matchesWon;
        }
        
        public int getMatchesPlayed() {
            return matchesPlayed;
        }
        
        public int getSetsWon () {
            return setsWon;
        }
	

}
