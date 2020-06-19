package bowling.step4.domain;

import bowling.step4.exception.PlayerMinimumCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PlayerCountTest {

    @DisplayName("플레이어의 수가 1 미만일 경우 PlayerMinimumCountException 발생")
    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    void 플레이어_수_검증(int playerCount) {
        assertThatExceptionOfType(PlayerMinimumCountException.class)
            .isThrownBy(() -> PlayerCount.of(playerCount));
    }

}