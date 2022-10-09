package bowling.step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerNameTest {
    private static final String PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE = "플레이어 이름 형식이 잘못되었습니다. 다시 입력해주세요.";
    
    @ParameterizedTest(name = "{displayName}")
    @DisplayName("\"\" 입력 시 예외")
    @EmptySource
    void input_empty_exception(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PlayerName(input))
                .withMessage(PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE);
    }
    
    @Test
    @DisplayName("숫자 입력 시 예외")
    void input_number_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PlayerName("A2C"))
                .withMessage(PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE);
    }
}