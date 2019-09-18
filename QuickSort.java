public Class QuickSort{
  /**
     * Recursive Quick Sort
     *
     * Selects a pivot RANDOMLY, and sorts the
     * left and right sides of the pivots.
     *
     * @param arr
     * @param start
     * @param end
     */
    public static void recursiveQuickSort(int[] arr, int start, int end) {
        if (start < end) {
            //retrieve the index of the pivot
            int pivotIndex = partition(arr, start, end);
            //sort the left and right side of the pivot
            recursiveQuickSort(arr, start, pivotIndex - 1);
            recursiveQuickSort(arr, pivotIndex + 1, end);

        }
    }

    /**
     * finds the appropriate index of the pivot.
     *
     * @param arr   reference of array
     * @param start inclusive start
     * @param end   inclusive end
     * @return appropriate index of the index
     */
    private static int partition(int[] arr, int start, int end) {
        int p = pickPivot(start, end);
        //first swap pivot with the first item in the array
        swap(arr, start, p);
        p = start;
        
        //reset subarrays indexes for <P and >P
        int i = start, j = start;
        //for each item in the subarray, compare with pivot
        for (int x = start + 1; x <= end; x++) {
            if (arr[p] < arr[x]) {
                //( > P ) only increment j
                j++;
            } else {
                // ( < P ) increment i and j
                swap(arr, x, i + 1);
                i++;
                j++;
            }
        }
        //swap the last item in <P with the pivot
        swap(arr, p, i);
        
        //return the index of the pivot
        return i;
    }

    /**
     * Swaps the elements of the given indexes.
     * No need to return the reference, since
     * arrays are references.
     *
     * @param arr array to swap elements
     * @param i   first index
     * @param j   second index
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Picks a random pivot [start, end]
     *
     * @param start
     * @param end
     * @return
     */
    private static int pickPivot(int start, int end) {
        Random r = new Random();
        return r.nextInt((end - start) + 1) + start;
    }
}
