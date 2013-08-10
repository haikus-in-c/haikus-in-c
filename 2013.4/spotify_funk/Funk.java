/*
	Spotify Programming Challenge 2: Funk
	by Ian Zapolsky
*/

import java.util.Scanner;
import java.util.Arrays;

public class Funk {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your input");
		
		int n = scan.nextInt();
		int m = scan.nextInt();
		double track_one_plays = 0;
		Song[] album = new Song[n];
		
		/* 	read input from stdin */
		for (int i = 0; i < n; i++) {
			double plays = scan.nextDouble();
			if (i == 0)
				track_one_plays = plays;
			String title = scan.next();
			album[i] = new Song(title, plays);
		}
		
		/*	set ratios for all songs in input */
		for (int i = 1; i <= n; i++)
			album[i-1].setRatio(track_one_plays, i);

		/*	sort album by ratio using any sorting method */
		Arrays.sort(album);
		
		/* 	print result */
		String output = "\n";
		for (int i = 0; i < m; i++)
			output += album[i]+"\n";
		System.out.println(output);
	}

	public void selectionSort(Song[] album, int n) {
		int max_index;
		for (int i = 0; i < (n-1); i++) {
			max_index = i;
			for (int j = i+1; j < n; j++) {
				if (album[j].getRatio() > album[max_index].getRatio())
					max_index = j;
			}
			Song temp = album[i];
			album[i] = album[max_index];
			album[max_index] = temp;
		}
	}
}
