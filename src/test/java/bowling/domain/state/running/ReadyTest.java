package bowling.domain.state.running;

import bowling.domain.pin.PinCount;
import bowling.domain.score.Score;
import bowling.domain.state.finish.Strike;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ReadyTest {

    @DisplayName("첫 투구에 10개가 넘어지면 Strike 반환")
    @Test
    public void returnStrike() {
        PinCount pinCount = PinCount.of(PinCount.MAX_COUNT);

        assertThat(Ready.getInstance().bowl(pinCount))
                .isInstanceOf(Strike.class);
    }

    @DisplayName("첫 투구에 10개 미만의 볼링 핀이 넘어지면 Hit 반환")
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 5, 9 })
    void returnHit(final int count) {
        PinCount pinCount = PinCount.of(count);

        assertThat(Ready.getInstance().bowl(pinCount))
                .isInstanceOf(FirstHit.class);
    }

    @DisplayName("종료 조건을 만족하지 않음")
    @Test
    public void isFinish() {
        assertThat(Ready.getInstance().isFinish())
                .isFalse();
    }

    @DisplayName("Miss 상태가 될 수 없음")
    @Test
    public void isMiss() {
        assertThat(Ready.getInstance().isMiss())
                .isFalse();
    }

    @DisplayName("볼링 핀이 남아있지 않은 상태를 만족하지 않음")
    @Test
    public void isCleanState() {
        assertThat(Ready.getInstance().isCleanState())
                .isFalse();
    }

    @DisplayName("첫 번째 투구 결과, 두 번째 투구 결과 모두 반환 불가능")
    @Test
    public void getFirstPinsAndSecondPins() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Ready.getInstance().getSecondPins());
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Ready.getInstance().getSecondPins());
    }

    @DisplayName("해당 프레임이 가지는 모든 상태값을 반환")
    @Test
    public void getState() {
        assertThat(Ready.getInstance().getState())
                .isEqualTo(Collections.singletonList(Ready.getInstance()));
    }

    @DisplayName("해당 상태의 점수를 반환")
    @Test
    public void getScore() {
        assertThat(Ready.getInstance().getScore())
                .isEqualTo(Score.UN_SCORE);
    }

    @DisplayName("점수를 계산할 수 있는 상태인지 확인")
    @ParameterizedTest
    @MethodSource
    public void calculateScoreForExtraBonusCount(final Score beforeScore, final Score expected) {
        assertThat(Ready.getInstance().calculateBonusScore(beforeScore))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> calculateScoreForExtraBonusCount() {
        return Stream.of(
                Arguments.of(Score.ofStrike(), Score.ofStrike()),
                Arguments.of(Score.ofSpare(), Score.ofSpare())
        );
    }
}
