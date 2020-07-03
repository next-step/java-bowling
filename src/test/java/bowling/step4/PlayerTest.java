package bowling.step4;

import bowling.step4.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @Test
    @DisplayName("플레이어 객체 생성")
    void create() {
        Player player = Player.of("PJS");
        assertThat(player).isEqualTo(Player.of("PJS"));
    }

    @Test
    @DisplayName("플레이어 객체 유효성 검사")
    void create_invalid() {
        assertThatThrownBy(() -> Player.of("FOUR"))
                    .isInstanceOf(IllegalArgumentException.class);
    }
}
