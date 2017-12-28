package task2;

public class Counter implements Runnable{
    private final Value value;

    Counter(Value value) {
        this.value = value;
    }

    public void run() {
        synchronized (value) {
            while (value.getNumber() < 1_000_000) {
                while (value.isFlag()) {
                    try {
                        value.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                value.setNumber(increment(value.getNumber()));
                value.setFlag(true);
                value.notify();
            }
        }
    }
    private long increment(long element) {
        return ++element;
    }
}
