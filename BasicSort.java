import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BasicSort {
    
     * First implementation of bubble sort
     *
     * @param arr
     * @return
     */
    public static void bubbleSort(int[] arr) {
        //comparisons and swaps
        int comps = 0, swaps = 0;
        
        //start bubble sort
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    //toggle swapped bool
                    swapped = true;
                    //increment swaps and comps
                    swaps++;
                    comps++;
                }
            }
        } while (swapped);
    
    /**
     * To improve bubble sort, we consider that if there were no
     * swaps through the bubbling, then the list is sorted. Also,
     * we do not have to go through the whole list each time. We
     * decrease the amount of times we bubble up by one.
     *
     * @param arr
     * @return
     */
    public static void improvedBubbleSort(int[] arr) {
        //comparisons and swaps
        int comps = 0, swaps = 0;

        //start improved bubble sort
        boolean swapped;
        for (int i = 0; i < arr.length; i++) {
            //assume we did not swap
            swapped = false;
            //decrement loop j's length in each iteration of i
            for (int j = 0; j < arr.length - (i + 1); j++) {
                if (arr[j + 1] < arr[j]) {
                    //swap!
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    //toggle swapped bool
                    swapped = true;
                    //increment swaps and comps
                    swaps++;
                    comps++;
                }
            }
            //if we did not swapped then stop!
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * First implementation of selection sort
     *
     * @param arr
     * @return
     */
    public static void selectionSort(int[] arr) {
        //comparisons and swaps
        int comps = 0, swaps = 0;
        int smallestIndex;
        boolean swapping;

        for (int i = 0; i < arr.length - 1; i++) {
            smallestIndex = i;
            //each iteration set swapping to false
            swapping = false;

            //find the next smallest
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[smallestIndex]) {
                    smallestIndex = j;
                    swapping = true;
                    //increment comps
                    comps++;
                }
            }
            if (swapping) {
                //swap with the smallest's index at index i
                int temp = arr[i];
                arr[i] = arr[smallestIndex];
                arr[smallestIndex] = temp;
                swaps++;
            }
        }
    }

    public static void improvedSelectionSort(int[] arr) {
        //comparisons and swaps
        int comps = 0, swaps = 0;

        int min, max;   //variables for min and max indexes in the list
        int rear;

        for (int i = 0; i < (arr.length / 2); i++) {
            //initialize the min and max each iteration
            min = i;
            max = rear = arr.length - (i + 1);  //keep track of rear as well

            for (int j = i; j < arr.length - i; j++) {
                //find new min and max
                if (arr[min] > arr[j]) {
                    min = j;
                    comps++; //increment comps
                } else if (arr[max] < arr[j]) {
                    max = j;
                    comps++;    //increment comps
                }
            }
            //if max is in the beginning(min index) then set the new index of max to be min
            if (i == max)
                max = min;
            //swap the new min and max values
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
            //second swap for max
            temp = arr[rear];
            arr[rear] = arr[max];
            arr[max] = temp;

            swaps += 2; //increment by two for both swaps
        }
    }

    /**
     * First implementation of selection sort
     *
     * @param arr
     * @return
     */
    public static void insertionSort(int[] arr) {
        //comparisons and swaps
        int comps = 0, shifts = 0;

        int contested;
        int j;
        boolean doneShifting;

        for (int i = 1; i < arr.length; i++) {
            contested = arr[i];
            doneShifting = false;
            j = i - 1;
            //check elements to left to shift and insert
            while (!doneShifting) {
                //shift and insert if element is greater
                if (contested < arr[j]) {
                    arr[j + 1] = arr[j];
                    arr[j] = contested;
                    //increment shifts and comps
                    shifts++;
                    comps++;
                    if (j != 0)
                        j--;
                } else
                    doneShifting = true;
            }
        }
    }
