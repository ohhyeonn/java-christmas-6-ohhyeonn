package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Model.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @DisplayName("산타뱃지 출력 테스트")
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:산타","초코케이크-5:트리","초코케이크-3:별","초코케이크-1:없음"},delimiter = ':')
    void 산타뱃지_출력(String input , String expected) {
        assertSimpleTest(() -> {
            run("3", input);
            assertThat(output()).contains(
                    "<12월 이벤트 배지>" + LINE_SEPARATOR + expected
            );
        });
    }


    @ParameterizedTest
    @DisplayName("할인후 예상 결제금액 출력 테스트")
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:135,754원","해산물파스타-3,아이스크림-2:108,754원"},delimiter = ':')
    void 할인후_예상_결제금액_출력(String input , String expected) {
        assertSimpleTest(() -> {
            run("3", input);
            assertThat(output()).contains(
                    "<할인 후 예상 결제 금액>" + LINE_SEPARATOR + expected
            );
        });
    }

    @ParameterizedTest
    @DisplayName("총혜택 금액 출력 테스트")
    @CsvSource(value = {"티본스테이크-2,아이스크림-4:-35,292원","티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:-31,246원"},delimiter = ':')
    void 총혜택_금액_출력(String input , String expected) {
        assertSimpleTest(() -> {
            run("3", input);
            assertThat(output()).contains(
                    "<총혜택 금액>" + LINE_SEPARATOR + expected
            );
        });
    }

    @Test
    void 혜택내역_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<혜택 내역>" + LINE_SEPARATOR + "크리스마스 디데이 할인: -1,200원"
                            + LINE_SEPARATOR + "평일 할인: -4,046원"
                            + LINE_SEPARATOR + "특별 할인: -1,000원"
                            + LINE_SEPARATOR + "증정 이벤트: -25,000원"
            );
        });
    }

    @ParameterizedTest
    @DisplayName("증정메뉴 출력 테스트")
    @CsvSource(value = {"티본스테이크-2,아이스크림-4:샴페인 1개","바비큐립-1,초코케이크-2,제로콜라-1:없음" , "해산물파스타-5:샴페인 1개"},delimiter = ':')
    void 증정메뉴_출력(String input , String expected) {
        assertSimpleTest(() -> {
            run("3", input);
            assertThat(output()).contains(
                    "<증정 메뉴>" + LINE_SEPARATOR + expected
            );
        });
    }

    @ParameterizedTest
    @DisplayName("할인전 총 주문 금액 출력 테스트")
    @CsvSource(value = {"티본스테이크-2,아이스크림-4:130,000원","해산물파스타-2:70,000원"},delimiter = ':')
    void 할인전_총_주문금액_출력(String input , String expected) {
        assertSimpleTest(() -> {
            run("3", input);
            assertThat(output()).contains(
                    "<할인 전 총주문 금액>" + LINE_SEPARATOR + expected
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

    @ParameterizedTest
    @DisplayName("메뉴 주문이 20개가 넘는지 확인")
    @ValueSource(strings = {"해산물파스타-21", "샴페인-11,아이스크림-10" , "티본스테이크-10,해산물파스타-5,아이스크림-6"})
    void 개수_입력_총_메뉴의_주문이_20개를_넘는지_검증(String input) {
        assertSimpleTest(() -> {
            runException("3", input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @DisplayName("개수 입력이 1이상의 숫자인지 검증")
    @ValueSource(strings = {"해산물파스타-0", "샴페인--1" , "제로콜라-2,레드와인-0"})
    void 개수_입력_1이상의_숫자인지_검증(String input) {
        assertSimpleTest(() -> {
            runException("3", input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }


    @ParameterizedTest
    @DisplayName("메뉴 입력 음료만 입력 하였는지 검증")
    @ValueSource(strings = {"제로콜라-2,레드와인-1", "샴페인-1" , "레드와인-1,샴페인-1,제로콜라-1"})
    void 메뉴_입력_음료만_입력_하였는지_검증(String input) {
        assertSimpleTest(() -> {
            runException("3", input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @DisplayName("메뉴 입력 중복 검증")
    @ValueSource(strings = {"해산물파스타-2,해산물파스타-1", "아이스크림-1,해산물파스타-2,아이스크림-2"})
    void 메뉴_입력_중복_메뉴_입력_검증(String input) {
        assertSimpleTest(() -> {
            runException("3", input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @DisplayName("메뉴 입력 메뉴에 있는지 검증")
    @ValueSource(strings = {"알리오올리오-2", "불닭볶음면-4"})
    void 메뉴_입력_메뉴에_있는지_검증(String input) {
        assertSimpleTest(() -> {
            runException("31", input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @DisplayName("메뉴와 개수 입력 형식에 맞는지 검증")
    @ValueSource(strings = {"해산물파스타_2", "해산물파스타--2" , "크리스마스파스타*3,바비큐립*2"})
    void 메뉴와_개수_입력_형식_검증(String input) {
        assertSimpleTest(() -> {
            runException("31", input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("메뉴와 개수 입력 공백 검증")
    void 메뉴와_개수_입력_공백_검증() {
        assertSimpleTest(() -> {
            runException("31", " ");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @DisplayName("방문날짜 입력 1에서 31인지 검증")
    @ValueSource(strings = {"0", "32" , "99"})
    void 방문날짜_입력_1에서_31인지_검증(String input) {
        assertSimpleTest(() -> {
            runException(input);
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

    @ParameterizedTest
    @DisplayName("혜택 내역 없음 출력")
    @ValueSource(strings = {"타파스-1,제로콜라-1","양송이수프-1,제로콜라-1"})
    void 혜택_내역_없음_출력(String input) {
        assertSimpleTest(() -> {
            run("26", input);
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @ParameterizedTest
    @DisplayName("방문일자 숫자 아닌 입력 예외 테스트")
    @ValueSource(strings = {"ㅁ" , "a" , " ", "-"})
    void 날짜_예외_테스트(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @DisplayName("주문 예외 테스트")
    @ValueSource(strings = {"ㅁ" , "a" , " ", "-" , "제로콜라-a"})
    void 주문_예외_테스트(String input) {
        assertSimpleTest(() -> {
            runException("3", input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
