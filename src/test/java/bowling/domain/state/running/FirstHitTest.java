package bowling.domain.state.running;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FirstHitTest {

    @DisplayName("Spare 상태를 가질 수 없으면 예외 반환")
    @Test
    public void createFailure() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> FirstHit.of(Pins.of(PinCount.MAX_COUNT)));
    }

    @DisplayName("두번째 투구에서 나머지 볼링 핀이 모두 넘어지면 Spare 반환")
    @ParameterizedTest
    @MethodSource
    public void returnSpare(final FirstHit hit, final PinCount pinCount) {
        assertThat(hit.bowl(pinCount))
                .isInstanceOf(Spare.class);
    }

    private static Stream<Arguments> returnSpare() {
        return Stream.of(
                Arguments.of(FirstHit.of(Pins.of(9)), PinCount.of(PinCount.MAX_COUNT - 9)),
                Arguments.of(FirstHit.of(Pins.of(2)), PinCount.of(PinCount.MAX_COUNT - 2)),
                Arguments.of(FirstHit.of(Pins.of(PinCount.MIN_COUNT)), PinCount.of(PinCount.MAX_COUNT))
        );
    }

    @DisplayName("두번째 투구에서 볼링 핀이 1개라도 남아있으면 Miss 반환")
    @ParameterizedTest
    @MethodSource
    public void returnMiss(final FirstHit hit, final PinCount pinCount) {
        assertThat(hit.bowl(pinCount))
                .isInstanceOf(Miss.class);
    }

    private static Stream<Arguments> returnMiss() {
        return Stream.of(
                Arguments.of(FirstHit.of(Pins.of(9)), PinCount.of(PinCount.MIN_COUNT)),
                Arguments.of(FirstHit.of(Pins.of(2)), PinCount.of(PinCount.MIN_COUNT)),
                Arguments.of(FirstHit.of(Pins.of(PinCount.MIN_COUNT)), PinCount.of(PinCount.MIN_COUNT))
        );
    }

    @DisplayName("종료 조건을 만족하지 않음")
    @Test
    public void isFinish() {
        assertThat(FirstHit.of(Pins.of(PinCount.MIN_COUNT)).isFinish())
                .isFalse();
    }

    @DisplayName("Miss 상태가 될 수 없음")
    @Test
    public void isMiss() {
        assertThat(FirstHit.of(Pins.of(PinCount.MIN_COUNT)).isMiss())
                .isFalse();
    }

    @DisplayName("볼링 핀이 남아있지 않은 상태를 만족하지 않음")
    @Test
    public void isCleanState() {
        assertThat(FirstHit.of(Pins.of(PinCount.MIN_COUNT)).isCleanState())
                .isFalse();
    }

    @DisplayName("첫 번째 투구 결과 반환, 두 번째 투구 결과 반환 불가능")
    @Test
    public void getFirstPinsAndSecondPins() {
        FirstHit firstHit = FirstHit.of(Pins.of(9));

        assertThat(firstHit.getFirstPins().getHitCount())
                .isEqualTo(9);
        assertThatIllegalArgumentException()
                .isThrownBy(firstHit::getSecondPins);
    }

    @DisplayName("해당 프레임이 가지는 모든 상태값을 반환")
    @Test
    public void getState() {
        FirstHit firstHit = FirstHit.of(Pins.of(9));

        assertThat(firstHit.getState())
                .isEqualTo(Collections.singletonList(firstHit));
    }
}
