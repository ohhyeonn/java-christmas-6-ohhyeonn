package christmas;

import christmas.Model.Menu;
import christmas.Model.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ReceiptTest {


    @BeforeEach
    void 각각의_테스트_하기전() {
        Menu.MUSHROOM_SOUP.setCount(0);
        Menu.TAPAS.setCount(0);
        Menu.CAESAR_SALAD.setCount(0);
        Menu.T_BONE_STEAK.setCount(0);
        Menu.BBQ_RIBS.setCount(0);
        Menu.SEAFOOD_PASTA.setCount(0);
        Menu.CHRISTMAS_PASTA.setCount(0);
        Menu.CHOCOLATE_CAKE.setCount(0);
        Menu.ICE_CREAM.setCount(0);
        Menu.ZERO_COLA.setCount(0);
        Menu.RED_WINE.setCount(0);
        Menu.CHAMPAGNE.setCount(0);
    }

    @Test
    void visitDateTest() {
        Receipt receipt = new Receipt(12 , "해산물파스타-2,아이스크림-1".split(","));

        // 방문일 12 일
        int visitDate = receipt.getVisitDate();

        assertThat(visitDate).isEqualTo(12);
    }

    @Test
    void lumpSumBeforeDiscountTest() {
        Receipt receipt = new Receipt(12 , "해산물파스타-2,아이스크림-2,제로콜라-2".split(","));

        // 아이스크림2개 10000 , 해산물파스타2개 70000 , 제로콜라2개 6000
        // 총 86000
        int lumpSumBeforeDiscount = receipt.getLumpSumBeforeDiscount();

        assertThat(lumpSumBeforeDiscount).isEqualTo(86000);
    }

    @Test
    void giftMenuCountTest() {
        Receipt receipt = new Receipt(3 , "티본스테이크-2,아이스크림-2,제로콜라-2".split(","));

        // 아이스크림2개 10000 , 티본스테이크2개 110000 , 제로콜라2개 6000
        // 총 126000 -> 사은품 증정 1개
        int giftMenuCount = receipt.getGiftMenuCount();

        assertThat(giftMenuCount).isEqualTo(1);
    }




    @Test
    void christmasDiscountTest() {
        Receipt receipt = new Receipt(19 , "크리스마스파스타-3,아이스크림-3".split(","));

        // 12/1 1000원할인 , 12/19 2800원할인
        int christmasDiscount = receipt.getChristmasDiscount();

        assertThat(christmasDiscount).isEqualTo(-2800);
    }


    @Test
    void weekDayDiscountTest() {
        Receipt receipt = new Receipt(21 , "바비큐립-2,레드와인-2,아이스크림-2".split(","));

        // 12/21 목요일 (평일)
        // 디저트메뉴 아이스크림 2개 할인 4046원
        int weekDayDiscount = receipt.getWeekDayDiscount();

        assertThat(weekDayDiscount).isEqualTo(-4046);
    }

    @Test
    void weekendDiscountTest() {
        Receipt receipt = new Receipt(22 , "해산물파스타-1,바비큐립-1,레드와인-2,아이스크림-2".split(","));

        // 12/22 금요일 주말
        // 메인메뉴 해산물파스타 1개 바비큐립 1개 할인
        int weekendDiscount = receipt.getWeekendDiscount();

        assertThat(weekendDiscount).isEqualTo(-4046);
    }


    @Test
    void specialDiscountTest() {
        Receipt receipt = new Receipt(24 , "바비큐립-1,아이스크림-2".split(","));

        // 12/24 별표 표시 있음
        // 1000원 할인
        int specialDiscount = receipt.getSpecialDiscount();

        assertThat(specialDiscount).isEqualTo(-1000);
    }


    @Test
    void benefitsDiscountTest() {
        Receipt receipt = new Receipt(18 , "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1".split(","));

        // 12/18 월요일
        // 크리스마스 디데이 할인: -2,700원
        // 평일 할인: -4,046원
        // 증정 이벤트: -25,000원
        int benefitsDiscount = receipt.getBenefitsDiscount();

        assertThat(benefitsDiscount).isEqualTo(-31746);
    }

}
