package christmas.Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

public class Receipt {
    private Integer visitDate;
    private String[] menuSets;

    public Receipt(Integer visitDate, String[] menuSets) {
        this.visitDate = visitDate;
        this.menuSets = menuSets;
        countMenu();
    }
    private void countMenu() {
        for(String menuset : menuSets){
            String[] menuAndCount = menuset.split("-");
            String menu = menuAndCount[0];
            int count = Integer.parseInt(menuAndCount[1]);
            Menu orderedMenu = Menu.getMenu(menu);
            orderedMenu.setCount(count);
        }
    }

    public Integer countGiftMenu() {
        if(Menu.countLumpSumBeforeDiscount() >= 120000){
            return 1;
        }
        return 0;
    }


    public Integer countChristmasDiscount() {
        if(isUnderTenThousands(Menu.countLumpSumBeforeDiscount())){
            return 0;
        }
        LocalDate christmasDate = LocalDate.of(2023,12,25);
        LocalDate visitDate = LocalDate.of(2023,12,this.visitDate);
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


    public Integer countWeekDayDiscount() {
        if(isUnderTenThousands(Menu.countLumpSumBeforeDiscount())){
            return 0;
        }
        boolean isWeekDay = isWeekDay(visitDate);
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

    public Integer countWeekendDiscount() {
        if(isUnderTenThousands(Menu.countLumpSumBeforeDiscount())){
            return 0;
        }
        boolean isWeekDay = isWeekDay(visitDate);
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

    public Integer countSpecialDiscount() {
        if(isUnderTenThousands(Menu.countLumpSumBeforeDiscount())){
            return 0;
        }
        if(visitDate == 3 || visitDate == 10 || visitDate == 17 || visitDate == 24 || visitDate == 25 || visitDate == 31) return -1000;
        return 0;
    }


    private static boolean isUnderTenThousands(Integer lumpSumBeforeDiscount) {
        boolean isOverTenThousands = lumpSumBeforeDiscount < 10000;
        return isOverTenThousands;
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

    public Integer countBenefitsDiscount() {
        return (countGiftMenu() * -25000) + countChristmasDiscount() + countWeekDayDiscount() + countWeekendDiscount() + countSpecialDiscount();
    }
    public Integer countEstimatedPaymentAmountAfterDiscount() {
        return Menu.countLumpSumBeforeDiscount() + countChristmasDiscount() + countWeekDayDiscount() + countWeekendDiscount() + countSpecialDiscount();
    }
    public Integer getVisitDate() {
        return visitDate;
    }
}
