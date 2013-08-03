/*
	Yodle TriangleSum Programming Challenge
	*Solved in a manner that allows for repeat operations on structure*
	*Includes calcMinPath Method*
	By Ian Zapolsky
*/

import java.util.Scanner;
import java.io.*;

public class TriangleSum {

	public static void main(String[] args) {
		
		try {
		String filename = args[0];
		TriangleSum tSum = new TriangleSum();
		Scanner in = new Scanner(new FileReader(filename));
		
		// count the number of lines in triangle
		int n = 0;
		while (in.hasNextLine()) {
			String s = in.nextLine();
			n++;
		}
		in.close();

		// create array to store numbers of the number triangle
		Number[] array = new Number[tSum.row(n)];
		int i = 0;
		in = new Scanner(new FileReader(filename));
		while (in.hasNextInt()) 
			array[i++] = new Number(in.nextInt());

		// calculate the maximum triangle sum path
		int rows = n-1;
		System.out.println(tSum.calcMaxPath(array, rows));

		// check for non destruction
		System.out.println("\n"+tSum.calcMaxPath(array, rows));

		// check again, using calcMinPath
		System.out.println("\n"+tSum.calcMinPath(array, rows));

		}
		catch (FileNotFoundException e) {
			System.out.println("Usage: java TriangleSum <filename>");
		}
	}

	public TriangleSum() {
	}

	public int row(int n) {
		int result = 0;
		while (n > 0) 
			result += n--;
		return result;
	}

	public int calcMaxPath(Number[] array, int n) {
		while (--n >= 0) {
			int index = row(n);
			int mark = index;
			while (index <= (mark+n)) {
				int bL = array[index+n+1].curSum;
				int bR = array[index+n+2].curSum;
				if (bR > bL) {
					array[index].curSum = array[index].val + bR;
				}
				else {
					array[index].curSum = array[index].val + bL;
				}
				index++;
			}
		}
		return array[0].curSum;
	}

	public int calcMinPath(Number[] array, int n) {
		while (--n >= 0) {
			int index = row(n);
			int mark = index;
			while (index <= (mark+n)) {
				int bL = array[index+n+1].curSum;
				int bR = array[index+n+2].curSum;
				if (bR < bL) {
					array[index].curSum = array[index].val + bR;
				}
				else {
					array[index].curSum = array[index].val + bL;
				}
				index++;
			}
		}
		return array[0].curSum;
	}
}

