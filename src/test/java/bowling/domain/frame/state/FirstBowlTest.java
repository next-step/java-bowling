package bowling.domain.frame.state;

import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FirstBowlTest {
    @DisplayName("두번째 투구에 남은 핀들을 넘어트리면 SPARE를 반환한다.")
    @Test
    void spare() {
        Pins first = createPins(5);
        Pins second = createPins(5);
        FirstBowl firstBowl = new FirstBowl(first);

        State actual = firstBowl.roll(second);

        assertThat(actual).isInstanceOf(Spare.class);
    }

    @DisplayName("두번째 투구에 남은 핀들을 못 넘어트리면 MISS를 반환한다.")
    @Test
    void miss() {
        Pins first = createPins(5);
        Pins second = createPins(4);
        FirstBowl firstBowl = new FirstBowl(first);

        State actual = firstBowl.roll(second);

        assertThat(actual).isInstanceOf(Miss.class);
    }

    @DisplayName("두번째 투구에도 핀들을 못 넘어트리면 GUTTER를 반환한다.")
    @Test
    void gutter() {
        Pins first = createPins(0);
        Pins second = createPins(0);
        FirstBowl firstBowl = new FirstBowl(first);

        State actual = firstBowl.roll(second);

        assertThat(actual).isInstanceOf(SecondGutter.class);
    }

    @DisplayName("FirstBowl 의 점수를 반환한다.")
    @Test
    void getScore() {
        Pins first = createPins(5);
        State firstBowl = new FirstBowl(first);
        Score expect = new Score(5);

        Score actual = ((Calculable) firstBowl).getScore();

        assertThat(actual).isEqualTo(expect);
    }

    Pins createPins(int count) {
        BowlCount bowlCount = new BowlCount(count);
        final Pins pins = Pins.of();

        return pins.knockOver(bowlCount);
    }
}