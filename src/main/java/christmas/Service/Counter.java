package christmas.Service;

import christmas.Model.Menu;
import christmas.View.OutputView;

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
}
