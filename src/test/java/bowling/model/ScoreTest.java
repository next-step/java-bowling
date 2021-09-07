package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("볼링 게임 점수 테스트")
public class ScoreTest {

    @DisplayName("첫 번째 볼링 점수가 0점 이상 10점 이하가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void outOfRangeFirstScoreExceptionTest(int first) {
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Score(first, 0))
                .withMessage("첫 번쨰 볼링 점수는 0점 이상 10점 이하여야 합니다.");
    }

    @DisplayName("두 번째 볼링 점수가 0점 미만이면 예외가 발생한다.")
    @Test
    void secondScoreUnderMinExceptionTest() {
        // given, when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Score(0, -1))
                .withMessage("두 번째 볼링 점수가 0점 미만일 수 없습니다.");
    }

    @DisplayName("점수의 총합이 10점을 초과면 예외가 발생한다.")
    @Test
    void sumOfScoreOverMaxExceptionTest() {
        // given, when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Score(5, 6))
                .withMessage("총 볼링 점수가 10점을 초과할 수 없습니다.");
    }

    @DisplayName("스트라이크 혹은 스페어 여부 메소드 기능이 정상 동작해야 한다.")
    @Test
    void isStrikeOrSpareTest() {
        // given, when, then
        assertTrue(new Score(10, 0).isStrikeOrSpare());
        assertTrue(new Score(5, 5).isStrikeOrSpare());
    }
}
