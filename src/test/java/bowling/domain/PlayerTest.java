package bowling.domain;

import bowling.exception.PlayerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PlayerTest {

    @ParameterizedTest
    @DisplayName("이름이 3개의 영어 글자가 아니면, BadNameException 이 발생한다.")
    @ValueSource(strings = {"세글자", "a", "ab", "abcd"})
    void constructor(String name) {
        assertThatExceptionOfType(PlayerException.class)
                .isThrownBy(() -> new Player(name));
    }
}
