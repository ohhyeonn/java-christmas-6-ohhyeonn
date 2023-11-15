package christmas;

import christmas.Model.Badge;
import christmas.Model.Menu;
import christmas.Model.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    @ParameterizedTest
    @DisplayName("방문일자 입력에 맞게 가져 오는지 테스트")
    @CsvSource({"12,12", "29,29", "1,1"})
    void visitDateTest(String input , String expected) {
        Receipt receipt = new Receipt(Integer.parseInt(input), "해산물파스타-2,아이스크림-1".split(","));

        int visitDate = receipt.getVisitDate();

        assertThat(visitDate).isEqualTo(Integer.parseInt(expected));
    }

    @ParameterizedTest
    @DisplayName("할인전 주문금액 올바른 계산으로 가져오는지 테스트")
    @CsvSource(value = {"해산물파스타-2,아이스크림-2,제로콜라-2:86000" , "티본스테이크-3,제로콜라-3:174000"},delimiter = ':')
    void lumpSumBeforeDiscountTest(String input , String expected) {
        Receipt receipt = new Receipt(12, input.split(","));

        // 할인전 주문금액
        int lumpSumBeforeDiscount = Menu.countLumpSumBeforeDiscount();

        assertThat(lumpSumBeforeDiscount).isEqualTo(Integer.parseInt(expected));
    }

    @ParameterizedTest
    @DisplayName("증정상품 12만원 경계로 잘 가져오는지 테스트")
    @CsvSource(value = {"아이스크림-2,티본스테이크-2:1" , "티본스테이크-2,아이스크림-1:0"},delimiter = ':')
    void giftMenuCountTest(String input , String expected) {
        Receipt receipt = new Receipt(3, input.split(","));

        // 증정상품 갯수
        int giftMenuCount = receipt.countGiftMenu();

        assertThat(giftMenuCount).isEqualTo(Integer.parseInt(expected));
    }


    @ParameterizedTest
    @DisplayName("크리스마스 디데이 할인 맞게 계산하는지 테스트")
    @CsvSource(value = {"19:-2800" , "5:-1400" , "26:0"},delimiter = ':')
    void christmasDiscountTest(String input , String expected) {
        Receipt receipt = new Receipt(Integer.parseInt(input), "크리스마스파스타-3,아이스크림-3".split(","));

        // 크리스마스 할인
        int christmasDiscount = receipt.countChristmasDiscount();

        assertThat(christmasDiscount).isEqualTo(Integer.parseInt(expected));
    }


    @ParameterizedTest
    @DisplayName("평일 할인 맞게 계산하는지 테스트")
    @CsvSource(value = {"바비큐립-2,레드와인-2,아이스크림-2:-4046" , "해산물파스타-1,아이스크림-1:-2023" , "크리스마스파스타-1,티본스테이크-1:0"},delimiter = ':')
    void weekDayDiscountTest(String input , String expected) {
        Receipt receipt = new Receipt(21, input.split(","));

        // 12/21 목요일 (평일)
        // 평일 할인
        int weekDayDiscount = receipt.countWeekDayDiscount();

        assertThat(weekDayDiscount).isEqualTo(Integer.parseInt(expected));
    }

    @ParameterizedTest
    @DisplayName("주말 할인 맞게 계산하는지 테스트")
    @CsvSource(value = {"바비큐립-1,레드와인-2,아이스크림-2:-2023" , "해산물파스타-1,바비큐립-1,레드와인-2,아이스크림-2:-4046" , "아이스크림-5:0"},delimiter = ':')
    void weekendDiscountTest(String input , String expected) {
        Receipt receipt = new Receipt(22, input.split(","));

        // 12/22 금요일 주말
        // 주말 할인
        int weekendDiscount = receipt.countWeekendDiscount();

        assertThat(weekendDiscount).isEqualTo(Integer.parseInt(expected));
    }


    @ParameterizedTest
    @DisplayName("특별 할인 맞게 계산하는지 테스트")
    @CsvSource(value = {"24:-1000" , "7:0"},delimiter = ':')
    void specialDiscountTest(String input , String expected) {
        Receipt receipt = new Receipt(Integer.parseInt(input), "바비큐립-1,아이스크림-2".split(","));

        // 특별 할인
        int specialDiscount = receipt.countSpecialDiscount();

        assertThat(specialDiscount).isEqualTo(Integer.parseInt(expected));
    }


    @ParameterizedTest
    @DisplayName("총헤택 금액 맞게 계산하는지 테스트")
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:-31746" , "크리스마스파스타-2,해산물파스타-1,초코케이크-1:-4723"},delimiter = ':')
    void benefitsDiscountTest(String input , String expected) {
        Receipt receipt = new Receipt(18, input.split(","));

        // 12/18 월요일 평
        // 총혜택
        int benefitsDiscount = receipt.countBenefitsDiscount();

        assertThat(benefitsDiscount).isEqualTo(Integer.parseInt(expected));
    }


    @ParameterizedTest
    @DisplayName("할인후 결제예상 금액 맞게 계산하는지 테스트")
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:135754" , "크리스마스파스타-2,해산물파스타-1,초코케이크-1:95777"},delimiter = ':')
    void estimatedPaymentAmountAfterDiscountTest(String input , String expected) {
        Receipt receipt = new Receipt(3, input.split(","));

        // 12/3 일요일
        int estimatedPaymentAmountAfterDiscount = receipt.countEstimatedPaymentAmountAfterDiscount();

        assertThat(estimatedPaymentAmountAfterDiscount).isEqualTo(Integer.parseInt(expected));
    }


    @ParameterizedTest
    @DisplayName("뱃지 금액에따라 맞게 계산하는지 테스트")
    @CsvSource(value = {"-20000:SANTA","-21000:SANTA","-19000:TREE","-10000:TREE","-9000:STAR","-5000:STAR","-4900:NONE","-500:NONE"},delimiter = ':')
    void badgeTest(String input , String expected) {
        Integer benefitDiscount = Integer.parseInt(input);

        // 20000원 이상 산타
        Badge badge = Badge.countBadge(benefitDiscount);

        assertThat(badge).isEqualTo(Badge.valueOf(expected));
    }
}
