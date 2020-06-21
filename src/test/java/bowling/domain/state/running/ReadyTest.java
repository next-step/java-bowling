package bowling.domain.state.running;

import bowling.domain.pin.PinCount;
import bowling.domain.state.StateExpression;
import bowling.domain.state.finish.Strike;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {

    @DisplayName("첫 투구에 10개가 넘어지면 Strike 반환")
    @Test
    public void returnStrike() {
        PinCount pinCount = PinCount.of(PinCount.MAX_COUNT);

        assertThat(Ready.of().bowl(pinCount))
                .isInstanceOf(Strike.class);
    }

    @DisplayName("첫 투구에 10개 미만의 볼링 핀이 넘어지면 Hit 반환")
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 5, 9 })
    void returnHit(final int count) {
        PinCount pinCount = PinCount.of(count);

        assertThat(Ready.of().bowl(pinCount))
                .isInstanceOf(Hit.class);
    }

    @DisplayName("Ready에 대한 문자열을 반환")
    @Test
    public void getDesc() {
        assertThat(Ready.of().getDesc())
                .isEqualTo(StateExpression.READY);
    }
}
