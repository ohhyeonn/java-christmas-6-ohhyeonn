package christmas.Service;

import christmas.Model.Menu;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

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

    public static Integer countGiftMenu(Integer lumpSumBeforeDiscount) {
        if(lumpSumBeforeDiscount >= 120000){
            return 1;
        }
        return 0;
    }

    public static Integer countChristmasDiscount(Integer date) {
        LocalDate christmasDate = LocalDate.of(2023,12,25);
        LocalDate visitDate = LocalDate.of(2023,12,date);
        Period diff = Period.between(visitDate , christmasDate);
        return calculateChristmasDiscount(diff.getDays());
    }

    private static Integer calculateChristmasDiscount(int days) {
        if(days >= 0){
            int totalDiscount = -3400;
            return totalDiscount + 100 * days;
        }
        return 0;
    }


    public static Integer countWeekDayDiscount(Integer date) {
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

    private static boolean isWeekDay(Integer date) {
        boolean isWeekDay = false;
        LocalDate visitDate = LocalDate.of(2023 , 12 , date);
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
        if(dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.TUESDAY || dayOfWeek == DayOfWeek.WEDNESDAY || dayOfWeek == DayOfWeek.THURSDAY){
            isWeekDay = true;
        }
        return isWeekDay;
    }

    public static Integer countWeekendDiscount(Integer date) {
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

    public static Integer countSpecialDiscount(Integer date) {
        if(date == 3 || date == 10 || date == 17 || date == 24 || date == 25 || date == 31) return -1000;
        return 0;
    }
}
