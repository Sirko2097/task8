package task2;

public class Printer implements Runnable {
    private final Value value;

    Printer(Value value) {
        this.value = value;
    }

    public void run() {
        synchronized (value) {
            while (value.getNumber() < 1000000) {
                while (!value.isFlag()) {
                    try {
                        value.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(value.getNumber() + " ");
                value.setFlag(false);
                value.notify();
            }
        }

    }
}
