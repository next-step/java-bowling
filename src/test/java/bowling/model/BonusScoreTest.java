package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("보너스 점수 테스트")
public class BonusScoreTest {

    @DisplayName("볼링 점수가 0점 이상 10점 이하가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void outOfRangeScoreExceptionTest(int score) {
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BonusScore(score))
                .withMessage("보너스 볼링 점수는 0점 이상 10점 이하여야 합니다.");
    }
}
