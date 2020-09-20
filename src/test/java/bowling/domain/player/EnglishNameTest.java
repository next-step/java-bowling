package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnglishNameTest {

    @DisplayName("입력값이 3자가 넘어가면 에러 발생")
    @ParameterizedTest
    @ValueSource(strings = {"next", "step"})
    void testNameThreeWord(final String inputText) {
        assertThatThrownBy(() -> {
            new EnglishName(inputText);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력값이 영어가 아니면 에러 발생")
    @ParameterizedTest
    @ValueSource(strings = {"철수", "영희"})
    void testEnglishInput(final String inputText) {
        assertThatThrownBy(() -> {
            new EnglishName(inputText);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName(value = "빈 문자열 또는 null 값을 입력할 경우 에러 발생.")
    @ParameterizedTest
    @NullAndEmptySource
    void emptyOrNull(final String inputText) {
        assertThatThrownBy(() -> {
            new EnglishName(inputText);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
