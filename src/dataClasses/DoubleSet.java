package dataClasses;

public class DoubleSet extends Set {
	
        DoubleSet(Match match) {
            super(match);
        }
        
        public String getPrintInfo() {
            
            String s = new String();
            s = this.getGameScores(0) + ", " + this.getGameScores(1) + ", " + this.getGameScores(2) + "\n ";
            
            return s;
        }
}
