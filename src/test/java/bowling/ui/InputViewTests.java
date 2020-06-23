package bowling.ui;

import bowling.ui.exceptions.InvalidPlayerNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTests {
    @DisplayName("입력된 이름이 3글자가 아니면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = { "a", "aa", "aaaa", "   " })
    void validationNameTest(String invalidName) {
        assertThatThrownBy(() -> InputView.validateUserName(invalidName))
                .isInstanceOf(InvalidPlayerNameException.class);
    }
}
