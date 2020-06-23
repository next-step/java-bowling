package bowling.domain.frame;

import bowling.domain.dto.FrameResult;
import bowling.domain.pin.PinCount;
import bowling.domain.state.StateExpression;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class NormalFrameTest {

    @DisplayName("생성 성공")
    @Test
    public void ofFirst() {
        assertThatCode(NormalFrame::ofFirst)
                .doesNotThrowAnyException();
    }

    @DisplayName("자신의 프레임 번호 반환")
    @Test
    public void getNo() {
        assertThat(NormalFrame.newInstance(FrameNumber.of(FrameNumber.MIN_NUMBER)).getNo())
                .isEqualTo(FrameNumber.MIN_NUMBER);
    }

    @DisplayName("종료 상태가 되면 다음 프레임을 반환")
    @Test
    public void finishedAfterBowl() {
        Frame nextFrame = NormalFrame.ofFirst()
                .bowl(PinCount.of(PinCount.MAX_COUNT));

        assertThat(nextFrame.getNo())
                .isEqualTo(FrameNumber.MIN_NUMBER + 1);
    }

    @DisplayName("진행 상태면 자기 자신 프레임을 반환")
    @Test
    public void runningAfterBowl() {
        Frame nextFrame = NormalFrame.ofFirst()
                .bowl(PinCount.of(PinCount.MIN_COUNT));

        assertThat(nextFrame.getNo())
                .isEqualTo(FrameNumber.MIN_NUMBER);
    }

    @DisplayName("게임 종료 불가능 상태")
    @Test
    public void isGameOver() {
        assertThat(NormalFrame.ofFirst().isGameOver())
                .isFalse();
    }

    @DisplayName("쓰러진 공에 따라 상태값이 변경 및 자신의 상태값을 반환 : gutter, nextFrame(ready)")
    @ParameterizedTest
    @MethodSource
    public void getFrameResult(final Frame frame, final FrameResult expected) {
        assertThat(frame.getFrameResult())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> getFrameResult() {
        return Stream.of(
                Arguments.of(NormalFrame.ofFirst().bowl(PinCount.of(PinCount.MIN_COUNT)),
                        FrameResult.of(StateExpression.GUTTER + StateExpression.BLANK)),
                Arguments.of(NormalFrame.ofFirst().bowl(PinCount.of(PinCount.MAX_COUNT)),
                        FrameResult.of(StateExpression.READY))
        );
    }
}
