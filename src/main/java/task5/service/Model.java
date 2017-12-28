package task5.service;

import task5.service.util.WordsFinder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Model {
    public File createFile(String str) {
        File fOut = new File(str);
        if (fOut.exists()) {
            try {
                new FileWriter(fOut).close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return fOut;
    }

    public void handleFile(File fOut, File dir, String firstLetter) {
        ForkJoinPool pool = new ForkJoinPool(4);
        boolean flag = false;
        flag = pool.invoke(new WordsFinder(fOut, dir, firstLetter));
        while (pool.getActiveThreadCount() != 0);
    }
}
