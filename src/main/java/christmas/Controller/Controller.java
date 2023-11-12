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

    public static Integer makeSpecialDiscount(Integer date) {
        Integer specialDiscount = Counter.countSpecialDiscount(date);
        return specialDiscount;
    }

    public static void makeBenefitsDetails(Integer giftMenuCount, Integer christmasDiscount, Integer weekDayDiscount, Integer weekendDiscount, Integer specialDiscount) {
        OutputView.printBenefitsDetails(giftMenuCount,christmasDiscount,weekDayDiscount,weekendDiscount,specialDiscount);
    }

    public static void makeBenefitsDiscount(Integer giftMenuCount, Integer christmasDiscount, Integer weekDayDiscount, Integer weekendDiscount, Integer specialDiscount) {
        Integer benefitsDiscount = Counter.countBenefitsDiscount(giftMenuCount, christmasDiscount, weekDayDiscount, weekendDiscount, specialDiscount);
        OutputView.printBenefitsDiscount(benefitsDiscount);
    }

    public static void makeEstimatedPaymentAmountAfterDiscount(Integer christmasDiscount, Integer weekDayDiscount, Integer weekendDiscount,
            Integer specialDiscount, Integer lumpSumBeforeDiscount) {
        Integer estimatedPaymentAmountAfterDiscount = Counter.countEstimatedPaymentAmountAfterDiscount(christmasDiscount,weekDayDiscount,weekendDiscount,specialDiscount,lumpSumBeforeDiscount);
    }
}
