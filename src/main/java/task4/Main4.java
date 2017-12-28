package task4;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main4 {
    private static final int SIZE = 1_000_000;
    private static int[] arr = new int[SIZE];

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = random.nextInt(404);
        }
        ForkJoinPool pool = new ForkJoinPool(4);
        Counter counter = new Counter(0, SIZE, arr);
        Long sum = pool.invoke(counter);
        System.out.println("Sum = " + sum);
    }
}
