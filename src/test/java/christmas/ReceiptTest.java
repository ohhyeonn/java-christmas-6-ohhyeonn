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

        int visitDate = receipt.getVisitDate();

        assertThat(visitDate).isEqualTo(12);
    }


}
