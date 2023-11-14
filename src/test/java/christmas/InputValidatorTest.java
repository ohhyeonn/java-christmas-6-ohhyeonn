package christmas;


import christmas.View.InputValidator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputValidatorTest {

    @Test
    void validateVisitDateTest() {
        int date = 32;
        InputValidator validator = new InputValidator();

        assertThatThrownBy(() -> {
            validator.validateVisitDate(date);
        }).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void validateMenuSetsTest() {
        String[] menuSets = "abcd-1,해산물파스타-2".split(",");
        InputValidator validator = new InputValidator();

        assertThatThrownBy(() -> {
            validator.validateMenuSets(menuSets);
        }).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void validateDuplicatedMenuTest() {
        String[] menusSets = "해산물파스타-2,해산물파스타-1".split(",");
        InputValidator validator = new InputValidator();

        assertThatThrownBy(() -> {
            validator.validateDuplicatedMenu(menusSets);
        }).isInstanceOf(IllegalArgumentException.class);

    }


    @Test
    void validateOnlyDrinkMenuTest() {
        String[] menusSets = "제로콜라-2,레드와인-3".split(",");
        InputValidator validator = new InputValidator();


        assertThatThrownBy(() -> {
            validator.validateOnlyDrinkMenu(menusSets);
        }).isInstanceOf(IllegalArgumentException.class);

    }
    @Test
    void validate20MenuTest() {
        InputValidator validator = new InputValidator();
        String[] menusSets = "해산물파스타-21".split(",");




        assertThatThrownBy(() -> {
            validator.validate20Menu(menusSets);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateLineTest() {
        String line = "";
        InputValidator validator = new InputValidator();


        assertThatThrownBy(() -> {
            validator.validateLine(line);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
