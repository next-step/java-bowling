package domain.state.closed;

import domain.Pins;
import domain.Score;
import domain.state.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static domain.Pins.GUTTER_PINS;
import static domain.Pins.STRIKE_PINS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StrikeTest {
    @ParameterizedTest
    @ValueSource(ints = {GUTTER_PINS, STRIKE_PINS})
    void 스트라이크_상태에서_다시_투구를_시도하면_예외가_발생한다(int fallenPins) {
        //given
        State state = new Strike();

        //when
        //then
        assertThatExceptionOfType(ClosedFrameException.class)
                .isThrownBy(() -> {
                    state.update(Pins.from(fallenPins));
                });
    }

    @Test
    void 스트라이크_상태는_계산이_끝난_상태가_아니다() {
        //given
        State state = new Strike();

        //when
        Score score = state.getScore();

        //then
        assertThat(score.isFullyCalculated()).isFalse();
    }
}
