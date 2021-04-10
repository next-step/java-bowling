package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingNormalFrameTest {

    @DisplayName("볼링 프레임은 0부터 시작한다.")
    @Test
    void case1() {
        BowlingNormalFrame bowlingNormalFrame = BowlingNormalFrame.first(Point.of(0));

        assertThat(bowlingNormalFrame.position()).isEqualTo((Position.of(0)));
    }

    @DisplayName("한 게임이 끝나면 새로운 프레임이 생성된다.")
    @Test
    void case2() {
        BowlingNormalFrame bowlingNormalFrame = BowlingNormalFrame.first(Point.of(0));
        BowlingFrame newBowlingNormalFrame = bowlingNormalFrame.nextFrame();

        assertThat(newBowlingNormalFrame.position()).isEqualTo(Position.of(1));
    }

}