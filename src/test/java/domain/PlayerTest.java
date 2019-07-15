package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {
    @Test
    void 플레이어_객체_생성() {
        final String playerName = "SIS";
        Player player = new Player(playerName);
        assertThat(player.getName()).isEqualTo(playerName);
    }

    @Test
    void 플레이어_이름_자릿수_초과_예외발생() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Player("ABCD");
        });
    }
}
