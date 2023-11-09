package christmas.View;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String ERROR_ONE_THIRTYONE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static Integer readDate() {
        int date;
        while (true) {
            try {
                date = getVisitDate();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_ONE_THIRTYONE);
            }
        }
        return date;
    }

    private static int getVisitDate() {
        return Integer.parseInt(Console.readLine());
    }
}
