package task4;

import java.util.concurrent.RecursiveTask;

public class Counter extends RecursiveTask<Long>{
    private int start;
    private int end;
    private int[] arr;

    Counter(int start, int end, int[] arr) {
        this.start = start;
        this.end = end;
        this.arr = arr;
    }

    @Override
    protected Long compute() {
        if (end - start < 20) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += (long)arr[i];
            }
            return sum;
        } else {
            int middle = start + (end - start) / 2;
            Counter fPart = new Counter(start, middle, arr);
            fPart.fork();

            Counter sPart = new Counter(middle + 1, end, arr);
            sPart.fork();
            return fPart.join() + sPart.join();
        }
    }
}
