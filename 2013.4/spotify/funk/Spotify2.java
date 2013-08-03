/*
	Spotify Programming Challenge 2: Funk
	by Ian Zapolsky
*/

import java.util.Scanner;

public class Spotify2 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your input");
		
		int n = scan.nextInt();
		int m = scan.nextInt();
		int i = 0;
		double one_plays = 0;
		Song[] array = new Song[n];
		
		// read input from stdin
		while (i < n) {
			double plays = scan.nextDouble();
			if (i == 0)
				one_plays = plays;
			String title = scan.next();
			array[i++] = new Song(title, plays);
		}
		
		// set ratios for all songs in input
		for (int j = 1; j <= n; j++)
			array[j-1].setRatio(one_plays, j);

		// sort array by ratio using simple selection sort
		Spotify2 x = new Spotify2();
		x.selectionSort(array, n);
		
		// print result
		String output = "\n";
		for (int k = 0; k < m; k++)
			output += array[k]+"\n";
		System.out.println(output);
	}

	public void selectionSort(Song[] array, int n) {
		int max_index;
		for (int i = 0; i < (n-1); i++) {
			max_index = i;
			for (int j = i+1; j < n; j++) {
				if (array[j].getRatio() > array[max_index].getRatio())
					max_index = j;
			}
			Song temp = array[i];
			array[i] = array[max_index];
			array[max_index] = temp;
		}
	}
}
