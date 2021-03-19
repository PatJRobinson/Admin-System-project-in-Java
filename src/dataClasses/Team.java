package dataClasses;
        
    import java.util.List;
    import java.util.ArrayList;


public class Team {
	
        // using List instead of array as length of PlyaerList is not fixed
	List<Player> players;
	TeamStats teamStats;
	String name;
	TeamsList teamList;
	
        // dummy constructor
        public Team(String name) {
            this.name = name;
        }
	//constructor
	public Team(String teamName, TeamsList list) {
            
            name = teamName;
            teamStats = new TeamStats();
            teamList = list;
            List<Player> newPlayerList = new ArrayList<Player>();
            players = newPlayerList;
	}
	
        public void setName(String name){
            this.name = name;
        }
        
	public TeamStats getTeamScore() {
		
            return teamStats;
	}
	
        //returns player names in a list
	public List<String> getPlayers() {
            
            List<String> playerList = new ArrayList<String>();
            for (int i = 0; i < players.size(); i++) {
                
              playerList.add(players.get(i).name);
            }
            return playerList;
	}

	public void addPlayer(String name) {
		
            Player newPlayer = new Player(name, this);
            this.appendPlayerList(newPlayer);
            
            System.out.println("player added " + getPlayer(name).name);
	}
	
	public void appendPlayerList(Player newPlayer) {
            
            players.add(newPlayer);
		
	}
        
        public String getName() {
            return name;
        }
        
        public Player getPlayer(String name) {
            

            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).name == name) {
                    return players.get(i);
                }
                
            }
            return null; 
        }
        
        public TeamStats getTeamStats() {
            return teamStats;
        }
        
        public boolean alreadyExists(String playerName) {
            

            for (int i =0; i < players.size(); i++) {
                
                if (playerName.equals(players.get(i).getName())) {
                    return true;
                }
            }
            
            return false;
        }

}
