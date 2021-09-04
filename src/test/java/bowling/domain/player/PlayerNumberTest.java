package bowling.domain.player;

import bowling.exception.IllegalPayerNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerNumberTest {

    @DisplayName("플레이어 숫자는 0보다 큰 숫자여야 한다.")
    @Test
    void player_number_validate() {
        assertThatThrownBy(() -> PlayerNumber.of(-1))
                .isInstanceOf(IllegalPayerNumberException.class);
    }
}