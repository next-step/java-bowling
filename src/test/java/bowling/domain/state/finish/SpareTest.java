package bowling.domain.state.finish;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class SpareTest {

    private final Pins firstPins = Pins.of(PinCount.of(2));
    private final Pins secondPins = Pins.of(PinCount.of(8));
    private final Spare spare = Spare.of(firstPins, secondPins);

    @DisplayName("Spare 상태를 가질 수 없으면 예외 반환")
    @Test
    public void createFailure() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Spare.of(Pins.of(2), Pins.of(3)));
    }

    @DisplayName("해당 상태에서 공을 굴리면 예외를 반환")
    @Test
    public void bowl() {
        assertThatThrownBy(() -> spare.bowl(PinCount.of(PinCount.MAX_COUNT)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("종료 조건을 만족")
    @Test
    public void isFinish() {
        assertThat(spare.isFinish())
                .isTrue();
    }

    @DisplayName("Miss 상태가 아님을 반환")
    @Test
    public void isMiss() {
        assertThat(spare.isMiss())
                .isFalse();
    }

    @DisplayName("볼링 핀이 남아있지 않은 상태 만족")
    @Test
    public void isCleanState() {
        assertThat(spare.isCleanState())
                .isTrue();
    }

    @DisplayName("첫 번째와 두 번째 투구 결과인 볼링 핀을 반환")
    @Test
    public void getFirstPinsAndSecondPins() {
        assertThat(spare.getFirstPins().getHitCount())
                .isEqualTo(firstPins.getHitCount());
        assertThat(spare.getSecondPins().getHitCount())
                .isEqualTo(secondPins.getHitCount());
    }

    @DisplayName("해당 프레임이 가지는 모든 상태값을 반환")
    @Test
    public void getState() {
        assertThat(spare.getState())
                .isEqualTo(Collections.singletonList(spare));
    }

    @DisplayName("해당 상태의 점수를 반환")
    @Test
    public void getScore() {
        assertThat(spare.getScore())
                .isEqualTo(Score.ofSpare());
    }

    @DisplayName("점수를 계산할 수 있는 상태인지 확인")
    @ParameterizedTest
    @MethodSource
    public void calculateScoreForExtraBonusCount(final Score beforeScore, final Score expected) {
        final Pins firstPins = Pins.of(PinCount.of(2));
        final Pins secondPins = Pins.of(PinCount.of(8));
        final Spare spare = Spare.of(firstPins, secondPins);

        assertThat(spare.calculateBonusScore(beforeScore))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> calculateScoreForExtraBonusCount() {
        return Stream.of(
                Arguments.of(Score.ofStrike(), Score.valueOf(20, 0)),
                Arguments.of(Score.ofSpare(), Score.valueOf(12, 0))
        );
    }
}
