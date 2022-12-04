public class Algorithm {

    /**
     * Given a list of n numbers, the Selection Problem is to find the xth smallest
     * element in the list.
     * This algorithm sorts merges two subarrays of arr[]
     * 
     * @param x
     * @param y
     * @param z
     * @param arr
     */
    public void mergeSortSplit(int left, int middle, int right, int arr[]) {
        // size of the subarray two be merged
        int size1 = (middle - left) + 1;
        int size2 = (right - middle);

        // temporary array
        int tempArray1[] = new int[size1];
        int tempArray2[] = new int[size2];

        // Copy the arr from the parameter into the tempporary arrays
        for (int i = 0; i < size1; ++i)
            tempArray1[i] = arr[left + i];
        for (int j = 0; j < size2; ++j)
            tempArray2[j] = arr[middle + 1 + j];

        // intitial index
        int a = 0;
        int b = 0;

        // initial index of merged subarray
        while (a < size1 && b < size2) {
            if (tempArray1[a] <= tempArray2[b]) {
                arr[left] = tempArray1[a];
                a++;
            } else {
                arr[left] = tempArray2[b];
                b++;
            }
            left++;
        }

        // Copy any remaining elements onto the temparrays
        while (a < size1) {
            arr[left] = tempArray1[a];
            a++;
            left++;
        }
        while (b < size2) {
            arr[left] = tempArray2[b];
            b++;
            left++;
        }
    }

    /**
     * The main merge algorithm that sorts the array
     * 
     * @param arr
     * @param left
     * @param right
     */
    public void mergeSort(int arr[], int left, int right) {
        if (left < right) {
            int middle = left + (right - 1) / 2;

            mergeSort(arr, left, right);
            mergeSort(arr, middle + 1, right);

            mergeSortSplit(left, middle, right, arr);
        }
    }

    /**
     * This algorithm takes an element as a pivot and places all values smaller than
     * it to the left and greater values to the right
     * 
     * @param arr
     * @param low
     * @param high
     */
    public void quickSortIterative(int arr[], int low, int high) {

    }
}
