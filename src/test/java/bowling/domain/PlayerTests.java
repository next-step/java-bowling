package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTests {
    private static final String playerName = "JBJ";

    @DisplayName("이름을 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        Player player = new Player(playerName);

        assertThat(player).isNotNull();
    }

    @DisplayName("첫번재 투구를 진행하고 상태를 확인할 수 있다.")
    @Test
    void bowlFirstTest() {
        Player player = new Player(playerName);
        int numberOfHitPin = 3;

        assertThat(player.bowlFirst(numberOfHitPin)).isEqualTo(FrameStatus.THREE);
    }
}
