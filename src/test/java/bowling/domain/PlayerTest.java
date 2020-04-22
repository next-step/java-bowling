package bowling.domain;

import bowling.domain.exception.IncorrectNameArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PlayerTest {
    @DisplayName("이름은 영문 세글자여야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "ABC", "Abc"})
    void init(String name) {
        new Player(name);
    }

    @DisplayName("이름이 영문 세글자가 아니면 에러가 난다")
    @ParameterizedTest
    @ValueSource(strings = {"ab", "abcd", "김보람"})
    void errorName(String name) {
        assertThatExceptionOfType(IncorrectNameArgumentException.class)
                .isThrownBy(() -> new Player(name));
    }
}
