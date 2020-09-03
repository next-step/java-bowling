package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerTest {
    @DisplayName("볼링 게임 플레이어 이름 테스트")
    @Test
    void nameTest() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Player.of(null))
            .withMessage(Player.ERROR_MESSAGE);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> Player.of(""))
            .withMessage(Player.ERROR_MESSAGE);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> Player.of("name"))
            .withMessage(Player.ERROR_MESSAGE);
    }
}
