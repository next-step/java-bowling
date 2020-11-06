package bowling.domain;

import bowling.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {
    @Test
    @DisplayName("플레이어 이름은 세글자여야 한다.")
    void playerName() {
        assertThat(new Player("abc")).isNotNull();
    }

    @Test
    @DisplayName("플레이어 이름은 세글자가 아닐 시 예외 발생")
    void exceptPlayerName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Player("ab");
        });
    }

    @Test
    @DisplayName("print() 시 플레이어 이름 출력")
    void print() {
        String name = "abc";
        assertThat(new Player(name).print()).isEqualTo(name);
    }
}
