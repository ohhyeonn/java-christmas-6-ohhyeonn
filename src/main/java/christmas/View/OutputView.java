package christmas.View;

import christmas.Model.Menu;
import christmas.Model.Receipt;

public class OutputView {
    private static final String INTRO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ORDERED_MENU = "<주문 메뉴>";
    private static final String DECEMBER = "12월 ";
    private static final String EVENT_INTRO = "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String BLANC = " ";
    private static final String COUNT = "개";
    private static final String LUMP_SUM_BEFORE_DISCOUNT = "<할인 전 총주문 금액>";
    private static final String SUM_FORMAT = "%,d원\n";
    private static final String GIFT_MENU = "<증정 메뉴>";
    private static final String CHAMPAGNE = "샴페인 ";
    private static final String NOTHING = "없음";
    private static final String BENEFITS_LIST = "<혜택 내역>";
    private static final String CHRISTMAS_D_DAY_DISCOUNT_FORMAT = "크리스마스 디데이 할인: %,d원\n";
    private static final String WEEKDAY_DISCOUNT_FORMAT = "평일 할인: %,d원\n";
    private static final String WEEKEND_DISCOUNT_FORMAT = "주말 할인: %,d원\n";
    private static final String SPECIAL_DISCOUNT_FORMAT = "특별 할인: %,d원\n";
    private static final String GIFT_EVENT_PRICE = "증정 이벤트: -25,000원";
    private static final String BENEFITS_SUM_PRICE = "<총혜택 금액>";
    private static final String ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE = "<12월 이벤트 배지>";
    public static void printIntro() {
        System.out.println(INTRO);
    }


    public static void printMenu() {
        System.out.println();
        System.out.println(ORDERED_MENU);
        for(Menu menu : Menu.values()){
            int count = menu.getCount();
            if(count != 0){
                System.out.print(menu.getName()+BLANC);
                System.out.println(count+COUNT);
            }

        }

    }

    public static void printEventIntro(Receipt receipt) {
        System.out.println(DECEMBER+ receipt.getVisitDate()+EVENT_INTRO );
    }

    public static void printLumpSumBeforeDiscount(Receipt receipt) {
        System.out.println();
        System.out.println(LUMP_SUM_BEFORE_DISCOUNT);
        System.out.printf(SUM_FORMAT, receipt.getLumpSumBeforeDiscount());
    }

    public static void printGiftMenu(Receipt receipt) {
        System.out.println();
        System.out.println(GIFT_MENU);
        if(receipt.getGiftMenuCount() > 0){
            System.out.println(CHAMPAGNE+receipt.getGiftMenuCount()+COUNT);
            return ;
        }
        System.out.println(NOTHING);
    }

    public static void printBenefitsDetails(Receipt receipt) {
        printBenefitsList();

        printChristmasDiscount(receipt.getChristmasDiscount());
        printWeekDayDiscount(receipt.getWeekDayDiscount());
        printWeekendDiscount(receipt.getWeekendDiscount());
        printSpecialDiscount(receipt.getSpecialDiscount());
        printGiftEvent(receipt.getGiftMenuCount());
        printBenefitsNothing(receipt);


    }

    private static void printBenefitsList() {
        System.out.println();
        System.out.println(BENEFITS_LIST);
    }

    private static void printBenefitsNothing(Receipt receipt) {
        if(receipt.getChristmasDiscount() == 0 && receipt.getWeekDayDiscount() == 0 && receipt.getWeekendDiscount() == 0 && receipt.getSpecialDiscount()
                == 0 && receipt.getGiftMenuCount() == 0){
            System.out.println(NOTHING);
        }
    }

    private static void printGiftEvent(Integer giftMenuCount) {
        if(giftMenuCount > 0){
            System.out.println(GIFT_EVENT_PRICE);
        }
    }

    private static void printSpecialDiscount(Integer specialDiscount) {
        if(specialDiscount < 0){
            System.out.printf(SPECIAL_DISCOUNT_FORMAT, specialDiscount);
        }
    }

    private static void printWeekendDiscount(Integer weekendDiscount) {
        if(weekendDiscount < 0){
            System.out.printf(WEEKEND_DISCOUNT_FORMAT, weekendDiscount);
        }
    }

    private static void printWeekDayDiscount(Integer weekDayDiscount) {
        if(weekDayDiscount < 0){
            System.out.printf(WEEKDAY_DISCOUNT_FORMAT, weekDayDiscount);
        }
    }

    private static void printChristmasDiscount(Integer christmasDiscount) {
        if(christmasDiscount < 0){
            System.out.printf(CHRISTMAS_D_DAY_DISCOUNT_FORMAT, christmasDiscount);
        }
    }

    public static void printBenefitsDiscount(Receipt receipt) {
        System.out.println();
        System.out.println(BENEFITS_SUM_PRICE);
        if(receipt.getBenefitsDiscount() < 0){
            System.out.printf(SUM_FORMAT,receipt.getBenefitsDiscount());
            return;
        }
        System.out.println(NOTHING);
    }

    public static void printEstimatedPaymentAmountAfterDiscount(Receipt receipt) {
        System.out.println();
        System.out.println(ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT);
        System.out.printf(SUM_FORMAT, receipt.getEstimatedPaymentAmountAfterDiscount());
    }

    public static void printBadge(Receipt receipt) {
        System.out.println();
        System.out.println(EVENT_BADGE);
        System.out.println(receipt.getBadge().getName());
    }
}
