package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PlayerTest {
    @Test
    @DisplayName("플레이어 이름은 3글자여야 한다")
    public void create() {
        assertThatThrownBy(() -> {
            new Player("1");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}