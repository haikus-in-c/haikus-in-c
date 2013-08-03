/*	JuggleFest Programming Challenge from Yodle
	By Ian Zapolsky	*/

import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

public class JuggleFest {

	public static void main(String[] args) {
		try {	
		
		String filename = args[0];
		JuggleFest x = new JuggleFest(filename);
		x.fillAllFirstPref();
		x.fixOverfill();
		x.printResult();	
		}
		catch (FileNotFoundException e) {
			System.out.println("Usage: java JuggleFest <filename>");
		}
	}

	int circuits;
	int jugglers;
	int target;
	Circuit[] cArray;
	Juggler[] jArray;	

	public JuggleFest(String filename) throws FileNotFoundException {

		Scanner in = new Scanner(new FileReader(filename));
		String nextLine;

		// determine number of circuits, number of jugglers, number of jugglers per circuit
		while ((nextLine = in.nextLine()).length() != 0)		
			circuits++;
		while (in.hasNext()) {
			nextLine = in.nextLine();
			jugglers++;
		}
		target = jugglers/circuits;
		in.close();

		// create array of circuits, array of jugglers
		cArray = new Circuit[circuits];
		jArray = new Juggler[jugglers];
		int i = 0;

		// create all circuits and juggler objects
		in = new Scanner(new FileReader(filename));
		while ((nextLine = in.nextLine()).length() != 0)
			cArray[i++] = new Circuit(nextLine, jugglers, target);
		i = 0;
		while (in.hasNext()) {
			nextLine = in.nextLine();
			jArray[i++] = new Juggler(nextLine);	
		}
	}

	public void printResult() {
		String result = "";
		for (Circuit c : cArray) {
			System.out.println(c.getName());
			result += c.getName()+" ";
			for (int i = 0; i < c.target; i++)
				result += c.jugglers.get(i)+" ";
			result += "\n";
		}
		System.out.println(result);
	}

	/*
	public void printResult() {
		String result = "";
		for (Circuit c: cArray)
			result += c.getName()+" target:"+c.target+" actual:"+c.jugglers.size()+" overflow:"+c.overflow.size()+"\n";
		System.out.println(result);
	}
	*/	

	public void fillAllFirstPref() {
		for (Juggler j : jArray) {
			String pref = j.getPref();
			Circuit p1 = this.matchCircuit(pref);
			p1.addJuggler(j);
		}
	}
	
	public boolean areFull() {
		for (Circuit c: cArray) {
			if (!c.isFull())
				return false;
		}
		return true;
	}

	public Circuit findUnder() {
		for (Circuit c: cArray) {
			if (c.isUnder())
				return c;
		}
		return null;
	}

	public Circuit matchCircuit(String pref) {
		/* 	Lying to the compiler here.... Not sure what the official fix to issues like
		 	this is, but if I don't lie, Java will complain about the possibility that match 
			might never get initialized. */
		Circuit match = cArray[0]; 
		for (Circuit c : cArray) {
			if (c.getName().equalsIgnoreCase(pref))
				match = c;
		}
		return match;
	}
	
	public void fixOverfill() {
		boolean full = false;
		while (full == false) {
			boolean placed;
			for (Circuit c : cArray) {
				while (c.isOver()) {
					Juggler j = c.getLowestPerformer();
					placed = false;
					while (placed == false) {
						String pref = j.getPref();
						Circuit nextCircuit = this.matchCircuit(pref);
						if (!(nextCircuit.isFull())) {
							nextCircuit.addJuggler(j);
							placed = true;					
						}
						else {
							if (nextCircuit.checkJuggler(j) == true) {
								nextCircuit.addJuggler(j);
								placed = true;
							}
							//else if (j.isLast()) {
							else {	
								Circuit under = findUnder(); 
								if (under != null) {
									under.addJuggler(j);
									placed = true;
								}
								else {
									Random gen = new Random();
									int randomIndex = gen.nextInt(circuits);
									cArray[randomIndex].addJuggler(j);
									placed = true;
								}
							}
						}
					}
				}
			}
		full = areFull();
		}
	}

}
