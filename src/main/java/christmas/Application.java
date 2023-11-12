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
        Integer christmasDiscount = Controller.makeChristmasDiscount(date);
        Integer weekDayDiscount = Controller.makeWeekDayDiscount(date);
        Integer weekendDiscount = Controller.makeWeekendDiscount(date);
        Integer specialDiscount = Controller.makeSpecialDiscount(date);
        Controller.makeBenefitsDetails(giftMenuCount ,christmasDiscount,weekDayDiscount,weekendDiscount,specialDiscount);
    }
}
