package task1;

public class Main {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                for (int i = 10; i >= 0; i--) {
                    if (i == 0) {
                        System.out.println("Boom!");
                    } else {
                        System.out.println(i);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
