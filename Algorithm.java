import java.util.*;

public class Algorithm {

    // -----------------------------MERGE SORT-----------------------------
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
    // -----------------------------END MERGE SORT-----------------------------

    // -----------------------------QUICK SORT-----------------------------
    /**
     * This algorithm takes an element as a pivot and places all values smaller than
     * it to the left and greater values to the right
     * 
     * @param arr
     * @param low
     * @param high
     */
    public int quickSortPartition(int arr[], int low, int high) {
        int pivot = arr[high];
        int index = (low - 1);

        for (int i = low; i <= high - 1; i++) {
            // if the current element is <= the pivot, swap arr[index] & arr[i]
            if (arr[i] <= pivot) {
                index++;
                // the swap
                int temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
            }
        }

        int tempArray = arr[index + 1];
        arr[index + 1] = arr[high];
        arr[high] = tempArray;

        return index + 1;
    }

    /**
     * Main algorithm that implements the quickSortPartition algorithm...
     * 
     * @param arr  the array that will be sorted
     * @param low  beginnging of the array that will be the starting index
     * @param high end of the array that will be the last index
     */
    public void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int partitioningIndex = quickSortPartition(arr, low, high);

            quickSort(arr, low, partitioningIndex - 1);
            quickSort(arr, partitioningIndex + 1, high);
        }
    }
    // -----------------------------END QUICK SORT-----------------------------

    // -----------------------------mm RULE-----------------------------
    public int mmRule(int arr[], int left, int right, int middle) {
        if (middle > 0 && middle <= right - left + 1) {
            int numOfElements = right - left + 1;
            int numOfGroups = (numOfElements + 6) / 7;
            int[] median = new int[numOfGroups];
            int count;

            for (count = 0; count < numOfElements / 7; count++) {
                median[count] = findMedian(arr, left + count * 7, left + count * 7 + numOfElements % 7);
                count++;
            }

            int mm;
            if (count == 1) {
                mm = median[count - 1];
            } else {
                mm = mmRule(median, 0, count - 1, count / 2);
            }

            int location = partition(arr, left, right, mm);
            if (location - left == middle - 1) {
                return arr[location];
            } else if (location - left > middle - 1) {
                return mmRule(arr, left, location - 1, middle);
            } else {
                return mmRule(arr, location + 1, right, middle - location + left - 1);
            }
        } else {
            int x = -1;
            return x;
        }
    }

    public int findMedian(int arr[], int left, int right) {
        Arrays.sort(arr, left, right);
        return arr[left + (right - left) / 2];
    }

    public int partition(int arr[], int left, int right, int num) {
        int count;
        for (int i = left; i < right; i++) {
            if (arr[i] == num) {
                int temp = arr[i];
                arr[i] = arr[right];
                arr[right] = temp;
            }
        }

        count = left;
        for (int i = left; i < right; i++) {
            int temp = arr[count];
            arr[i] = arr[right];
            arr[right] = temp;
            count++;
        }

        // copy the array onto the temp
        int temp = arr[count];
        arr[count] = arr[right];
        arr[right] = temp;
        return count;
    }
    // -----------------------------END mm RULE-----------------------------

}
