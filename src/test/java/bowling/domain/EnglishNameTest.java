package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnglishNameTest {

    @DisplayName("입력값이 3자가 넘어가면 에러 발생")
    @ParameterizedTest
    @ValueSource(strings = {"next", "step"})
    public void testNameThreeWord(final String inputText) {
        assertThatThrownBy(() -> {
            new EnglishName(inputText);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
