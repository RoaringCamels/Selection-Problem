import java.util.*;

public class Algorithm {
    public static void main(String[] args) {
        int size = 10;
        int sizeBound = 2000;
        long sum = 0;

        int arr[] = new int[size];
        fillArray(arr, size, sizeBound);

        for (int i = 0; i < 20; i++) {
            long start = System.nanoTime();
            int result = mmRule(arr, 0, arr.length - 1, 1);
            long end = System.nanoTime();
            long total = end - start;
            sum += total;

            System.out.println("The " + i + 1 + " run time is: " + total);
        }
        System.out.println("\nThe average time is: " + sum / 15 + " nanoseconds");
    }

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
    public static void mergeSortSplit(int left, int middle, int right, int arr[]) {
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
    public static void mergeSort(int arr[], int left, int right) {
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
    public static int quickSortPartition(int arr[], int low, int high) {
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
    public static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int partitioningIndex = quickSortPartition(arr, low, high);

            quickSort(arr, low, partitioningIndex - 1);
            quickSort(arr, partitioningIndex + 1, high);
        }
    }
    // -----------------------------END QUICK SORT-----------------------------

    // -----------------------------mm RULE-----------------------------
    /**
     * 
     * @param arr
     * @param left
     * @param right
     * @param middle
     * @return
     */
    public static int mmRule(int arr[], int left, int right, int k) {
        if (k > 0 && k <= right - left + 1) {
            int numOfElements = right - left + 1;
            int numOfGroups = (numOfElements + 6) / 7;
            int[] median = new int[numOfGroups];
            int count;

            for (count = 0; count < numOfElements / 7; count++) {
                median[count] = findMedian(arr, left + count * 7, left + count * 7 + 7);
            }

            if (count * 7 < numOfElements) {
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
            if (location - left == k - 1) {
                return arr[location];
            } else if (location - left > k - 1) {
                return mmRule(arr, left, location - 1, k);
            } else {
                return mmRule(arr, location + 1, right, k - location + left - 1);
            }

        } else {
            int x = -1;
            return x;
        }
    }

    /**
     * 
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int findMedian(int arr[], int left, int right) {
        Arrays.sort(arr, left, right);
        return arr[left + (right - left) / 2];
    }

    /**
     * 
     * @param arr
     * @param left
     * @param right
     * @param num
     * @return
     */
    public static int partition(int arr[], int left, int right, int num) {
        int count;
        for (count = left; count < right; count++) {
            if (arr[count] == num) {
                int temp = arr[count];
                arr[count] = arr[right];
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

    /**
     * 
     * @param arr
     * @param num
     * @param size
     * @return
     */
    public static int[] fillArray(int[] arr, int num, int bound) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }

}
