package christmas.View;

import camp.nextstep.edu.missionutils.Console;
import christmas.Model.Menu;
import christmas.Model.Receipt;
import java.util.ArrayList;

public class InputView {
    private static final String ERROR_VISIT_DATE_WRONG = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ENTER_VISIT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ENTER_MENUS = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String ERROR_NOT_VALID_MENUS = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final Integer FIRST_INDEX = 0;
    private static final Integer SECOND_INDEX = 1;
    private static final String REG_EXPRESS = "^[가-힣]+-[0-9]+$" ;
    private static final Integer ZERO = 0;
    private static final Integer THIRTYTWO = 32;
    private static final Integer TWENTY = 20;
    private static final String HYPHEN = "-";
    private static final String COMMA = ",";
    private static final String BLANC_STRING = "";
    private static Integer readDate() {
        int date;
        while (true) {
            try {
                printVisitDateInstruction();
                date = getVisitDate();
                validateVisitDate(date);
                break;
            } catch (NumberFormatException e) {
                System.out.println(ERROR_VISIT_DATE_WRONG);
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_VISIT_DATE_WRONG);
            }
        }
        return date;
    }

    private static void validateVisitDate(int date) {
        if (date <= ZERO || date >= THIRTYTWO) {
            throw new IllegalArgumentException();
        }
    }

    private static void printVisitDateInstruction() {
        System.out.println(ENTER_VISIT_DATE);
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
                System.out.println(ERROR_NOT_VALID_MENUS);
            }
        }

    }

    private static void validate20Menu(String[] menusSets) {
        int total = ZERO;
        for(String set : menusSets){
            String[] menuAndCount = set.split(HYPHEN);
            total = total +Integer.parseInt(menuAndCount[SECOND_INDEX]);
        }
        if(total > TWENTY){
            throw new IllegalArgumentException();
        }

    }

    private static void validateOnlyDrinkMenu(String[] menusSets) {
        for(String set : menusSets){
            String[] menuAndCount = set.split(HYPHEN);
            Menu menu = Menu.getMenu(menuAndCount[FIRST_INDEX]);
            if(menu != Menu.ZERO_COLA && menu != Menu.RED_WINE && menu != Menu.CHAMPAGNE && menu != Menu.NONE){
                return ;
            }
        }
        throw new IllegalArgumentException();
    }

    private static void validateDuplicatedMenu(String[] menusSets) {
        ArrayList<String> basket = new ArrayList<>();
        for(String set : menusSets){
            String[] menuAndCount = set.split(HYPHEN);
            String menu = menuAndCount[FIRST_INDEX];
            if(basket.contains(menu)) throw new IllegalArgumentException();
            basket.add(menu);
        }
    }

    private static void validateMenuSets(String[] menuSets) {
        for(String set : menuSets){
            if(!set.matches(REG_EXPRESS)){
                throw new IllegalArgumentException();
            }
            String[] menuAndCount = set.split(HYPHEN);
            validateMenu(menuAndCount[FIRST_INDEX]);
            validateCount(Integer.parseInt(menuAndCount[SECOND_INDEX]));
        }
    }

    private static void validateMenu(String menu) {
        Menu menuName = Menu.getMenu(menu);
        if(menuName == Menu.NONE){
            throw new IllegalArgumentException();
        }
    }

    private static void validateCount(int count) {
        if(count <= ZERO) throw new IllegalArgumentException();
    }

    private static void printMenusInstruction() {
        System.out.println(ENTER_MENUS);
    }

    private static String[] getMenuSets() {
        String line = Console.readLine();
        validateLine(line);
        String[] lines = line.split(COMMA);
        return lines;
    }

    private static void validateLine(String line) {
        if(BLANC_STRING.equals(line)) throw new IllegalArgumentException();
    }

    public static Receipt makeReceipt() {
        return new Receipt(readDate() , readMenu());
    }
}
