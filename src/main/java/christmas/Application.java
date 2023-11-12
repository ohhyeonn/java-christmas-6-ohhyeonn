package christmas;

import christmas.Controller.Controller;
import christmas.Model.Menu;

public class Application {
    public static void main(String[] args) {
        Controller.makeIntroduce();
        Integer date = Controller.makeVisitDate();
        String[] menus = Controller.makeMenu();
        Controller.makeOrder(date , menus);
        Integer lumpSumBeforeDiscount = Controller.makeLumpSumBeforeDiscount();
        Integer giftMenuCount = Controller.makeGiftMenu(lumpSumBeforeDiscount);
        Integer christmasDiscount = Controller.makeChristmasDiscount(date,lumpSumBeforeDiscount);
        Integer weekDayDiscount = Controller.makeWeekDayDiscount(date,lumpSumBeforeDiscount);
        Integer weekendDiscount = Controller.makeWeekendDiscount(date,lumpSumBeforeDiscount);
        Integer specialDiscount = Controller.makeSpecialDiscount(date,lumpSumBeforeDiscount);
        Controller.makeBenefitsDetails(giftMenuCount ,christmasDiscount,weekDayDiscount,weekendDiscount,specialDiscount);
        Integer benefitsDiscount = Controller.makeBenefitsDiscount(giftMenuCount ,christmasDiscount,weekDayDiscount,weekendDiscount,specialDiscount);
        Controller.makeEstimatedPaymentAmountAfterDiscount(lumpSumBeforeDiscount,christmasDiscount,weekDayDiscount,weekendDiscount,specialDiscount);
        Controller.makeEventBadge(benefitsDiscount);
    }
}
