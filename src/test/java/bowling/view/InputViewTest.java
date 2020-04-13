package bowling.view;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {
    private static InputView inputView = InputView.getInputView();

    @DisplayName("영어로 된 3글자 이름이 아닐 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = { "123", "홍길동", "TEST" })
    void validateUserName(String userName) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            inputView.validateUserName(userName);
        });
    }
}
