package task3;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main3 {
    static Map<Integer, Integer> map = new HashMap<>();
    static Map<Integer, Integer> conMap = new ConcurrentHashMap<>();
    private static final int AMOUNT = 10;

    public static void main(String[] args) {
        ArrayList<Thread> readers = new ArrayList<>();
        ArrayList<Thread> writers = new ArrayList<>();
        Date date = new Date();

        for (int i = 0; i < AMOUNT; i++) {
            readers.add(new Reader(i, 1));
            writers.add(new Writer(i, 1));
        }

        for (int i = 0; i < AMOUNT; i++) {
            writers.get(i).start();
            readers.get(i).start();
        }

        for (int i = 0; i < AMOUNT; i++) {
            try {
                writers.get(i).join();
                readers.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Time(map): " + (new Date().getTime() - date.getTime()));
        System.out.println("Size of the map: " + map.size());

    }


    static class Reader extends Thread {
        private int id;
        private int choice;
        private final int COUNTER = 1_000_000;

        Reader(int id, int choice) {
            this.id = id;
            this.choice = choice;
        }

        private void readMap(Integer key) {
            map.get(key);
        }
        private void readConMap(Integer key) {
            conMap.get(key);
        }


        @Override
        public void run() {
            switch (choice){
                case 1:
                    for (int i = (id * COUNTER) - COUNTER; i < id * COUNTER; i++) {
                        readMap(i);
                    }
                    break;
                case 2:
                    for (int i = (id * COUNTER) - COUNTER; i < id * COUNTER; i++) {
                        readConMap(i);
                    }
                    break;
            }
        }
    }

    static class Writer extends Thread {
        private int id;
        private int choice;
        private final int COUNTER = 1_000_000;

        Writer(int id, int choice) {
            this.id = id;
            this.choice = choice;
        }

        private void writeMap(Integer key) {
            map.put(key, 2 * key - 1);
        }
        private void writeConMap(Integer key) {
            conMap.put(key, 2 * key - 1);
        }

        @Override
        public void run() {
            switch (choice) {
                case 1:
                    for (int i = (id * COUNTER) - COUNTER; i < id * COUNTER; i++) {
                        writeMap(i);
                    }
                    break;
                case 2:
                    for (int i = (id * COUNTER) - COUNTER; i < id * COUNTER; i++) {
                        writeConMap(i);
                    }
                    break;
            }
        }
    }
}


