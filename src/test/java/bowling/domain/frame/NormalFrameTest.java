package bowling.domain.frame;

import bowling.domain.state.StateExpression;
import bowling.fixture.NormalFrameFixture;
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
        assertThat(NormalFrame.newInstance(FrameNumber.of(FrameNumber.MIN_NUMBER)).getFrameNo())
                .isEqualTo(FrameNumber.MIN_NUMBER);
    }

    @DisplayName("initNextFrame: 종료 상태(Strike, Spare, Miss) - 다음 프레임을 생성하여 반환")
    @ParameterizedTest
    @MethodSource("finishedStateCase")
    public void initNextFrameByFinishedState(final NormalFrame normalFrame) {
        assertThat(normalFrame.initNextFrame().getFrameNo())
                .isEqualTo(normalFrame.getFrameNo() + 1);
    }

    private static Stream<Arguments> finishedStateCase() {
        return Stream.of(
                Arguments.of(NormalFrameFixture.getStrikeFrame()),
                Arguments.of(NormalFrameFixture.getSpareFrame()),
                Arguments.of(NormalFrameFixture.getMissFrame())
        );
    }

    @DisplayName("initNextFrame: 진행 상태 - 현재 프레임을 반환")
    @ParameterizedTest
    @MethodSource("runningStateCase")
    public void initNextFrameByRunningState(final NormalFrame normalFrame) {
        assertThat(normalFrame.initNextFrame().getFrameNo())
                .isEqualTo(normalFrame.getFrameNo());
    }

    private static Stream<Arguments> runningStateCase() {
        return Stream.of(
                Arguments.of(NormalFrameFixture.getOneHitFrame()),
                Arguments.of(NormalFrameFixture.getGutterFrame())
        );
    }

    @DisplayName("addFrame: 종료 상태 - 다음 프레임을 추가")
    @ParameterizedTest
    @MethodSource("finishedStateCase")
    public void addFrameWhenFinishedState(final NormalFrame normalFrame) {
        Frames frames = Frames.newInstance();
        normalFrame.addFrame(frames);

        assertThat(frames.getFrameNumber())
                .isEqualTo(normalFrame.getFrameNo() + 1);
    }

    @DisplayName("addFrame: 진행 상태 - 프레임 추가하지 않음")
    @ParameterizedTest
    @MethodSource("runningStateCase")
    public void addFrameWhenRunningState(final NormalFrame normalFrame) {
        Frames frames = Frames.newInstance();
        normalFrame.addFrame(frames);

        assertThat(frames.getFrameNumber())
                .isEqualTo(normalFrame.getFrameNo());
    }

    @DisplayName("게임 종료 불가능 상태")
    @Test
    public void isGameOver() {
        assertThat(NormalFrame.ofFirst().isGameOver())
                .isFalse();
    }

    @DisplayName("쓰러진 공에 따라 상태값이 변경 및 자신의 상태값을 반환")
    @ParameterizedTest
    @MethodSource
    public void getFrameResult(final Frame frame, final String expected) {
        assertThat(frame.getFrameResult().getSymbol())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> getFrameResult() {
        Frame gutterStateFrame = NormalFrameFixture.getGutterFrame();
        Frame strikeFrame = NormalFrameFixture.getStrikeFrame();

        return Stream.of(
                Arguments.of(gutterStateFrame, StateExpression.GUTTER),
                Arguments.of(strikeFrame, StateExpression.STRIKE)
        );
    }
}
