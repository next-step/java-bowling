package bowling.step4.domain;

import bowling.step4.domain.frame.*;
import bowling.step4.domain.scores.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FramesTest {

    private static final Scores normalScores = NormalScores.init();
    private static final Scores finalScores = FinalScores.init();
    private static final Frame lastFrame = FinalFrame.of(Frames.LAST_FRAME, finalScores);
    private static final Frame frame9 = NormalFrame.of(9, normalScores, lastFrame);
    private static final Frame frame8 = NormalFrame.of(8, normalScores, frame9);
    private static final Frame frame7 = NormalFrame.of(7, normalScores, frame8);
    private static final Frame frame6 = NormalFrame.of(6, normalScores, frame7);
    private static final Frame frame5 = NormalFrame.of(5, normalScores, frame6);
    private static final Frame frame4 = NormalFrame.of(4, normalScores, frame5);
    private static final Frame frame3 = NormalFrame.of(3, normalScores, frame4);
    private static final Frame frame2 = NormalFrame.of(2, normalScores, frame3);
    private static final Frame startFrame = NormalFrame.of(Frames.START_FRAME, normalScores, frame2);

    @DisplayName("프레임의 최대 갯수가 10개인지 확인")
    @Test
    void 프레임_갯수_확인() {
        Frame firstFrame = NormalFrame.start();
        assertEquals(10, Frames.preview(firstFrame).count());
    }

    @DisplayName("첫 번째 프레임으로 부터 전체 프레임이 생성되는지 확인")
    @ParameterizedTest
    @MethodSource("provideFrameAndFrames")
    void 프레임_생성_확인(Frame firstFrame, long expected) {
        assertEquals(expected, Frames.framesByFirst(firstFrame).size());
    }

    private static Stream<Arguments> provideFrameAndFrames() {
        return Stream.of(
            Arguments.of(startFrame, 10),
            Arguments.of(frame2, 9),
            Arguments.of(frame3, 8),
            Arguments.of(frame4, 7),
            Arguments.of(frame5, 6),
            Arguments.of(frame6, 5),
            Arguments.of(frame7, 4),
            Arguments.of(frame8, 3),
            Arguments.of(frame9, 2),
            Arguments.of(lastFrame, 1)
        );
    }

    @DisplayName("주어진 프레임을 통하여 마지막 프레임을 확인하는 테스트")
    @ParameterizedTest
    @MethodSource("provideLastFrameAndFrame")
    void 마지막_프레임_테스트(Frame frame, Frame expected) {
        assertEquals(expected, Frames.getLastFrameOf(frame));
    }

    private static Stream<Arguments> provideLastFrameAndFrame() {
        return Stream.of(
            Arguments.of(startFrame, lastFrame),
            Arguments.of(frame2, lastFrame),
            Arguments.of(frame3, lastFrame),
            Arguments.of(frame4, lastFrame),
            Arguments.of(frame5, lastFrame),
            Arguments.of(frame6, lastFrame),
            Arguments.of(frame7, lastFrame),
            Arguments.of(frame8, lastFrame),
            Arguments.of(frame9, lastFrame)
        );
    }

}
