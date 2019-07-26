package domain.state.open;

import domain.Pins;
import domain.Score;
import domain.UndoneCalculationException;
import domain.state.State;
import domain.state.closed.Strike;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static domain.Pins.GUTTER_PINS;
import static domain.Pins.STRIKE_PINS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StandByTest {

    @Test
    void 첫_투구의_결과가_STRIKE면_STRIKE_상태를_생성한다() {
        //given
        StandBy standBy = new StandBy();

        //when
        State state = standBy.update(Pins.STRIKE);

        //then
        assertThat(state instanceof Strike).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {GUTTER_PINS, 1, STRIKE_PINS - 1})
    void 첫_투구의_결과가_STRIKE가_아니면_HIT_상태를_생성한다(int fallenPins) {
        //given
        StandBy standBy = new StandBy();

        //when
        State state = standBy.update(Pins.from(fallenPins));

        //then
        assertThat(state instanceof Hit).isTrue();
    }

    @Test
    void 히트_상태에서_점수를_구하면_0을_반환한다() {
        //given
        StandBy standBy = new StandBy();

        //when
        Score score = standBy.getScore();

        //then
        assertThat(score.getScore()).isEqualTo(0);
    }
}
