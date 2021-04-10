package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerNameTest {

    @DisplayName("플레이어 이름은 3자리여야 한다.")
    @Test
    void case1() {
        Assertions.assertThatThrownBy(() -> {
            PlayerName.of("4444");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}