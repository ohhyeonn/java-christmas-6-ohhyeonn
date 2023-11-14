package christmas.Model;

import christmas.Constant.Constant;
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
        for (String menuset : menuSets) {
            String[] menuAndCount = menuset.split("-");
            String menu = menuAndCount[Constant.FIRST_INDEX];
            int count = Integer.parseInt(menuAndCount[Constant.SECOND_INDEX]);
            Menu orderedMenu = Menu.getMenu(menu);
            orderedMenu.setCount(count);
        }
    }

    public Integer countGiftMenu() {
        if (Menu.countLumpSumBeforeDiscount() >= Constant.HUNDRED_TWENTY_THOUSAND) {
            return Constant.ONE;
        }
        return Constant.ZERO;
    }


    public Integer countChristmasDiscount() {
        if (isUnderTenThousands(Menu.countLumpSumBeforeDiscount())) {
            return Constant.ZERO;
        }
        LocalDate christmasDate = LocalDate.of(Constant.TWO_THOUSAND_TWENTY_THREE, Constant.TWELVE,
                Constant.TWENTY_FIVE);
        LocalDate visitDate = LocalDate.of(Constant.TWO_THOUSAND_TWENTY_THREE, Constant.TWELVE,
                this.visitDate);
        Period diff = Period.between(visitDate, christmasDate);
        return calculateChristmasDiscount(diff.getDays());
    }

    private static Integer calculateChristmasDiscount(int days) {
        if (days >= Constant.ZERO) {
            int totalDiscount = Constant.MINUS_THREE_THOUSAND_FOUR_HUNDRED;
            return totalDiscount + Constant.ONE_HUNDRED * days;
        }
        return Constant.ZERO;
    }


    public Integer countWeekDayDiscount() {
        if (isUnderTenThousands(Menu.countLumpSumBeforeDiscount())) {
            return Constant.ZERO;
        }
        boolean isWeekDay = isWeekDay(visitDate);
        if (!isWeekDay) {
            return Constant.ZERO;
        }
        Integer weekDayDiscount = Constant.ZERO;
        for (Menu menu : Menu.values()) {
            if (menu == Menu.CHOCOLATE_CAKE || menu == Menu.ICE_CREAM) {
                weekDayDiscount =
                        weekDayDiscount - (Constant.TWO_THOUSAND_TWENTY_THREE * menu.getCount());
            }
        }
        return weekDayDiscount;
    }

    public Integer countWeekendDiscount() {
        if (isUnderTenThousands(Menu.countLumpSumBeforeDiscount())) {
            return Constant.ZERO;
        }
        boolean isWeekDay = isWeekDay(visitDate);
        if (isWeekDay) {
            return Constant.ZERO;
        }
        Integer weekendDiscount = Constant.ZERO;
        for (Menu menu : Menu.values()) {
            if (menu == Menu.T_BONE_STEAK || menu == Menu.BBQ_RIBS || menu == Menu.SEAFOOD_PASTA
                    || menu == Menu.CHRISTMAS_PASTA) {
                weekendDiscount =
                        weekendDiscount - (Constant.TWO_THOUSAND_TWENTY_THREE * menu.getCount());
            }
        }
        return weekendDiscount;
    }

    public Integer countSpecialDiscount() {
        if (isUnderTenThousands(Menu.countLumpSumBeforeDiscount())) {
            return Constant.ZERO;
        }
        if (visitDate == Constant.THREE || visitDate == Constant.TEN
                || visitDate == Constant.SEVENTEEN || visitDate == Constant.TWENTY_FOUR
                || visitDate == Constant.TWENTY_FIVE || visitDate == Constant.THIRTY_ONE) {
            return Constant.MINUS_ONE_THOUSAND;
        }
        return Constant.ZERO;
    }


    private static boolean isUnderTenThousands(Integer lumpSumBeforeDiscount) {
        boolean isOverTenThousands = lumpSumBeforeDiscount < Constant.TEN_THOUSAND;
        return isOverTenThousands;
    }

    private static boolean isWeekDay(Integer date) {
        boolean isWeekDay = false;
        LocalDate visitDate = LocalDate.of(Constant.TWO_THOUSAND_TWENTY_THREE, Constant.TWELVE,
                date);
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.MONDAY
                || dayOfWeek == DayOfWeek.TUESDAY || dayOfWeek == DayOfWeek.WEDNESDAY
                || dayOfWeek == DayOfWeek.THURSDAY) {
            isWeekDay = true;
        }
        return isWeekDay;
    }

    public Integer countBenefitsDiscount() {
        return (countGiftMenu() * Constant.MINUS_TWENTY_FIVE_THOUSAND) + countChristmasDiscount()
                + countWeekDayDiscount() + countWeekendDiscount() + countSpecialDiscount();
    }

    public Integer countEstimatedPaymentAmountAfterDiscount() {
        return Menu.countLumpSumBeforeDiscount() + countChristmasDiscount() + countWeekDayDiscount()
                + countWeekendDiscount() + countSpecialDiscount();
    }

    public Integer getVisitDate() {
        return visitDate;
    }
}
