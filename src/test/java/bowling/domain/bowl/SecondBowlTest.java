package bowling.domain.bowl;

import bowling.domain.frame.score.FrameScore;
import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SecondBowlTest {

    @DisplayName("첫번째 + 두번째 투구를 합쳐 SPARE를 확인할 수 있다.")
    @Test
    void spare() {
        FirstBowl firstBowl = createFirstBowl(8);
        SecondBowl secondBowl = SecondBowl.valueOf(firstBowl.getPins());

        SecondBowl actual = secondBowl.bowl(new BowlCount(2));
        SecondBowl expect = SecondBowl.valueOf(Pins.of().knockOver(new BowlCount(10)), FrameScore.SPARE);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("첫번째 + 두번째 투구를 합쳐 MISS를 확인할 수 있다.")
    @Test
    void miss() {
        FirstBowl firstBowl = createFirstBowl(8);
        SecondBowl secondBowl = SecondBowl.valueOf(firstBowl.getPins());

        SecondBowl actual = secondBowl.bowl(new BowlCount(1));
        SecondBowl expect = SecondBowl.valueOf(Pins.of().knockOver(new BowlCount(9)), FrameScore.MISS);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("첫번째 + 두번째 투구를 합쳐 GUTTER를 확인할 수 있다.")
    @Test
    void gutter() {
        FirstBowl firstBowl = createFirstBowl(0);
        SecondBowl secondBowl = SecondBowl.valueOf(firstBowl.getPins());

        SecondBowl actual = secondBowl.bowl(new BowlCount(0));
        SecondBowl expect = SecondBowl.valueOf(Pins.of().knockOver(new BowlCount(0)), FrameScore.GUTTER);

        assertThat(actual).isEqualTo(expect);
    }

    FirstBowl createFirstBowl(int count) {
        BowlCount firstBowlCount = new BowlCount(count);
        return FirstBowl.valueOf(Pins.of().knockOver(firstBowlCount), FrameScore.RUNNING);
    }
}