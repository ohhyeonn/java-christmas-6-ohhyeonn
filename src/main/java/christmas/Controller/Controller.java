package christmas.Controller;

import christmas.Model.Badge;
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

    public static Integer makeChristmasDiscount(Integer date, Integer lumpSumBeforeDiscount) {
        Integer christmasDiscount = Counter.countChristmasDiscount(date,lumpSumBeforeDiscount);
        return christmasDiscount;
    }

    public static Integer makeWeekDayDiscount(Integer date, Integer lumpSumBeforeDiscount) {
        Integer weekDayDiscount = Counter.countWeekDayDiscount(date,lumpSumBeforeDiscount);
        return weekDayDiscount;
    }

    public static Integer makeWeekendDiscount(Integer date, Integer lumpSumBeforeDiscount) {
        Integer weekendDiscount = Counter.countWeekendDiscount(date,lumpSumBeforeDiscount);
        return weekendDiscount;
    }

    public static Integer makeSpecialDiscount(Integer date, Integer lumpSumBeforeDiscount) {
        Integer specialDiscount = Counter.countSpecialDiscount(date,lumpSumBeforeDiscount);
        return specialDiscount;
    }

    public static void makeBenefitsDetails(Integer giftMenuCount, Integer christmasDiscount, Integer weekDayDiscount, Integer weekendDiscount, Integer specialDiscount) {
        OutputView.printBenefitsDetails(giftMenuCount,christmasDiscount,weekDayDiscount,weekendDiscount,specialDiscount);
    }

    public static Integer makeBenefitsDiscount(Integer giftMenuCount, Integer christmasDiscount, Integer weekDayDiscount, Integer weekendDiscount, Integer specialDiscount) {
        Integer benefitsDiscount = Counter.countBenefitsDiscount(giftMenuCount, christmasDiscount, weekDayDiscount, weekendDiscount, specialDiscount);
        OutputView.printBenefitsDiscount(benefitsDiscount);
        return benefitsDiscount;
    }

    public static void makeEstimatedPaymentAmountAfterDiscount(Integer christmasDiscount, Integer weekDayDiscount, Integer weekendDiscount,
            Integer specialDiscount, Integer lumpSumBeforeDiscount) {
        Integer estimatedPaymentAmountAfterDiscount = Counter.countEstimatedPaymentAmountAfterDiscount(christmasDiscount,weekDayDiscount,weekendDiscount,specialDiscount,lumpSumBeforeDiscount);
        OutputView.printEstimatedPaymentAmountAfterDiscount(estimatedPaymentAmountAfterDiscount);
    }

    public static void makeEventBadge(Integer benefitsDiscount) {
        Badge badge = Counter.countBadge(benefitsDiscount);
        OutputView.printBadge(badge);
    }
}
