package bowling.domain.frame.state;

import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {
    @DisplayName("MISS 의 상태를 갖고올 수 있다.")
    @Test
    void spare() {
        String expect = "4";
        Pins first = Pins.of().knockOver(new BowlCount(4));
        Pins second = Pins.of().knockOver(new BowlCount(4));

        State firstBowl = new FirstBowl(first);

        State actual = firstBowl.roll(second);

        assertThat(actual.toResult()).isEqualTo(expect);
    }

    @DisplayName("MISS 의 점수를 갖고올 수 있다.")
    @Test
    void getScore() {
        Pins first = Pins.of().knockOver(new BowlCount(4));
        Pins second = Pins.of().knockOver(new BowlCount(4));

        State firstBowl = new FirstBowl(first);

        State actual = firstBowl.roll(second);
        Score score = ((Calculable) actual).getScore();

        assertThat(score).isEqualTo(new Score(4));
    }
}