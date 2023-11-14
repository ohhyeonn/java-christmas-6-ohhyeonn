package christmas.View;

import christmas.Constant.Constant;
import christmas.Model.Badge;
import christmas.Model.Menu;
import christmas.Model.Receipt;

public class OutputView {
    public static void printIntro() {
        System.out.println(Constant.INTRO);
    }


    public static void printMenu() {
        System.out.println();
        System.out.println(Constant.ORDERED_MENU);
        for(Menu menu : Menu.values()){
            int count = menu.getCount();
            if(count != Constant.ZERO){
                System.out.print(menu.getName()+Constant.BLANC);
                System.out.println(count+Constant.COUNT);
            }

        }

    }

    public static void printEventIntro(Receipt receipt) {
        System.out.println(Constant.DECEMBER+ receipt.getVisitDate()+Constant.EVENT_INTRO );
    }

    public static void printLumpSumBeforeDiscount(Receipt receipt) {
        System.out.println();
        System.out.println(Constant.LUMP_SUM_BEFORE_DISCOUNT);
        System.out.printf(Constant.SUM_FORMAT, Menu.countLumpSumBeforeDiscount());
    }

    public static void printGiftMenu(Receipt receipt) {
        System.out.println();
        System.out.println(Constant.GIFT_MENU);
        if(receipt.countGiftMenu() > Constant.ZERO){
            System.out.println(Constant.CHAMPAGNE+receipt.countGiftMenu()+Constant.COUNT);
            return ;
        }
        System.out.println(Constant.NOTHING);
    }

    public static void printBenefitsDetails(Receipt receipt) {
        printBenefitsList();

        printChristmasDiscount(receipt.countChristmasDiscount());
        printWeekDayDiscount(receipt.countWeekDayDiscount());
        printWeekendDiscount(receipt.countWeekendDiscount());
        printSpecialDiscount(receipt.countSpecialDiscount());
        printGiftEvent(receipt.countGiftMenu());
        printBenefitsNothing(receipt);


    }

    private static void printBenefitsList() {
        System.out.println();
        System.out.println(Constant.BENEFITS_LIST);
    }

    private static void printBenefitsNothing(Receipt receipt) {
        if(receipt.countChristmasDiscount() == Constant.ZERO && receipt.countWeekDayDiscount() == Constant.ZERO && receipt.countWeekendDiscount() == Constant.ZERO && receipt.countSpecialDiscount()
                == Constant.ZERO && receipt.countGiftMenu() == Constant.ZERO){
            System.out.println(Constant.NOTHING);
        }
    }

    private static void printGiftEvent(Integer giftMenuCount) {
        if(giftMenuCount > Constant.ZERO){
            System.out.println(Constant.GIFT_EVENT_PRICE);
        }
    }

    private static void printSpecialDiscount(Integer specialDiscount) {
        if(specialDiscount < Constant.ZERO){
            System.out.printf(Constant.SPECIAL_DISCOUNT_FORMAT, specialDiscount);
        }
    }

    private static void printWeekendDiscount(Integer weekendDiscount) {
        if(weekendDiscount < Constant.ZERO){
            System.out.printf(Constant.WEEKEND_DISCOUNT_FORMAT, weekendDiscount);
        }
    }

    private static void printWeekDayDiscount(Integer weekDayDiscount) {
        if(weekDayDiscount < Constant.ZERO){
            System.out.printf(Constant.WEEKDAY_DISCOUNT_FORMAT, weekDayDiscount);
        }
    }

    private static void printChristmasDiscount(Integer christmasDiscount) {
        if(christmasDiscount < Constant.ZERO){
            System.out.printf(Constant.CHRISTMAS_D_DAY_DISCOUNT_FORMAT, christmasDiscount);
        }
    }

    public static void printBenefitsDiscount(Receipt receipt) {
        System.out.println();
        System.out.println(Constant.BENEFITS_SUM_PRICE);
        if(receipt.countBenefitsDiscount() < Constant.ZERO){
            System.out.printf(Constant.SUM_FORMAT,receipt.countBenefitsDiscount());
            return;
        }
        System.out.println(Constant.NOTHING);
    }

    public static void printEstimatedPaymentAmountAfterDiscount(Receipt receipt) {
        System.out.println();
        System.out.println(Constant.ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT);
        System.out.printf(Constant.SUM_FORMAT, receipt.countEstimatedPaymentAmountAfterDiscount());
    }

    public static void printBadge(Receipt receipt) {
        System.out.println();
        System.out.println(Constant.EVENT_BADGE);
        System.out.println(Badge.countBadge(receipt.countBenefitsDiscount()).getName());
    }
}
