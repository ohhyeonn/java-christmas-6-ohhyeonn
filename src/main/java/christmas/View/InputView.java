package christmas.View;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String ERROR_VISIT_DATE_ONE_THIRTYONE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ERROR_VISIT_DATE_IS_NUMBER = "[ERROR] 방문일자는 숫자여야 합니다.";
    private static final String ENTER_VISIT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static Integer readDate() {
        int date;
        while (true) {
            try {
                printVisitDateInstruction();
                date = getVisitDate();
                validateVisitDate(date);
                break;
            } catch (NumberFormatException e) {
                System.out.println(ERROR_VISIT_DATE_IS_NUMBER);
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_VISIT_DATE_ONE_THIRTYONE);
            }
        }
        return date;
    }

    private static void validateVisitDate(int date) {
        if (date <= 0 || date >= 32) {
            throw new IllegalArgumentException();
        }
    }

    private static void printVisitDateInstruction() {
        System.out.println(ENTER_VISIT_DATE);
    }

    private static int getVisitDate() {
        return Integer.parseInt(Console.readLine());
    }

    public static void readMenu() {
        String[] menus = getMenus();
    }

    private static String[] getMenus() {
        String[] line = Console.readLine().split(",");
        return line;
    }
}
