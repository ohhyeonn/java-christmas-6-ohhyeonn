package christmas.Controller;

import christmas.View.InputView;
import christmas.View.OutputView;

public class Controller {


    public static void makeIntroduce() {
        OutputView.printIntro();
    }

    public static void makeVisitDate() {
        Integer date = InputView.readDate();
    }

    public static void makeMenu() {
        InputView.readMenu();


    }
}
