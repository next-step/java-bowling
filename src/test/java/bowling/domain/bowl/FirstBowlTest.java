package bowling.domain.bowl;

import bowling.domain.frame.score.FrameScore;
import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FirstBowlTest {

    @DisplayName("첫 투구시 넘어진 핀이 10개이면 스트라이크를 반환한다.")
    @Test
    void strike() {
        BowlCount bowlCount = new BowlCount(10);
        FirstBowl expect = FirstBowl.valueOf(Pins.of().knockOver(bowlCount), FrameScore.STRIKE);
        FirstBowl firstBowl = FirstBowl.of();

        FirstBowl actual = firstBowl.bowl(bowlCount);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("첫 투구시 넘어진 핀이 1~9개 이면 Running 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void running(int pinCount) {
        BowlCount bowlCount = new BowlCount(pinCount);
        FirstBowl expect = FirstBowl.valueOf(Pins.of().knockOver(bowlCount), FrameScore.RUNNING);
        FirstBowl firstBowl = FirstBowl.of();

        FirstBowl actual = firstBowl.bowl(bowlCount);

        assertThat(actual).isEqualTo(expect);
    }
}