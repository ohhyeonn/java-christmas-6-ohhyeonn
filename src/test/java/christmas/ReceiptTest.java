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

}
