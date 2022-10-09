package bowling.step2.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CountOfFallenPinsDTOTest {
    public static final CountOfFallenPinsDTO COUNT_OF_FALLEN_PINS_DTO = new CountOfFallenPinsDTO("10");
    private static final String COUNT_OF_FALLEN_PINS_EXCEPTION_MESSAGE = "투구 결과를 잘못 입력하였습니다. 다시 입력해 주세요.";
    
    @Test
    @DisplayName("입력 값 반환 받기")
    void get_result() {
        assertThat(COUNT_OF_FALLEN_PINS_DTO.getCountOfFallenPins()).isEqualTo(10);
    }
    
    @ParameterizedTest(name = "{displayName}")
    @DisplayName("\"\" 입력 시 예외")
    @EmptySource
    void input_empty_exception(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CountOfFallenPinsDTO(input))
                .withMessage(COUNT_OF_FALLEN_PINS_EXCEPTION_MESSAGE);
    }
    
    @ParameterizedTest(name = "{displayName} = {0}")
    @DisplayName("입력 범위 초과 시 예외")
    @ValueSource(strings = {"0", "11"})
    void input_range_exceed_exception(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CountOfFallenPinsDTO(input))
                .withMessage(COUNT_OF_FALLEN_PINS_EXCEPTION_MESSAGE);
    }
    
    @ParameterizedTest(name = "{displayName} = {0}")
    @DisplayName("한글, 영어 입력 시 예외")
    @ValueSource(strings = {"준", "ju"})
    void input_korean_and_english_exception(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CountOfFallenPinsDTO(input))
                .withMessage(COUNT_OF_FALLEN_PINS_EXCEPTION_MESSAGE);
    }
    
    @Test
    @DisplayName("특수 문자 입력 시 예외")
    void input_special_characters_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CountOfFallenPinsDTO("@"))
                .withMessage(COUNT_OF_FALLEN_PINS_EXCEPTION_MESSAGE);
    }
    
    @Test
    @DisplayName("공백 입력 시 예외")
    void input_space_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CountOfFallenPinsDTO(" "))
                .withMessage(COUNT_OF_FALLEN_PINS_EXCEPTION_MESSAGE);
    }
}