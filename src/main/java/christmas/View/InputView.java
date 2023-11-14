package christmas.View;

import camp.nextstep.edu.missionutils.Console;
import christmas.Model.Menu;
import christmas.Model.Receipt;
import java.util.ArrayList;
import christmas.Constant.Constant;

public class InputView {

    private static Integer readDate() {
        int date;
        while (true) {
            try {
                printVisitDateInstruction();
                date = getVisitDate();
                validateVisitDate(date);
                break;
            } catch (NumberFormatException e) {
                System.out.println(Constant.ERROR_VISIT_DATE_WRONG);
            } catch (IllegalArgumentException e) {
                System.out.println(Constant.ERROR_VISIT_DATE_WRONG);
            }
        }
        return date;
    }

    private static void validateVisitDate(int date) {
        if (date <= Constant.ZERO || date >= Constant.THIRTYTWO) {
            throw new IllegalArgumentException();
        }
    }

    private static void printVisitDateInstruction() {
        System.out.println(Constant.ENTER_VISIT_DATE);
    }

    private static int getVisitDate() {
        return Integer.parseInt(Console.readLine());
    }

    private static String[] readMenu() {
        while (true) {
            try {
                printMenusInstruction();
                String[] menusSets = getMenuSets();
                validateMenuSets(menusSets);
                validateDuplicatedMenu(menusSets);
                validateOnlyDrinkMenu(menusSets);
                validate20Menu(menusSets);
                return menusSets;
            } catch (IllegalArgumentException e) {
                System.out.println(Constant.ERROR_NOT_VALID_MENUS);
            }
        }

    }

    private static void validate20Menu(String[] menusSets) {
        int total = Constant.ZERO;
        for(String set : menusSets){
            String[] menuAndCount = set.split(Constant.HYPHEN);
            total = total +Integer.parseInt(menuAndCount[Constant.SECOND_INDEX]);
        }
        if(total > Constant.TWENTY){
            throw new IllegalArgumentException();
        }

    }

    private static void validateOnlyDrinkMenu(String[] menusSets) {
        for(String set : menusSets){
            String[] menuAndCount = set.split(Constant.HYPHEN);
            Menu menu = Menu.getMenu(menuAndCount[Constant.FIRST_INDEX]);
            if(menu != Menu.ZERO_COLA && menu != Menu.RED_WINE && menu != Menu.CHAMPAGNE && menu != Menu.NONE){
                return ;
            }
        }
        throw new IllegalArgumentException();
    }

    private static void validateDuplicatedMenu(String[] menusSets) {
        ArrayList<String> basket = new ArrayList<>();
        for(String set : menusSets){
            String[] menuAndCount = set.split(Constant.HYPHEN);
            String menu = menuAndCount[Constant.FIRST_INDEX];
            if(basket.contains(menu)) throw new IllegalArgumentException();
            basket.add(menu);
        }
    }

    private static void validateMenuSets(String[] menuSets) {
        for(String set : menuSets){
            if(!set.matches(Constant.REG_EXPRESS)){
                throw new IllegalArgumentException();
            }
            String[] menuAndCount = set.split(Constant.HYPHEN);
            validateMenu(menuAndCount[Constant.FIRST_INDEX]);
            validateCount(Integer.parseInt(menuAndCount[Constant.SECOND_INDEX]));
        }
    }

    private static void validateMenu(String menu) {
        Menu menuName = Menu.getMenu(menu);
        if(menuName == Menu.NONE){
            throw new IllegalArgumentException();
        }
    }

    private static void validateCount(int count) {
        if(count <= Constant.ZERO) throw new IllegalArgumentException();
    }

    private static void printMenusInstruction() {
        System.out.println(Constant.ENTER_MENUS);
    }

    private static String[] getMenuSets() {
        String line = Console.readLine();
        validateLine(line);
        String[] lines = line.split(Constant.COMMA);
        return lines;
    }

    private static void validateLine(String line) {
        if(Constant.BLANC_STRING.equals(line)) throw new IllegalArgumentException();
    }

    public static Receipt makeReceipt() {
        return new Receipt(readDate() , readMenu());
    }
}
