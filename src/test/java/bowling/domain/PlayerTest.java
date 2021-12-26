package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PlayerTest {
    @DisplayName("플레이어 생성 성공")
    @ParameterizedTest
    @ValueSource(strings = {"bBb", "###", "123"})
    void create(String name) {
        assertThatCode(() -> new Player(name))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어 이름 길이가 3자가 아닌 경우 예외를 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"#", "12345"})
    void createWithInvalidName(String invalidName) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Player(invalidName));
    }
}
