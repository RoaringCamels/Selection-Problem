import java.util.*;

public class AlgorithmService {
    /**
     * 
     * @param arr
     * @param num
     * @param size
     * @return
     */
    public int[] fillArray(int[] arr, int num, int bound) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }
}
