/* 
	Song class, written for solution to Spotify Programming Challenge 2
	by Ian Zapolsky
*/

public class Song implements Comparable<Song> {

	String title;
	double plays;
	double expected_plays;
	double ratio;
	
	public Song(String init_title, double init_plays) {
		title = init_title;
		plays = init_plays;
	}

	public void setRatio(double track_one_plays, double position) {
		expected_plays = (track_one_plays/position);
		ratio = (plays/expected_plays);
	}

	public int compareTo(Song other) {
		if (this.getRatio() > other.getRatio())
			return -1;
		else if (this.getRatio() < other.getRatio())
			return 1;
		else
			return 0;
	}

	public double getRatio() { return ratio; }

	public String toString() { return title; }
}
