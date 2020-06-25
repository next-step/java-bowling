package bowling.domain.state.finish;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

public class MissTest {

    private final Pins firstPins = Pins.of(PinCount.of(PinCount.MIN_COUNT));
    private final Pins secondPins = Pins.of(PinCount.of(1));
    private final Miss miss = Miss.of(firstPins, secondPins);

    @DisplayName("Miss 상태를 가질 수 없으면 예외 반환")
    @Test
    public void createFailure() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Miss.of(Pins.of(2), Pins.of(8)));
    }

    @DisplayName("Miss 상태이므로 return true")
    @Test
    public void isMiss() {
        assertThat(miss.isMiss())
                .isTrue();
    }

    @DisplayName("해당 상태에서 공을 굴리면 예외를 반환")
    @Test
    public void bowl() {
        assertThatThrownBy(() -> miss.bowl(PinCount.of(PinCount.MAX_COUNT)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("종료 조건을 만족")
    @Test
    public void isFinish() {
        assertThat(miss.isFinish())
                .isTrue();
    }

    @DisplayName("볼링 핀이 남아있지 않은 상태를 만족하지 않음")
    @Test
    public void isCleanState() {
        assertThat(miss.isCleanState())
                .isFalse();
    }

    @DisplayName("첫 번째와 두 번째 투구 결과인 볼링 핀을 반환")
    @Test
    public void getFirstPinsAndSecondPins() {
        assertThat(miss.getFirstPins().getHitCount())
                .isEqualTo(firstPins.getHitCount());
        assertThat(miss.getSecondPins().getHitCount())
                .isEqualTo(secondPins.getHitCount());
    }

    @DisplayName("해당 프레임이 가지는 모든 상태값을 반환")
    @Test
    public void getState() {
        assertThat(miss.getState())
                .isEqualTo(Collections.singletonList(miss));
    }
}
