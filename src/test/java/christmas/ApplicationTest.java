package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Model.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();


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
    void 혜택내역_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<혜택 내역>" + LINE_SEPARATOR+ "크리스마스 디데이 할인: -1,200원"
                            + LINE_SEPARATOR+ "평일 할인: -4,046원"
                            + LINE_SEPARATOR+ "특별 할인: -1,000원"
                            + LINE_SEPARATOR+ "증정 이벤트: -25,000원"
            );
        });
    }
    @Test
    void 증정메뉴_출력() {
        assertSimpleTest(() -> {
            run("3", "해산물파스타-5");
            assertThat(output()).contains(
                    "<증정 메뉴>" + LINE_SEPARATOR+ "샴페인 1개"
            );
        });
    }
    @Test
    void 할인전_총_주문금액_출력() {
        assertSimpleTest(() -> {
            run("3", "해산물파스타-2");
            assertThat(output()).contains(
                    "<할인 전 총주문 금액>" + LINE_SEPARATOR+ "70,000원"
            );
        });
    }
    @Test
    void 주문메뉴_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "티본스테이크 1개",
                    "바비큐립 1개",
                    "초코케이크 2개",
                    "제로콜라 1개"
            );
        });
    }

    @Test
    void 개수_입력_총_메뉴의_주문이_20개를_넘는지_검증() {
        assertSimpleTest(() -> {
            runException("3" , "해산물파스타-21");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }
    @Test
    void 개수_입력_1이상의_숫자인지_검증() {
        assertSimpleTest(() -> {
            runException("3" , "제로콜라-2,레드와인-0");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }


    @Test
    void 메뉴_입력_음료만_입력_하였는지_검증() {
        assertSimpleTest(() -> {
            runException("3" , "제로콜라-2,레드와인-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }
    @Test
    void 메뉴_입력_중복_메뉴_입력_검증() {
        assertSimpleTest(() -> {
            runException("3" , "해산물파스타-2,해산물파스타-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }
    @Test
    void 메뉴_입력_메뉴에_있는지_검증() {
        assertSimpleTest(() -> {
            runException("31" , "알리오올리오-2");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴와_개수_입력_형식_검증() {
        assertSimpleTest(() -> {
            runException("31" , "해산물파스타_2");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }
    @Test
    void 메뉴와_개수_입력_공백_검증() {
        assertSimpleTest(() -> {
            runException("31" , " ");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 방문날짜_입력_1에서_31인지_검증() {
        assertSimpleTest(() -> {
            runException("32");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }


    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
