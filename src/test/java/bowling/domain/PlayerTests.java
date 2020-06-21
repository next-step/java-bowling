package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTests {
    private Player player;

    @BeforeEach
    public void setup() {
        player = new Player("JBJ");
    }

    @DisplayName("이름을 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        assertThat(player).isNotNull();
    }

    @DisplayName("첫번재 투구를 진행하고 상태를 확인할 수 있다.")
    @Test
    void bowlFirstTest() {
        int numberOfHitPin = 3;

        assertThat(player.bowlFirst(numberOfHitPin)).isEqualTo(FrameStatus.THREE);
    }

    @DisplayName("프레임을 마무리하면 다음 프레임을 진행할 수 있다.")
    @Test
    void nextFrameAfterStrikeTest() {
        int firstNumberOfHitPin = 10;
        int secondNumberOfHitPin = 5;

        player.bowlFirst(firstNumberOfHitPin);
        assertThat(player.bowl(secondNumberOfHitPin)).isEqualTo(FrameStatus.FIVE);
    }
}
