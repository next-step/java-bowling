package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {

    @Test
    @DisplayName("플레이어의 이름은 3글자여야 한다.")
    void assertPlayerName() {
        String name = "ye";
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Player(name);
        }).withMessage(Player.PLAYER_NAME_ERROR);
    }
}
