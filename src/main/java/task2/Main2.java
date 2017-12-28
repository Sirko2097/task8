package task2;

public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        Value value = new Value();

        Counter counter = new Counter(value);
        Printer printer = new Printer(value);
        new Thread(counter).start();
        new Thread(printer).start();


    }
}
