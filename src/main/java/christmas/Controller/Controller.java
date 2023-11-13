package christmas.Controller;

import christmas.Model.Badge;
import christmas.Model.Discount;
import christmas.Service.Counter;
import christmas.View.InputView;
import christmas.View.OutputView;
import java.util.HashMap;

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

    public static void makeBenefitsDetails(HashMap<Discount , Integer> discounts) {
        OutputView.printBenefitsDetails(discounts);
    }

    public static Integer makeBenefitsDiscount(HashMap<Discount , Integer> discounts) {
        Integer benefitsDiscount = Counter.countBenefitsDiscount(discounts);
        OutputView.printBenefitsDiscount(benefitsDiscount);
        return benefitsDiscount;
    }

    public static void makeEstimatedPaymentAmountAfterDiscount(HashMap<Discount,Integer> discounts, Integer lumpSumBeforeDiscount) {
        Integer estimatedPaymentAmountAfterDiscount = Counter.countEstimatedPaymentAmountAfterDiscount(discounts,lumpSumBeforeDiscount);
        OutputView.printEstimatedPaymentAmountAfterDiscount(estimatedPaymentAmountAfterDiscount);
    }

    public static void makeEventBadge(Integer benefitsDiscount) {
        Badge badge = Counter.countBadge(benefitsDiscount);
        OutputView.printBadge(badge);
    }

    public static HashMap<Discount, Integer> makeDiscount(Integer date, Integer lumpSumBeforeDiscount) {
        HashMap<Discount , Integer> discounts = new HashMap<>();
        discounts.put(Discount.GIFT_MENU_COUNT, Counter.countGiftMenu(lumpSumBeforeDiscount));
        discounts.put(Discount.CHRISTMAS_DISCOUNT , Counter.countChristmasDiscount(date,lumpSumBeforeDiscount));
        discounts.put(Discount.WEEK_DAY_DISCOUNT , Counter.countWeekDayDiscount(date,lumpSumBeforeDiscount));
        discounts.put(Discount.WEEKEND_DISCOUNT , Counter.countWeekendDiscount(date,lumpSumBeforeDiscount));
        discounts.put(Discount.SPECIAL_DISCOUNT , Counter.countSpecialDiscount(date,lumpSumBeforeDiscount));
        return discounts;
    }

    public static void makeGiftMenu(HashMap<Discount, Integer> discounts) {
        OutputView.printGiftMenu(discounts.get(Discount.GIFT_MENU_COUNT));
    }
}
