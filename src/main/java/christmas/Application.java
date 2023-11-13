    package christmas;

    import christmas.Controller.Controller;
    import christmas.Model.Discount;
    import christmas.Model.Menu;
    import java.util.HashMap;

    public class Application {
        public static void main(String[] args) {
            Controller.makeIntroduce();
            Integer date = Controller.makeVisitDate();
            String[] menus = Controller.makeMenu();
            Controller.makeOrder(date , menus);
            Integer lumpSumBeforeDiscount = Controller.makeLumpSumBeforeDiscount();
            HashMap<Discount, Integer> discounts = Controller.makeDiscount(date,lumpSumBeforeDiscount);
            Controller.makeGiftMenu(discounts);
            Controller.makeBenefitsDetails(discounts);
            Integer benefitsDiscount = Controller.makeBenefitsDiscount(discounts);
            Controller.makeEstimatedPaymentAmountAfterDiscount(discounts,lumpSumBeforeDiscount);
            Controller.makeEventBadge(benefitsDiscount);
        }
    }
