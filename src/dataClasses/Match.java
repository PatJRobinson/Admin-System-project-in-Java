package dataClasses;

public class Match {
	
	DoubleSet doubleSet; 
	SingleSet singleSet[] = new SingleSet[4];
	Team winner;
	Team teams[]; 
	Player players[][];
        Team homeTeam;
        Team awayTeam;
        int matchNumber;
        boolean matchPlayed = false;

        //constructor
        public Match() {
            
            for (int i = 0; i < 4; i++) {
                singleSet[i] = new SingleSet(this);
            }
            doubleSet = new DoubleSet(this);
        
        }
        public Match(Team homeTeam, Team awayTeam) {
            for (int i = 0; i < 4; i++) {
                singleSet[i] = new SingleSet(this);
            }
            doubleSet = new DoubleSet(this);
            //teams = new Team[2];
            //players = new Player[2][2];
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
        }
	public Team getWinner() {
		
		return winner;
	}
	
	public DoubleSet getDoubleSet() {
		//System.out.println("DEBUG getDoubleSet()");
		return doubleSet;
	}
	
        public void setDoubleSet(DoubleSet newDoubleSet) {
            doubleSet = newDoubleSet;
        }
        
	public SingleSet getSingleSet(int setNumber) {
		
		return singleSet[setNumber];
	}
        
        public void setSingleSet(int setNumber, SingleSet newSingleSet) {
            
            singleSet[setNumber] = newSingleSet;
        }

	public Team[] getTeams() {
		
		return teams; 
	}
        
        public Team getHomeTeam() {
            
            return homeTeam;
        }
        
        public boolean getMatchPlayed() {
            
            return matchPlayed;
        }
        
        public void setMatchPlayed(boolean played) {
            
            matchPlayed = true;
        }
        
        public Team getAwayTeam() {
            return awayTeam;
        }
	
	public String getMatchInfo() {
            String winner = "NA (Not yet played)";
            if (matchPlayed) {
                winner = this.winner.getName();
            }
            String s = String.format("%1$-15s", homeTeam.getName() + " vs " + awayTeam.getName() + "            winner = " + winner);
            return s;
	}
        
	
	//NOT SURE HOW MATCHES ARE SCORED: JUST GUESSING
	public void calculateMatchWin() {
		
            int homeScore = 0;
            int awayScore = 0;
            
            for (int i = 0; i < 4; i++) {
                if (singleSet[i].winner == homeTeam) {
                    homeScore++;
                } else if (singleSet[i].winner == awayTeam) {
                    awayScore++;
                } else {
                    System.out.println("ERROR: unable to calculate match winner");
                    break;
                }
            }
            
            if (doubleSet.winner == homeTeam) {
                    homeScore++;
            } else if (doubleSet.winner == awayTeam) {
                awayScore++;
            }
            
            if (homeScore > awayScore) {
                winner = homeTeam;
            } else if (awayScore > homeScore) {
                winner = homeTeam;
            } else {
                System.out.println("Not calculated winner");
            }
            matchPlayed = true;
              
            
	}
	
}
