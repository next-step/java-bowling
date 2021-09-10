package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("볼링 점수 테스트")
public class ScoreTest {

    @DisplayName("볼링 점수가 0점 미만 300점 초과면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 301})
    void outOfRangeScoreExceptionTest(int score) {
        // given, when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Score.of(score))
                .withMessage("볼링 점수는 0점 이상 300점 이하이어야 합니다.");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Score.strike(score))
                .withMessage("볼링 점수는 0점 이상 300점 이하이어야 합니다.");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Score.spare(score))
                .withMessage("볼링 점수는 0점 이상 300점 이하이어야 합니다.");
    }

    @DisplayName("기다리고 있는 프레임 개수가 0개 미만 2개 초과면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 3})
    void outOfRangeWaitingFrameCountExceptionTest(int waitingFrameCount) {
        // given, when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Score(5, waitingFrameCount))
                .withMessage("기다리고 있는 프레임 개수는 0개 이상 2개 이하이어야 합니다.");

    }
}
