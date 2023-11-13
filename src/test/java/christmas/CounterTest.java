package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.Model.Discount;
import christmas.Model.Menu;
import christmas.Service.Counter;

import java.util.HashMap;
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

        //비비큐립1개 54,000 , 아이스크림2개 10000
        assertThat(lumpSum).isEqualTo(64000);
    }

    @Test
    void countBenefitsDiscountTest(){
        //티본스테이크 2개 = 110,000 //비비큐립 1개 = 54,000 //아이스크림 2개 = 10,000 //총 = 174,000
        Menu.BBQ_RIBS.setCount(1);
        Menu.ICE_CREAM.setCount(2);
        Menu.T_BONE_STEAK.setCount(2);
        int date = 3;
        int lumpSumBeforeDiscount = 174000;
        HashMap<Discount, Integer> discounts = Counter.countDiscount(date , lumpSumBeforeDiscount);
        //크리스마스 디데이 할인: -1,200원 //평일 할인: -4,046원 //특별 할인: -1,000원 //증정 이벤트: -25,000원 //총 -31,246원
        Integer benefitsDiscount = Counter.countBenefitsDiscount(discounts);

        assertThat(benefitsDiscount).isEqualTo(-31246);
    }
}
