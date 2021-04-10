package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingFinalFrameTest {

    @DisplayName("한번더 투구한다.")
    @Test
    void case2() {
        Score score = Score.first(Point.of(10));
        BowlingFinalFrame bowlingFinalFrame = BowlingFinalFrame.of(score);

        BowlingFrame bowlingFrame = bowlingFinalFrame.play();

        assertThat(bowlingFrame.position()).isEqualTo(Position.of(11));
    }

    @DisplayName("볼링을 끝마친다.")
    @Test
    void case3() {
        Score score = Score.first(Point.of(4)).next(Point.of(2));
        BowlingFinalFrame bowlingFinalFrame = BowlingFinalFrame.of(score);

        BowlingFrame bowlingFrame = bowlingFinalFrame.play();

        assertThat(bowlingFrame.position()).isEqualTo(Position.of(10));
    }
}