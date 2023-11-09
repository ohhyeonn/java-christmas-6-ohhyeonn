package christmas.View;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static Integer readDate() {
        return getVisitDate();
    }

    private static int getVisitDate() {
        return Integer.parseInt(Console.readLine());
    }
}
