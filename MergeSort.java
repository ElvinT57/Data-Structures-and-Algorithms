public Class MergeSort{
  /**
     * Iterative Merge Sort.
     *
     * Uses 2D array as a vector of subarrays.
     * {3,2,0,1} -> {{3}
     *               {2}
     *               {0}
     *               {1}}
     * Combines and sorts every pair of twos.
     * Executes this until the vector is of
     * size 1x1.
     *
     * @param arr
     * @return Sorted array
     */
    private static int[] iterativeMergeSort(int[] arr) {
        //create a vector with arr.length rows to begin with
        int[][] mergedArrays = new int[arr.length][1];
        for (int i = 0; i < mergedArrays.length; i++)
            mergedArrays[i][0] = arr[i];

        //merge until it results into one array (1x1 vector)
        while (mergedArrays.length != 1) {
            //reduce the next vector's row by quotient of 2
            int[][] temp = new int[mergedArrays.length - (mergedArrays.length / 2)][1];
            //merge every two subarrays
            for (int i = 0; i < mergedArrays.length; i += 2) {
                //if i is the last element, assign it as is
                if (i == mergedArrays.length - 1)
                    temp[i / 2] = mergedArrays[i];
                else    //otherwise merge all others
                    //divide i by 2 to get the appropriate index of temp
                    temp[i / 2] = merge(mergedArrays[i], mergedArrays[i + 1]);
            }
            //prints the vector each iteration
            print2DArray(temp);
            //update the mergedArrays
            mergedArrays = temp;
        }
        //return the merged result
        return mergedArrays[0];
    }

    /**
     * Combines and sorts two 1D arrays and returns the results
     *
     * @param A
     * @param B
     * @return
     */
    private static int[] merge(int[] A, int[] B) {
        //combine the size of both arrays
        int[] result = new int[A.length + B.length];
        int j = 0, k = 0;   //index of A and B
        //sort the elements
        for (int i = 0; i < result.length; i++) {
            if (j == A.length)     //if there is no more elements to compare in A
                //B has the last elements
                result[i] = B[k++];
            else if (k == B.length) //if there is no more elements to compare in B
                //A has the last elements
                result[i] = A[j++];
            else if (A[j] < B[k])
                //A has the next smallest element
                result[i] = A[j++]; //increment to next index in A
            else
                //B has the next smallest element
                result[i] = B[k++]; //increment to next index in B

        }
        return result;
    }
}
