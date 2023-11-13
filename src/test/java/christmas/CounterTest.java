package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import christmas.Model.Menu;
import christmas.Service.Counter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CounterTest {

    @BeforeEach
    void beforeCounterTest() {
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
    void countMenuTest() {
        String line = "양송이수프-1,아이스크림-2";
        String[] sets = line.split(",");

        Counter.countMenu(sets);

        assertThat(Menu.ICE_CREAM.getCount()).isEqualTo(2);
    }

    @Test
    void countLumpSumBeforeDiscountTest(){
        Menu.BBQ_RIBS.setCount(1);
        Menu.ICE_CREAM.setCount(2);

        int lumpSum = Counter.countLumpSumBeforeDiscount();

        assertThat(lumpSum).isEqualTo(64000);
    }


}
