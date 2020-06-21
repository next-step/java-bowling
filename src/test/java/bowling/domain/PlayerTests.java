package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

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

    @DisplayName("현재 진행중인 프레임이 어디인지 알 수 있다.")
    @Test
    void checkWhereTest() {
        int firstNumberOfHitPin = 10;
        int secondNumberOfHitPin = 5;
        int thirdNumberOfHitPin = 4;

        player.bowlFirst(firstNumberOfHitPin);
        assertThat(player.checkWhere()).isEqualTo(1);
        player.bowl(secondNumberOfHitPin);
        assertThat(player.checkWhere()).isEqualTo(2);
        player.bowl(thirdNumberOfHitPin);
        assertThat(player.checkWhere()).isEqualTo(2);
    }

    @DisplayName("첫번재 투구를 진행하고 상태를 확인할 수 있다.")
    @Test
    void bowlFirstTest() {
        int numberOfHitPin = 3;

        assertThat(player.bowlFirst(numberOfHitPin))
                .isEqualTo(new FrameStatuses(Collections.singletonList(FrameStatus.THREE)));
        assertThat(player.checkWhere()).isEqualTo(1);
    }

    @DisplayName("프레임을 마무리하면 다음 프레임을 진행할 수 있다.")
    @Test
    void nextFrameAfterStrikeTest() {
        int firstNumberOfHitPin = 10;
        int secondNumberOfHitPin = 5;

        player.bowlFirst(firstNumberOfHitPin);
        assertThat(player.bowl(secondNumberOfHitPin))
                .isEqualTo(new FrameStatuses(Collections.singletonList(FrameStatus.FIVE)));
        assertThat(player.checkWhere()).isEqualTo(2);
    }

    @DisplayName("해당 프레임이 마무리되지 않았다면 현재 프레임을 진행할 수 있다.")
    @Test
    void doCurrentFrameWhenNotFinished() {
        int firstNumberOfHitPin = 10;
        int secondNumberOfHitPin = 5;
        int thirdNumberOfHitPin = 5;

        player.bowlFirst(firstNumberOfHitPin);
        player.bowl(secondNumberOfHitPin);
        assertThat(player.bowl(thirdNumberOfHitPin))
                .isEqualTo(new FrameStatuses(Arrays.asList(FrameStatus.FIVE, FrameStatus.SPARE)));
        assertThat(player.checkWhere()).isEqualTo(2);
    }
}
