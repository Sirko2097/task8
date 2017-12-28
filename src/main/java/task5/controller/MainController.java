package task5.controller;

import org.apache.log4j.Logger;
import task5.service.Model;
import task5.service.util.Input;
import task5.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class MainController {
    private final View VIEW;
    private final Model MODEL;
    private static Logger logger = Logger.getLogger(MainController.class);

    public MainController(View VIEW, Model MODEL) {
        this.VIEW = VIEW;
        this.MODEL = MODEL;
    }

    public void go() {
        while (true) {
            VIEW.printMessage("If you want to quit - input \"0\"");
            String dirPath = Input.inputString(VIEW, "Input path to directory: ");
            if (dirPath.equals("0")) {
                logger.info("Exit");
                System.exit(0);
            }

            File dir = new File(dirPath);

            if (!dir.isDirectory() || !dir.exists()) {
                VIEW.printMessage("Wrong input> \"" + dirPath + "\" Try again.");
                logger.info(dirPath + " is a directory> " + dir.isDirectory());
                logger.info(dirPath + " exists: " + dir.exists());
                continue;
            }

            String firstLet = Input.inputLetter(VIEW, "Input letter: ",
                    "Error in input. Try again.", "[a-zA-Z]");

            File fOut = MODEL.createFile("Test.txt");

            MODEL.handleFile(fOut, dir, firstLet);
            VIEW.printMessage("~~~~~~~~RESULT~~~~~~~~");
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(fOut));

                String str;
                while ((str = reader.readLine()) != null) {
                    VIEW.printMessage(str);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            VIEW.printMessage("~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
}
