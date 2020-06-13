package bowling.domain;

import bowling.step2.domain.frame.Frame;
import bowling.step2.domain.frame.FinalFrame;
import bowling.step2.domain.scores.FinalScores;
import bowling.step2.domain.scores.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinalFrameTest {

    @DisplayName("다음 프레임을 정상적으로 생성하고 있는지 확인")
    @ParameterizedTest
    @MethodSource("provideFrameAndNextFrame")
    void 다음_프레임_생성_테스트 (Frame frame, Frame prevFrame) {
        assertEquals(prevFrame, frame.getPrevFrame());
    }

    private static Stream<Arguments> provideFrameAndNextFrame () {
        Scores scores = FinalScores.init();
        Frame frame = FinalFrame.of(10, scores, null);
        Frame nextFrame1 = frame.createNextFrame(scores);
        Frame nextFrame2 = nextFrame1.createNextFrame(scores);
        Frame nextFrame3 = nextFrame2.createNextFrame(scores);
        return Stream.of(
            Arguments.of(frame, null),
            Arguments.of(nextFrame1, null),
            Arguments.of(nextFrame2, null),
            Arguments.of(nextFrame3, null)
        );
    }
}
