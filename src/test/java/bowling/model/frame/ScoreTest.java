package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("볼링 점수 테스트")
public class ScoreTest {

    @DisplayName("볼링 점수가 0점 이상 10점 이하가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void outOfRangeScoreExceptionTest(int score) {
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Score.of(score))
                .withMessage("볼링 점수는 0점 이상 10점 이하여야 합니다.");
    }

    @DisplayName("볼링 점수를 생성할 때, 기존에 같은 점수가 존재하면 객체를 재사용한다.")
    @Test
    void reuseScoreTest() {
        // given, when
        Score firstScore = Score.of(5);
        Score secondScore = Score.of(5);

        // then
        assertSame(firstScore, secondScore);
    }
}
