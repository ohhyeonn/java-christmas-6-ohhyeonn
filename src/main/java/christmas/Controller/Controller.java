package christmas.Controller;

import christmas.Model.Receipt;
import christmas.View.OutputView;
import christmas.View.InputView;

public class Controller {


    public static void makeIntroduce() {
        OutputView.printIntro();
    }

    public static Receipt makeVisitDateMenus() {
        return InputView.makeOrder();

    }

    public static void makeResultView(Receipt receipt) {
        OutputView.printEventIntro(receipt);
        OutputView.printMenu();

        OutputView.printLumpSumBeforeDiscount(receipt);

        OutputView.printGiftMenu(receipt);

        OutputView.printBenefitsDetails(receipt);

        OutputView.printBenefitsDiscount(receipt);

        OutputView.printEstimatedPaymentAmountAfterDiscount(receipt);

        OutputView.printBadge(receipt);
    }
}
