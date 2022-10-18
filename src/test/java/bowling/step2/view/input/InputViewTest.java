package bowling.step2.view.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class InputViewTest {
    private static final String EXCEPTION_MESSAGE = "올바른 입력 값이 아닙니다. 다시 입력해 주세요.";
    
    @ParameterizedTest(name = "{displayName} : {0}")
    @DisplayName("플레이어 수 입력")
    @ValueSource(strings = {"1", "9"})
    void count_of_player_input(String input) {
        assertThat(InputView.inputCountOfPlayer(input)).isEqualTo(Integer.parseInt(input));
    }
    
    @ParameterizedTest(name = "{displayName} : {0}")
    @DisplayName("플레이어 수 입력 - null or \"\" 입력 시 예외")
    @NullAndEmptySource
    void count_of_player_input_null_or_empty_exception(String input) {
        assertThatThrownBy(() -> InputView.inputCountOfPlayer(input))
                .isInstanceOf(RuntimeException.class);
    }
    
    @ParameterizedTest(name = "{displayName} : {0}")
    @DisplayName("플레이어 수 입력 - 범위 벗어날 시 예외")
    @ValueSource(strings = {"0", "10"})
    void count_of_player_input_exceeded_range_exception(String input) {
        assertThatThrownBy(() -> InputView.inputCountOfPlayer(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(EXCEPTION_MESSAGE);
    }
    
    @ParameterizedTest(name = "{displayName} : {0}")
    @DisplayName("플레이어 수 입력 - 한.영 입력 시 예외")
    @ValueSource(strings = {"a", "가"})
    void count_of_player_input_not_number_exception(String input) {
        assertThatThrownBy(() -> InputView.inputCountOfPlayer(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(EXCEPTION_MESSAGE);
    }
    
    @Test
    @DisplayName("플레이어 수 입력 - 특수문자 입력 시 예외")
    void count_of_player_input_special_characters_exception() {
        assertThatThrownBy(() -> InputView.inputCountOfPlayer("@"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(EXCEPTION_MESSAGE);
    }
    
    @Test
    @DisplayName("플레이어 수 입력 - 특수문자 입력 시 예외")
    void count_of_player_input_space_exception() {
        assertThatThrownBy(() -> InputView.inputCountOfPlayer(" "))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(EXCEPTION_MESSAGE);
    }
}