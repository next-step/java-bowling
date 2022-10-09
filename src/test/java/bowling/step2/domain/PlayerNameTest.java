package bowling.step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerNameTest {
    @ParameterizedTest(name = "{displayName}")
    @DisplayName("\"\" 입력 시 예외")
    @EmptySource
    void input_empty_exception(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PlayerName(input))
                .withMessage("올바른 이름이 아닙니다. 다시 입력해주세요");
    }
    
}