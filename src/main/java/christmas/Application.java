package christmas;

import christmas.Controller.Controller;
import christmas.Model.Receipt;

public class Application {

    public static void main(String[] args) {
        Controller.makeIntroduce();
        Receipt receipt = Controller.makeVisitDateMenus();
        Controller.makeResultView(receipt);

    }
}
