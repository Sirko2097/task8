package task5.service.util;

import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.RecursiveTask;


public class WordsFinder extends RecursiveTask<Boolean> {
    private static Logger logger = Logger.getLogger(WordsFinder.class);
    private File fOut;
    private File file;
    private String firstLet;

    public WordsFinder(File fOut, File file, String firstLet) {
        this.fOut = fOut;
        this.file = file;
        this.firstLet = firstLet;
    }


    @Override
    protected Boolean compute() {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return true;
            }

            logger.info(file.getPath());
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    WordsFinder wordsFinder = new WordsFinder(fOut, file1, firstLet);
                    wordsFinder.fork();
                    wordsFinder.compute();
                } else {
                    if (file1.getName().toLowerCase().endsWith(".txt"));
                }
            }
        }
        return null;
    }

    private void findAndWrite(File file) {
        String fileStr = "";
        try {
            byte[] codes = Files.readAllBytes(file.toPath());
            fileStr = new String(codes);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String[] strings = fileStr.split("\\W+");
        long count = Arrays.stream(strings).filter(s -> s.toLowerCase().startsWith(firstLet.toLowerCase())).count();

        if (count > 0) {
            synchronized (fOut) {
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(fOut, true))) {
                    String write = file.getPath() + " " + count + '\n';
                    writer.write(write);
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

}
