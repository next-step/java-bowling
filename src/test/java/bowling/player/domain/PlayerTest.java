package bowling.player.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PlayerTest {

    private Player player;

    @Test
    @DisplayName("플레이어 입력")
    void createPlayer() {
        player = new Player("PJS");
        assertThat(player.getName()).isEqualTo("PJS");
    }

    @Test
    @DisplayName("플레이어 글자가 3글자가 넘어가면 Exception 발생")
    void validatePlayerNameLength() {
        assertThatExceptionOfType(NotMatchingPlayerNameException.class)
                .isThrownBy(() -> {
                    player = new Player("ABCD");
                });
    }

}
