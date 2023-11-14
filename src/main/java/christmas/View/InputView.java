package christmas.View;

import camp.nextstep.edu.missionutils.Console;
import christmas.Model.Receipt;
import christmas.Constant.Constant;

public class InputView {

    private final static InputValidator validator = new InputValidator();

    private static Integer readDate() {
        int date;
        while (true) {
            try {
                printVisitDateInstruction();
                date = getVisitDate();
                validator.validateVisitDate(date);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(Constant.ERROR_VISIT_DATE_WRONG);
            }
        }
        return date;
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
                validator.validateMenuSets(menusSets);
                validator.validateDuplicatedMenu(menusSets);
                validator.validateOnlyDrinkMenu(menusSets);
                validator.validate20Menu(menusSets);
                return menusSets;
            } catch (IllegalArgumentException e) {
                System.out.println(Constant.ERROR_NOT_VALID_MENUS);
            }
        }

    }


    private static void printMenusInstruction() {
        System.out.println(Constant.ENTER_MENUS);
    }

    private static String[] getMenuSets() {
        String line = Console.readLine();
        validator.validateLine(line);
        String[] lines = line.split(Constant.COMMA);
        return lines;
    }


    public static Receipt makeReceipt() {
        return new Receipt(readDate(), readMenu());
    }
}
