package bowling.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"test", "testt", "teste"})
    @DisplayName("이름이 3글자 이상 들어오면 Exception이 발생해야 한다.")
    void nameLengthExceptionTest(String input) {

        // when & then
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> Name.of(input))
            .withMessageMatching("이름은 3글자 이하로 입력되어야 한다.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이름이 공백 null이 들어오면 Exception이 발생해야 한다.")
    void nameNullAndEmptyExceptionTest(String input) {

        // when & then
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> Name.of(input))
            .withMessageMatching("이름은 반드시 제공되어야 한다.");
    }

}