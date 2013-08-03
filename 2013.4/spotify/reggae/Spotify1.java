/* 	solution to the 'reggae' programming question on spotify.com
	by Ian Zapolsky 4/20/13 */

public class Spotify1 {

	public static void main(String[] args) {
		// this is sketchy, should probably know how to write a method that converts a String to an int
		int test = Integer.valueOf(args[0]);	
		Spotify1 x = new Spotify1();
		System.out.println(x.convertBinToDec(x.convertReverseDecToBin(test)));
	}

	public Spotify1() {
	}

	// converts int i to reversed binary string
	public String convertReverseDecToBin(int i) {
		int input = i;
		int rem;
		String result = "";
		while (input != 0) {
			rem = input%2;
			input = input/2;
			result+=rem;
		}
		return result;
	}

	// converts i to binary representation using bit manipulation
	// TODO: research this bit manipulation
	public String alternateConvert(int i) {
		char[] buf = new char[32];
		String result = "";
		boolean oneHit = false;
		int k;
		for (int c = 31; c >= 0; c--) {
			k = i >> c;
			if ((k & 1) == 1) {
				result += "1";
				oneHit = true;
			}
			else {
				if (oneHit)
					result += "0";
			}
		}
		return result;
	}

	// reverses some input string
	public String reverseString(String s) {
		char[] buf = new char[s.length()];
		int count = 0;
		for (int i = (s.length()-1); i >= 0; i--)
			buf[count++] = s.charAt(i);
		return new String(buf);
	}

	// converts binary string to decimal number by using builtin Math.pow() function
	// TODO: is there a better way to do this without using Math.pow()?
	public int convertBinToDec(String s) {
		int result, count;
		result = 0;
		count = 0;
		for (int i = (s.length()-1); i >= 0; i--) {
			if (s.charAt(i) == '1') {
				result += Math.pow(2, count);
				count++;
			}
			else
				count++;
		}
		return result;
	}
}
