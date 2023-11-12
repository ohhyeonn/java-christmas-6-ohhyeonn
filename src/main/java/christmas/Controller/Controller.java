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
        OutputView.printEventIntro(date);
        Counter.countMenu(menus);
        OutputView.printMenu();
    }

    public static Integer makeLumpSumBeforeDiscount() {
        Integer LumpSumBeforeDiscount = Counter.countLumpSumBeforeDiscount();
        OutputView.printLumpSumBeforeDiscount(LumpSumBeforeDiscount);
        return LumpSumBeforeDiscount;
    }

    public static Integer makeGiftMenu(Integer lumpSumBeforeDiscount) {
        Integer giftMenuCount = Counter.countGiftMenu(lumpSumBeforeDiscount);
        OutputView.printGiftMenu(giftMenuCount);
        return giftMenuCount;
    }

    public static Integer makeChristmasDiscount(Integer date) {
        Integer christmasDiscount = Counter.countChristmasDiscount(date);
        return christmasDiscount;
    }

    public static Integer makeWeekDayDiscount(Integer date) {
        Integer weekDayDiscount = Counter.countWeekDayDiscount(date);
        return weekDayDiscount;
    }

    public static Integer makeWeekendDiscount(Integer date) {
        Integer weekendDiscount = Counter.countWeekendDiscount(date);
        return weekendDiscount;
    }
}
