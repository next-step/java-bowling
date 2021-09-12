package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("볼링 점수 테스트")
public class ScoreTest {

    @DisplayName("볼링 점수가 0점 미만 300점 초과면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 301})
    void outOfRangeScoreExceptionTest(int score) {
        // given, when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Score.from(score))
                .withMessage("볼링 점수는 0점 이상 300점 이하이어야 합니다.");
    }

    @DisplayName("볼링 점수가 0점 미만 300점 이하면 정상 생성되어야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 300})
    void createScoreTest(int score) {
        // given, when, then
        assertEquals(Score.from(score).value(), score);
    }
}
