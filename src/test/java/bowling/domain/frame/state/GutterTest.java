package bowling.domain.frame.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GutterTest {
    @DisplayName("첫번째, 두번째 투구 다 거터일경우 - 를 출력한다.")
    @Test
    void gutter() {
        String expect = "-";

        State first = new FirstGutter(Pins.GUTTER_PINS);
        State second = first.roll(Pins.GUTTER_PINS);

        assertThat(first.toResult()).isEqualTo(expect);
        assertThat(second.toResult()).isEqualTo(expect);
    }

    @DisplayName("SecondGutter의 Score를 가져올 수 있다.")
    @Test
    void getScore() {
        State first = new FirstGutter(Pins.GUTTER_PINS);
        State second = first.roll(Pins.GUTTER_PINS);

        final Score score = ((Calculable) second).getScore();

        assertThat(score).isEqualTo(new Score(0, 0));
    }
}