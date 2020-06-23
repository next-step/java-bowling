package bowling.domain.state;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.running.Hit;
import bowling.domain.state.running.Ready;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StateFactoryTest {

    @DisplayName("준비 상태인 Ready를 반환")
    @Test
    public void ready() {
        assertThat(StateFactory.ready())
                .isInstanceOf(Ready.class);
    }

    @DisplayName("준비 상태인 Ready에서 넘어트린 볼링핀의 개수에 해당하는 상태를 반환")
    @ParameterizedTest
    @MethodSource
    public void hit(final PinCount pinCount, final State expected) {
        assertThat(StateFactory.hit(pinCount))
                .isInstanceOf(expected.getClass());
    }

    private static Stream<Arguments> hit() {
        final PinCount minCount = PinCount.of(PinCount.MIN_COUNT);

        return Stream.of(
                Arguments.of(PinCount.of(PinCount.MAX_COUNT), Strike.getInstance()),
                Arguments.of(minCount, Hit.of(Pins.of(minCount)))
        );
    }
}
