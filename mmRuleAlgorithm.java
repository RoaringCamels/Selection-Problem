import java.util.*;

public class mmRuleAlgorithm {
    public static void main(String[] args) {
        AlgorithmService alg = new AlgorithmService();
        int size = 10;
        int sizeBound = 2000;
        long sum = 0;

        int arr[] = new int[size];
        alg.fillArray(arr, size, sizeBound);

        for (int i = 0; i < 20; i++) {
            long start = System.nanoTime();
            mmRule(arr, 0, arr.length - 1, 1);
            long end = System.nanoTime();
            long total = end - start;
            sum += total;

            System.out.println("The " + i + 1 + " run time is: " + total);
        }
        System.out.println("\nThe average time is: " + sum / 15 + " nanoseconds");
    }

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
}