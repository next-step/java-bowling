package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerNameTest {
    @Test
    void create() {
        PlayerName playerName = new PlayerName("ABC");
        assertThat(playerName).isEqualTo(new PlayerName("ABC"));
    }

    @DisplayName("3자리를 초과하여 이름 입력시 에러 발생")
    @Test
    void error() {
        assertThatThrownBy(() -> new PlayerName("ABCD")).isInstanceOf(IllegalArgumentException.class);
    }
}