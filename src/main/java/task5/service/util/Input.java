package task5.service.util;

import task5.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static int inputScanInt(View view, String message, String error, String pattern) {
        view.printMessage(message);
        while (!scanner.hasNextInt()) {
            view.printMessage(error + message);
            scanner.next();
        }

        return scanner.nextInt();
    }

    public static String inputLetter(View view, String message, String error, String pattern) {
        view.printMessage(message);
        scanner.useDelimiter("\n");

        while (!scanner.hasNextInt()) {
            view.printMessage(error + message);
            scanner.next();
        }

        return scanner.next(pattern);
    }

    public static String inputString(View view, String message) {
        String str = "";

        view.printMessage(message);
        try {
            str = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return str;
    }
}
