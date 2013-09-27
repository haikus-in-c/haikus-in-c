// Quicksort.java
// by Ian Zapolsky (09/26/13)

public class Quicksort {

    public static void main(String[] args) {
        Quicksort q = new Quicksort();
        int[] array = {1, 5, 2, 3, 6, 10, 11, 2, 3};
        System.out.println(q.printArray(array));
        q.quicksort(array);
        System.out.println(q.printArray(array));
    }

    public Quicksort() { }

    public void quicksort(int[] arrayToSort) {
        quicksort(arrayToSort, 0, arrayToSort.length-1);
    }

    // recursive quicksort method
    private void quicksort(int[] arrayToSort, int begIndex, int endIndex) {

        // if we're looking at an array section with 1 element, return
        if (endIndex <= begIndex)
            return;

        // choose the pivot
        int pivotIndex = (endIndex+begIndex)/2;
        int pivot = arrayToSort[pivotIndex];
    
        // move the pivot to the end of the array
        swap(arrayToSort, pivotIndex, endIndex);

        // in-place partitioning sequence
        int cur = begIndex;
        for (int i = begIndex; i < endIndex; i++) {
            if (arrayToSort[i] < pivot) {
                swap(arrayToSort, cur, i);
                cur++;
            }
        }

        // move the pivot back from the end of the array
        swap(arrayToSort, cur, endIndex);
        pivotIndex = cur;

        // call quicksort on the two subarrays to the left and right of the pivot
        quicksort(arrayToSort, begIndex, pivotIndex-1);
        quicksort(arrayToSort, pivotIndex+1, endIndex);
    }

    private void swap(int[] array, int indexOne, int indexTwo) {
        int temp = array[indexOne];
        array[indexOne] = array[indexTwo];
        array[indexTwo] = temp;
    }

    private String printArray(int[] array) {
        String result = "";
        for (int i : array)
            result += i+", ";
        return result;
    }

}


