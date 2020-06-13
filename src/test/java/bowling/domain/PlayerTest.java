package bowling.domain;

import bowling.step2.domain.Player;
import bowling.step2.exception.PlayerNameMaximumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PlayerTest {

    @DisplayName("플레이어이의 이름의 글자수가 3글자를 초과할 경우 PlayerNameMaximumException 발생")
    @ParameterizedTest
    @ValueSource(strings = {"1234", "12345"})
    void 이름_검증_테스트 (String value) {
        assertThatExceptionOfType(PlayerNameMaximumException.class)
            .isThrownBy(() -> Player.valueOf(value));
    }
}
