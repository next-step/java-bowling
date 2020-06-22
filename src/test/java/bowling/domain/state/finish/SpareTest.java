package bowling.domain.state.finish;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import bowling.domain.state.State;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class SpareTest {

    @DisplayName("Spare 상태를 가질 수 없으면 예외 반환")
    @Test
    public void createFailure() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Spare.of(Pins.of(2), Pins.of(3)));
    }

    @DisplayName("Miss 상태가 아님을 반환")
    @Test
    public void isMiss() {
        assertThat(Spare.of(Pins.of(2), Pins.of(8)).isMiss())
                .isFalse();
    }

    @DisplayName("Spare 점수에 대한 문자열을 반환")
    @ParameterizedTest
    @MethodSource
    public void getDesc(final State state, final String expected) {
        assertThat(state.getDesc())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> getDesc() {
        return Stream.of(
                Arguments.of(Spare.of(Pins.of(9), Pins.of(1)), "9|/"),
                Arguments.of(Spare.of(Pins.of(2), Pins.of(8)), "2|/"),
                Arguments.of(Spare.of(Pins.of(0), Pins.of(10)), "-|/")
        );
    }

    @DisplayName("해당 상태에서 공을 굴리면 예외를 반환")
    @Test
    public void bowl() {
        assertThatThrownBy(() -> Spare.of(Pins.of(0), Pins.of(10))
                .bowl(PinCount.of(PinCount.MAX_COUNT))
        ).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("종료 조건을 만족")
    @Test
    public void isFinish() {
        assertThat(Spare.of(Pins.of(0), Pins.of(10)).isFinish())
                .isTrue();
    }
}
