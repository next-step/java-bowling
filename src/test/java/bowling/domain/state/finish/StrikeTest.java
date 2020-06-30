package bowling.domain.state.finish;

import bowling.domain.pin.PinCount;
import bowling.domain.score.Score;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class StrikeTest {

    @DisplayName("Miss 상태가 아님을 반환")
    @Test
    public void isMiss() {
        assertThat(Strike.getInstance().isMiss())
                .isFalse();
    }

    @DisplayName("해당 상태에서 공을 굴리면 예외를 반환")
    @Test
    public void bowl() {
        assertThatThrownBy(() -> Strike.getInstance().bowl(PinCount.of(PinCount.MAX_COUNT)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("종료 조건을 만족")
    @Test
    public void isFinish() {
        assertThat(Strike.getInstance().isFinish())
                .isTrue();
    }

    @DisplayName("볼링 핀이 남아있지 않은 상태 만족")
    @Test
    public void isCleanState() {
        assertThat(Strike.getInstance().isCleanState())
                .isTrue();
    }

    @DisplayName("첫 번째 투구 결과 반환, 두 번째 투구 결과 반환 불가능")
    @Test
    public void getFirstPinsAndSecondPins() {
        assertThat(Strike.getInstance().getFirstPins().getHitCount())
                .isEqualTo(10);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Strike.getInstance().getSecondPins());
    }

    @DisplayName("해당 프레임이 가지는 모든 상태값을 반환")
    @Test
    public void getState() {
        assertThat(Strike.getInstance().getState())
                .isEqualTo(Collections.singletonList(Strike.getInstance()));
    }

    @DisplayName("해당 상태의 점수를 반환")
    @Test
    public void getScore() {
        assertThat(Strike.getInstance().getScore())
                .isEqualTo(Score.ofStrike());
    }

    @DisplayName("점수를 계산할 수 있는 상태인지 확인")
    @ParameterizedTest
    @MethodSource
    public void calculateScoreForExtraBonusCount(final Score beforeScore, final Score expected) {
        assertThat(Strike.getInstance()
                .calculateBonusScore(beforeScore)
        ).isEqualTo(expected);
    }

    private static Stream<Arguments> calculateScoreForExtraBonusCount() {
        return Stream.of(
                Arguments.of(Score.ofStrike(), Score.valueOf(20, 1)),
                Arguments.of(Score.ofSpare(), Score.valueOf(20, 0))
        );
    }
}
