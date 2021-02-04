package bowling.domain.user;

import bowling.bowlingexception.InvalidNameFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {

    @Test
    @DisplayName("일반적인 상황에 대한 테스트")
    void happyPath() {
        User user = new User("ABC");
    }

    @Test
    @DisplayName("Null 테스트")
    void testNull() {
        assertThatThrownBy(() -> new User(null))
                .isInstanceOf(InvalidNameFormatException.class);
    }

    @Test
    @DisplayName("empty string")
    void emptyString() {
        assertThatThrownBy(() -> new User(""))
                .isInstanceOf(InvalidNameFormatException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"AB", "FEFE"})
    @DisplayName("3글자가 아닌 문자열 테스트")
    void notThree(String input) {
        assertThatThrownBy(() -> new User(input))
                .isInstanceOf(InvalidNameFormatException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"AB1", "고De", "가나다"})
    @DisplayName("영어 문자 이외 다른 글자를 포함하고 있을 때")
    void testWithInvalidCharacter(String input) {
        assertThatThrownBy(() -> new User(input))
                .isInstanceOf(InvalidNameFormatException.class);
    }
}
