package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어 이름은 3자리여야 한다.")
    @Test
    void case1() {
        Assertions.assertThatThrownBy(() -> {
            Player.of("4444", 0);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}