package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("볼링 프레임 점수 테스트")
public class FrameScoreTest {

    @DisplayName("프레임 점수 첫 번째 객체의 두 번째 점수값은 비어있어야 한다.")
    @Test
    void emptySecondScoreOfFrameScoreFirstTest() {
        // given, when
        FrameScore frameScoreFirst = FrameScore.first(5);

        // then
        assertSame(frameScoreFirst.getSecond(), null);
    }

    @DisplayName("프레임 점수 두 번째 객체의 첫 번째 점수값이 비어있으면 안된다.")
    @Test
    void notEmptyFirstScoreOfFrameScoreSecondTest() {
        // given, when
        FrameScore frameScoreFirst = FrameScore.first(5);
        FrameScore frameScoreSecond = frameScoreFirst.second(5);

        // then
        assertNotSame(frameScoreSecond.getFirst(), null);
    }

    @DisplayName("이미 스트라이크인데 두 번째 점수를 생성하려고 하면 예외가 발생한다.")
    @Test
    void createSecondScoreOfStrikeFrameExceptionTest() {
        // given
        FrameScore strikeFrameScore = FrameScore.first(10);

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> strikeFrameScore.second(0))
                .withMessage("이미 스트라이크이기 때문에 두 번째 점수를 생성할 수 없습니다.");
    }

    @DisplayName("프레임 점수의 총합이 10점을 초과면 예외가 발생한다.")
    @Test
    void totalScoreOverMaxExceptionTest() {
        // given
        FrameScore frameScore = FrameScore.first(5);

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> frameScore.second(6))
                .withMessage("총 볼링 점수가 10점을 초과할 수 없습니다.");
    }
}
