package christmas.View;

import christmas.Constant.Constant;
import christmas.Model.Menu;
import java.util.ArrayList;

public class InputValidator {

    public void validateVisitDate(int date) {
        if (date <= Constant.ZERO || date >= Constant.THIRTYTWO) {
            throw new IllegalArgumentException();
        }
    }

    public void validateMenuSets(String[] menuSets) {
        for (String set : menuSets) {
            if (!set.matches(Constant.REG_EXPRESS)) {
                throw new IllegalArgumentException();
            }
            String[] menuAndCount = set.split(Constant.HYPHEN);
            validateMenu(menuAndCount[Constant.FIRST_INDEX]);
            validateCount(Integer.parseInt(menuAndCount[Constant.SECOND_INDEX]));
        }
    }

    private void validateMenu(String menu) {
        Menu menuName = Menu.getMenu(menu);
        if (menuName == Menu.NONE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateCount(int count) {
        if (count <= Constant.ZERO) {
            throw new IllegalArgumentException();
        }
    }


    public void validateDuplicatedMenu(String[] menusSets) {
        ArrayList<String> basket = new ArrayList<>();
        for (String set : menusSets) {
            String[] menuAndCount = set.split(Constant.HYPHEN);
            String menu = menuAndCount[Constant.FIRST_INDEX];
            if (basket.contains(menu)) {
                throw new IllegalArgumentException();
            }
            basket.add(menu);
        }
    }

    public void validateOnlyDrinkMenu(String[] menusSets) {
        for (String set : menusSets) {
            String[] menuAndCount = set.split(Constant.HYPHEN);
            Menu menu = Menu.getMenu(menuAndCount[Constant.FIRST_INDEX]);
            if (menu != Menu.ZERO_COLA && menu != Menu.RED_WINE && menu != Menu.CHAMPAGNE
                    && menu != Menu.NONE) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public void validate20Menu(String[] menusSets) {
        int total = Constant.ZERO;
        for (String set : menusSets) {
            String[] menuAndCount = set.split(Constant.HYPHEN);
            total = total + Integer.parseInt(menuAndCount[Constant.SECOND_INDEX]);
        }
        if (total > Constant.TWENTY) {
            throw new IllegalArgumentException();
        }

    }


    public void validateLine(String line) {
        if (Constant.BLANC_STRING.equals(line)) {
            throw new IllegalArgumentException();
        }
    }
}
