package bowling.domain;

import bowling.step2.domain.Frames;
import bowling.step2.domain.frame.FinalFrame;
import bowling.step2.domain.frame.Frame;
import bowling.step2.domain.frame.NormalFrame;
import bowling.step2.domain.scores.FinalScores;
import bowling.step2.domain.scores.NormalScores;
import bowling.step2.domain.scores.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FramesTest {

    @DisplayName("프레임의 최대 갯수가 10개인지 확인")
    @Test
    void 프레임_갯수_확인 () {
        assertEquals(10, Frames.init()
                                        .preview()
                                        .count());
    }

    @DisplayName("마지막 프레임으로 부터 전체 프레임이 생성되는지 확인")
    @ParameterizedTest
    @MethodSource("provideFrameAndFrames")
    void 프레임_생성_확인 (Frame lastFrame, long expected) {
        assertEquals(expected, Frames.ofLastFrame(lastFrame)
                                     .stream()
                                     .count());
    }

    private static Stream<Arguments> provideFrameAndFrames () {
        Scores normalScores = NormalScores.init();
        Scores finalScores = FinalScores.init();
        Frame frame1 = NormalFrame.of(1, normalScores, null);
        Frame frame2 = NormalFrame.of(2, normalScores, frame1);
        Frame frame3 = NormalFrame.of(3, normalScores, frame2);
        Frame frame4 = NormalFrame.of(4, normalScores, frame3);
        Frame frame5 = NormalFrame.of(5, normalScores, frame4);
        Frame frame6 = NormalFrame.of(6, normalScores, frame5);
        Frame frame7 = NormalFrame.of(7, normalScores, frame6);
        Frame frame8 = NormalFrame.of(8, normalScores, frame7);
        Frame frame9 = NormalFrame.of(9, normalScores, frame8);
        Frame frame10 = FinalFrame.of(10, finalScores, frame9);
        return Stream.of(
            Arguments.of(frame1, 1),
            Arguments.of(frame2, 2),
            Arguments.of(frame3, 3),
            Arguments.of(frame4, 4),
            Arguments.of(frame5, 5),
            Arguments.of(frame6, 6),
            Arguments.of(frame7, 7),
            Arguments.of(frame8, 8),
            Arguments.of(frame9, 9),
            Arguments.of(frame10, 10)
        );
    }
}
