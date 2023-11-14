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

}
