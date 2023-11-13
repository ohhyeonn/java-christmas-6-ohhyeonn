package christmas.Service;

import christmas.Model.Badge;
import christmas.Model.Discount;
import christmas.Model.Menu;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

public class Counter {
    public static void countMenu(String[] menus) {
        for(String menuset : menus){
            String[] menuAndCount = menuset.split("-");
            String menu = menuAndCount[0];
            int count = Integer.parseInt(menuAndCount[1]);
            Menu orderedMenu = Menu.getMenu(menu);
            orderedMenu.setCount(count);
        }


    }

    public static Integer countLumpSumBeforeDiscount() {
        Integer lumpSumBeforeDiscount = 0;
        for(Menu menu : Menu.values()){
            int count = menu.getCount();
            if(count != 0){
                lumpSumBeforeDiscount = lumpSumBeforeDiscount + menu.getPrice() * menu.getCount();
            }
        }
        return lumpSumBeforeDiscount;
    }

    private static boolean isUnderTenThousands(Integer lumpSumBeforeDiscount) {
        boolean isOverTenThousands = lumpSumBeforeDiscount < 10000;
        return isOverTenThousands;
    }

    private static Integer calculateChristmasDiscount(int days) {
        if(days >= 0){
            int totalDiscount = -3400;
            return totalDiscount + 100 * days;
        }
        return 0;
    }

    private static boolean isWeekDay(Integer date) {
        boolean isWeekDay = false;
        LocalDate visitDate = LocalDate.of(2023 , 12 , date);
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
        if(dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.TUESDAY || dayOfWeek == DayOfWeek.WEDNESDAY || dayOfWeek == DayOfWeek.THURSDAY){
            isWeekDay = true;
        }
        return isWeekDay;
    }

    public static Integer countBenefitsDiscount(HashMap<Discount , Integer> discounts) {
        Integer benefitsDiscount = (discounts.get(Discount.GIFT_MENU_COUNT) * -25000) + discounts.get(Discount.CHRISTMAS_DISCOUNT) + discounts.get(Discount.WEEK_DAY_DISCOUNT) + discounts.get(Discount.WEEKEND_DISCOUNT) + discounts.get(Discount.SPECIAL_DISCOUNT);

        return benefitsDiscount;
    }

    public static Integer countEstimatedPaymentAmountAfterDiscount(HashMap<Discount,Integer> discounts , Integer lumpSumBeforeDiscount) {
        return lumpSumBeforeDiscount + discounts.get(Discount.CHRISTMAS_DISCOUNT) + discounts.get(Discount.WEEK_DAY_DISCOUNT) + discounts.get(Discount.WEEKEND_DISCOUNT) + discounts.get(Discount.SPECIAL_DISCOUNT);


    }

    public static Badge countBadge(Integer benefitsDiscount) {
        if(benefitsDiscount <= Badge.SANTA.getUnder()){
            return Badge.SANTA;
        }
        if(benefitsDiscount <= Badge.TREE.getUnder()){
            return Badge.TREE;
        }
        if(benefitsDiscount <= Badge.STAR.getUnder()){
            return Badge.STAR;
        }

        return Badge.NONE;
    }

    private static Integer countGiftMenu(Integer lumpSumBeforeDiscount) {
        if(lumpSumBeforeDiscount >= 120000){
            return 1;
        }
        return 0;
    }

    private static Integer countChristmasDiscount(Integer date, Integer lumpSumBeforeDiscount) {
        if(isUnderTenThousands(lumpSumBeforeDiscount)){
            return 0;
        }
        LocalDate christmasDate = LocalDate.of(2023,12,25);
        LocalDate visitDate = LocalDate.of(2023,12,date);
        Period diff = Period.between(visitDate , christmasDate);
        return calculateChristmasDiscount(diff.getDays());
    }

    private static Integer countWeekDayDiscount(Integer date, Integer lumpSumBeforeDiscount) {
        if(isUnderTenThousands(lumpSumBeforeDiscount)){
            return 0;
        }
        boolean isWeekDay = isWeekDay(date);
        if(!isWeekDay){
            return 0;
        }
        Integer weekDayDiscount = 0;
        for(Menu menu : Menu.values()){
            if(menu == Menu.CHOCOLATE_CAKE || menu == Menu.ICE_CREAM){
                weekDayDiscount = weekDayDiscount - (2023 * menu.getCount());
            }
        }
        return weekDayDiscount;
    }

    private static Integer countWeekendDiscount(Integer date, Integer lumpSumBeforeDiscount) {
        if(isUnderTenThousands(lumpSumBeforeDiscount)){
            return 0;
        }
        boolean isWeekDay = isWeekDay(date);
        if(isWeekDay){
            return 0;
        }
        Integer weekendDiscount = 0;
        for(Menu menu : Menu.values()){
            if(menu == Menu.T_BONE_STEAK || menu == Menu.BBQ_RIBS || menu == Menu.SEAFOOD_PASTA || menu == Menu.CHRISTMAS_PASTA){
                weekendDiscount = weekendDiscount - (2023 * menu.getCount());
            }
        }
        return weekendDiscount;
    }

    private static Integer countSpecialDiscount(Integer date, Integer lumpSumBeforeDiscount) {
        if(isUnderTenThousands(lumpSumBeforeDiscount)){
            return 0;
        }
        if(date == 3 || date == 10 || date == 17 || date == 24 || date == 25 || date == 31) return -1000;
        return 0;
    }

    public static HashMap<Discount, Integer> countDiscount(Integer date, Integer lumpSumBeforeDiscount) {
        HashMap<Discount , Integer> discounts = new HashMap<>();
        discounts.put(Discount.GIFT_MENU_COUNT, countGiftMenu(lumpSumBeforeDiscount));
        discounts.put(Discount.CHRISTMAS_DISCOUNT , countChristmasDiscount(date,lumpSumBeforeDiscount));
        discounts.put(Discount.WEEK_DAY_DISCOUNT , countWeekDayDiscount(date,lumpSumBeforeDiscount));
        discounts.put(Discount.WEEKEND_DISCOUNT , countWeekendDiscount(date,lumpSumBeforeDiscount));
        discounts.put(Discount.SPECIAL_DISCOUNT , countSpecialDiscount(date,lumpSumBeforeDiscount));
        return discounts;
    }
}
