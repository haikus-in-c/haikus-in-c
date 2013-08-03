/* 
	Song class, written for solution to Spotify Programming Challenge 2
	by Ian Zapolsky
*/

public class Song {

	String title;
	double plays;
	double xPlays;
	double ratio;
	
	public Song(String init_title, double init_plays) {
		title = init_title;
		plays = init_plays;
	}

	public void setRatio(double one_plays, double i) {
		xPlays = (one_plays/i);
		ratio = (plays/xPlays);
	}

	public double getRatio() { return ratio; }

	public String toString() { return title; }
}
