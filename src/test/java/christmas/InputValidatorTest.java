package christmas;


import christmas.View.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputValidatorTest {
    private static final String COMMA = ",";

    @ParameterizedTest
    @DisplayName("방문일자가 1에서 31까지 인지 확인")
    @ValueSource(ints = {32, 0})
    void validateVisitDateTest(Integer input) {
        int date = input;
        InputValidator validator = new InputValidator();

        assertThatThrownBy(() -> {
            validator.validateVisitDate(date);
        }).isInstanceOf(IllegalArgumentException.class);

    }

    @ParameterizedTest
    @DisplayName("메뉴형식이 올바른지 확인")
    @ValueSource(strings = {"abcd-1,해산물파스타-2", "아이스크림-1,asdf,해산물파스타-2","아이스크림-asd,해산물파스타-2"})
    void validateMenuSetsTest(String input) {
        String[] menuSets = input.split(COMMA);
        InputValidator validator = new InputValidator();

        assertThatThrownBy(() -> {
            validator.validateMenuSets(menuSets);
        }).isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @DisplayName("메뉴주문이 중복되는지 확인")
    @ValueSource(strings = {"해산물파스타-2,해산물파스타-1", "아이스크림-1,해산물파스타-2,아이스크림-2"})
    void validateDuplicatedMenuTest(String input) {
        String[] menusSets = input.split(COMMA);
        InputValidator validator = new InputValidator();

        assertThatThrownBy(() -> {
            validator.validateDuplicatedMenu(menusSets);
        }).isInstanceOf(IllegalArgumentException.class);

    }


    @ParameterizedTest
    @DisplayName("음료만 주문하는지 확인")
    @ValueSource(strings = {"제로콜라-2,레드와인-1", "샴페인-1" , "레드와인-1,샴페인-1,제로콜라-1"})
    void validateOnlyDrinkMenuTest(String input) {
        String[] menusSets = input.split(COMMA);
        InputValidator validator = new InputValidator();

        assertThatThrownBy(() -> {
            validator.validateOnlyDrinkMenu(menusSets);
        }).isInstanceOf(IllegalArgumentException.class);

    }

    @ParameterizedTest
    @DisplayName("메뉴 주문이 20개가 넘는지 확인")
    @ValueSource(strings = {"해산물파스타-21", "샴페인-11,아이스크림-10" , "티본스테이크-10,해산물파스타-5,아이스크림-6"})
    void validate20MenuTest(String input) {
        InputValidator validator = new InputValidator();
        String[] menusSets = input.split(COMMA);

        assertThatThrownBy(() -> {
            validator.validate20Menu(menusSets);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("인풋 라인이 공백인지 확인")
    void validateLineTest() {
        String line = "";
        InputValidator validator = new InputValidator();

        assertThatThrownBy(() -> {
            validator.validateLine(line);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
