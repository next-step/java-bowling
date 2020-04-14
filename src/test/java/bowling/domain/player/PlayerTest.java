package bowling.domain.player;

import bowling.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @DisplayName("이름을 입력받을 수 있다.")
    @Test
    void inputPlayerName() {
        String name = "otk";
        Player expect = Player.of(name);

        Player actual = Player.of(name);

        assertThat(actual).isEqualTo(expect);
    }
}