package dataClasses;

public class Player {

	Team team;
	PlayerStats playerStats;
	String name;
	
        public Player() {
            
        }
	//Constructor
	public Player(String playerName, Team playerTeam) {
		name = playerName;
		//new Player stats object
		PlayerStats newPlayerStats = new PlayerStats(this);
		playerStats = newPlayerStats;
		// set team 
		team = playerTeam;
	}
	
	public PlayerStats getPlayerStats() {
		
		return playerStats;
	}
        
        public String getName() {
            
            return this.name;
        }
}
