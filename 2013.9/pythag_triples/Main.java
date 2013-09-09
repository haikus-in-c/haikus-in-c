// java pythag_triple code with a hashmap for better time
// by Ian Zapolsky (09/09/13)

// imports
import java.util.LinkedList;
import java.util.HashMap;

public class Main {

    int[] array;
    HashMap<Integer, Integer> squares; 
    LinkedList triples;

    public Main(int[] init_array) {
        array = init_array;
        squares = new HashMap<Integer, Integer>();
        triples = new LinkedList();

        for (int i : array)
            squares.put((i*i), i);
    }

    private void findPythagoreanTriples() {
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i+1; j < array.length; j++) {
                int target = ((array[i]*array[i])+(array[j]*array[j]));
                if (squares.get(target) != null) {
                    int[] pythagoreanTriple = {array[i], array[j], squares.get(target)};
                    triples.add(pythagoreanTriple);
                }
            }
        }
    }

    private String printArray(Object array) {
        String result = "[";
        for (int i : (int[])array)
            result += String.valueOf(i)+" ";
        result += "]";
        return result;
    }

    public void printPythagoreanTriples() {
        findPythagoreanTriples();
        for (int i = 0; i < triples.size(); i++) 
            System.out.println(printArray(triples.get(i)));
    }
        
    public static void main(String[] args) {
        int[] array = {1,3,4,5,6,7,8,10,11};
        Main main = new Main(array);
        main.printPythagoreanTriples();
    }
}
