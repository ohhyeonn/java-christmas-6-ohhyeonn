package christmas.Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

public class Receipt {
    private Integer visitDate;
    private String[] menuSets;
    private Integer lumpSumBeforeDiscount;
    private Integer giftMenuCount ;
    private Integer christmasDiscount;
    private Integer weekDayDiscount;
    private Integer weekendDiscount;
    private Integer specialDiscount;
    private Integer benefitsDiscount;
    private Integer estimatedPaymentAmountAfterDiscount;
    private Badge badge;

    public Receipt(Integer visitDate, String[] menuSets) {
        this.visitDate = visitDate;
        this.menuSets = menuSets;
        countMenu();
        this.lumpSumBeforeDiscount = countLumpSumBeforeDiscount();
        this.giftMenuCount = countGiftMenu();
        this.christmasDiscount = countChristmasDiscount();
        this.weekDayDiscount = countWeekDayDiscount();
        this.weekendDiscount = countWeekendDiscount();
        this.specialDiscount = countSpecialDiscount();
        this.benefitsDiscount = countBenefitsDiscount();
        this.estimatedPaymentAmountAfterDiscount = countEstimatedPaymentAmountAfterDiscount();
        this.badge = countBadge();
    }
    private Integer countLumpSumBeforeDiscount() {
        Integer lumpSumBeforeDiscount = 0;
        for(Menu menu : Menu.values()){
            int count = menu.getCount();
            if(count != 0){
                lumpSumBeforeDiscount = lumpSumBeforeDiscount + menu.getPrice() * menu.getCount();
            }
        }
        return lumpSumBeforeDiscount;
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




    private Integer countGiftMenu() {
        if(this.lumpSumBeforeDiscount >= 120000){
            return 1;
        }
        return 0;
    }


    private Integer countChristmasDiscount() {
        if(isUnderTenThousands(this.lumpSumBeforeDiscount)){
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


    private Integer countWeekDayDiscount() {
        if(isUnderTenThousands(lumpSumBeforeDiscount)){
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

    private Integer countWeekendDiscount() {
        if(isUnderTenThousands(lumpSumBeforeDiscount)){
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

    private Integer countSpecialDiscount() {
        if(isUnderTenThousands(lumpSumBeforeDiscount)){
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

    private Integer countBenefitsDiscount() {
        return (giftMenuCount * -25000) + christmasDiscount + weekDayDiscount + weekendDiscount + specialDiscount;
    }
    private Integer countEstimatedPaymentAmountAfterDiscount() {
        return lumpSumBeforeDiscount + christmasDiscount + weekDayDiscount + weekendDiscount + specialDiscount;
    }

    private Badge countBadge() {
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






    public Integer getVisitDate() {
        return visitDate;
    }

    public Integer getLumpSumBeforeDiscount() {
        return lumpSumBeforeDiscount;
    }

    public Integer getGiftMenuCount() {
        return giftMenuCount;
    }

    public Integer getChristmasDiscount() {
        return christmasDiscount;
    }

    public Integer getWeekDayDiscount() {
        return weekDayDiscount;
    }

    public Integer getWeekendDiscount() {
        return weekendDiscount;
    }

    public Integer getSpecialDiscount() {
        return specialDiscount;
    }

    public Integer getBenefitsDiscount() {
        return benefitsDiscount;
    }

    public Integer getEstimatedPaymentAmountAfterDiscount() {
        return estimatedPaymentAmountAfterDiscount;
    }

    public Badge getBadge() {
        return badge;
    }
}
