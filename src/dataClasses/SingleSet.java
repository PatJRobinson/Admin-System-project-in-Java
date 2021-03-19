package dataClasses;

public class SingleSet extends Set{

	Player homeTeamPlayer;
        Player awayTeamPlayer;
        
        SingleSet(Match match) {
            super(match);
        }
        
        public void setPlayers(Player homePlayer, Player awayPlayer) {
            System.out.println("setting players");
            this.homeTeamPlayer = homePlayer;
            this.awayTeamPlayer = awayPlayer;
            System.out.println("DEBUG sSet: setPlayers " + homeTeamPlayer.getName() + awayTeamPlayer.getName());
        }
        
        public String getPrintInfo() {
            
            String s = new String();
            
            s = homeTeamPlayer.getName() + " vs " + awayTeamPlayer.getName() + ": " + this.getGameScores(0) + ", " + this.getGameScores(1) + ", " + this.getGameScores(2) + "\n ";            
            return s;
        }
        

}
