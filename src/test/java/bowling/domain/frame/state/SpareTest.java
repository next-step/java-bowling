package bowling.domain.frame.state;

import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {
    @DisplayName("SPARE의 상태를 갖고올 수 있다.")
    @Test
    void spare() {
        String expect = "/";

        State actual = new Spare(Pins.GUTTER_PINS);

        assertThat(actual.toResult()).isEqualTo(expect);
    }

    @DisplayName("SPARE의 점수를 갖고올 수 있다.")
    @Test
    void getScore() {
        Pins second = Pins.of().knockOver(new BowlCount(4));
        State actual = new Spare(second);

        Score score = ((Calculable) actual).getScore();
        assertThat(score).isEqualTo(new Score(4, 1));
    }
}