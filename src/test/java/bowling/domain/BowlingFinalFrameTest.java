package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingFinalFrameTest {

    @DisplayName("스트라이크면 한번더 투구한다.")
    @Test
    void case1() {
        Score score = Score.first(Point.of(10));
        BowlingFinalFrame bowlingFinalFrame = BowlingFinalFrame.of(score);

        assertThat(bowlingFinalFrame.isOneMoreTime()).isTrue();
    }

    @DisplayName("스페어면 한번더 투구한다.")
    @Test
    void case2() {
        Score score = Score.first(Point.of(5)).next(Point.of(5));
        BowlingFinalFrame bowlingFinalFrame = BowlingFinalFrame.of(score);

        assertThat(bowlingFinalFrame.isOneMoreTime()).isTrue();
    }

    @DisplayName("미스면 한번더 투구한다.")
    @Test
    void case3() {
        Score score = Score.first(Point.of(5)).next(Point.of(4));
        BowlingFinalFrame bowlingFinalFrame = BowlingFinalFrame.of(score);

        assertThat(bowlingFinalFrame.isOneMoreTime()).isFalse();
    }
}