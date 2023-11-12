package christmas.Service;

import christmas.Model.Menu;
import java.time.LocalDateTime;
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
        LocalDateTime christmasDate = LocalDateTime.of(2023 , 12 , 25 , 0,0,0);
        LocalDateTime visitDate = LocalDateTime.of(2023 , 12 , date ,0 , 0 , 0);
        Period diff = Period.between(visitDate.toLocalDate() , christmasDate.toLocalDate());
        return calculateChristmasDiscount(diff.getDays());
    }

    private static Integer calculateChristmasDiscount(int days) {
        if(days >= 0){
            int totalDiscount = -3400;
            return totalDiscount + 100 * days;
        }
        return 0;
    }
}
