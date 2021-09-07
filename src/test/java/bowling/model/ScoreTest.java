package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("볼링 게임 점수 테스트")
public class ScoreTest {

    @DisplayName("첫 번째 볼링 점수가 0점 이상 10점 이하가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void outOfRangeFirstScoreExceptionTest(int first) {
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Score(first))
                .withMessage("첫 번쨰 볼링 점수는 0점 이상 10점 이하여야 합니다.");
    }

    @DisplayName("두 번째 볼링 점수가 0점 미만이면 예외가 발생한다.")
    @Test
    void minSecondScoreExceptionTest() {
        // given
        Score score = new Score(0);

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> score.setSecond(-1))
                .withMessage("두 번째 볼링 점수가 0점 미만일 수 없습니다.");
    }
}
