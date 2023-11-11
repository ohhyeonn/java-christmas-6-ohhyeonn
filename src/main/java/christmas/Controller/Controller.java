package christmas.Controller;

import christmas.Service.Counter;
import christmas.View.InputView;
import christmas.View.OutputView;

public class Controller {


    public static void makeIntroduce() {
        OutputView.printIntro();
    }

    public static Integer makeVisitDate() {
        Integer date = InputView.readDate();
        return date;
    }

    public static String[] makeMenu() {
        String[] menus = InputView.readMenu();
        return menus;

    }

    public static void makeOrder(Integer date, String[] menus) {
        Counter.countMenu(menus);
    }
}
