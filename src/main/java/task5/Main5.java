package task5;

import task5.controller.MainController;
import task5.service.Model;
import task5.view.View;

public class Main5 {
    public static void main(String[] args) {
        new MainController(new View(), new Model()).go();
    }
}
