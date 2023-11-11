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
}
