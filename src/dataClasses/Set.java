package dataClasses;

public class Set {

	Team winner;
	private String gameScores[];
        private String gameWins[];
        private Match match;
        //Game object
	
	//constructor 
        public Set(Match match) {
            
            this.gameWins = new String[3];
            this.gameScores = new String[3];
            this.match = match;
        }
	public void setGameScores(String[] scores) {
            
            String[] tokens = new String[2];
            
            for (int i = 0; i < 3; i ++) {
            
                this.gameScores[i] = scores[i];
                
            }
            
            int homeWins = 0;
            int awayWins = 0;
            // in case of a draw
            int homeScore = 0;
            int awayScore = 0;
            
            //parsing out the scores
            for (int i = 0; i < 3; i ++) {
                
                String delims = "[:]";
                tokens = scores[i].split(delims);
                
                if (Integer.parseInt(tokens[0]) > Integer.parseInt(tokens[1])) {
                    gameWins[i] = match.getHomeTeam().getName();
                    homeWins ++;                  
                } else if (Integer.parseInt(tokens[1]) > Integer.parseInt(tokens[0])) {
                    gameWins[i] = match.getAwayTeam().getName();
                    awayWins ++;
                } else {
                    //draw
                    gameWins[i] = "draw";
                }
            }
            homeScore += Integer.parseInt(tokens[0]);
            awayScore += Integer.parseInt(tokens[1]);
                
                // set the winner, if draw, set to dummy team "None"
                if (homeWins > awayWins) {
                    winner = match.getHomeTeam();
                } else if (awayWins > homeWins) {
                    winner = match.getAwayTeam();
                } else if (homeScore > awayScore) {
                    winner = match.getHomeTeam();
                } else if (awayScore > homeScore) {
                    winner = match.getAwayTeam();
                } else {
                    winner = new Team("None");
                }
                System.out.println("DEBUG set: setGamescores, winner: " + winner.getName() );
              
            }


            
	
	
	public String getGameScores(int index) {
		
		return gameScores[index];
	}
       
	
	public Team getWinner() {
		
		return winner;
	}
        
}
