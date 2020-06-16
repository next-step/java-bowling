package bowling.step3.domain;

import bowling.step3.domain.frame.*;
import bowling.step3.domain.scores.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinalFrameTest {

    @DisplayName("FinalFrame의 경우 다음 프레임은 항상 null이다.")
    @ParameterizedTest
    @MethodSource("provideFrameAndNextFrame")
    void 다음_프레임_생성_테스트(Frame frame, Frame nextFrame) {
        assertEquals(nextFrame, frame.getNextFrame());
    }

    private static Stream<Arguments> provideFrameAndNextFrame() {
        Frame finalFrame = FinalFrame.of(10, FinalScores.init());
        finalFrame.createNextFrameOfScores(
            finalFrame.getScores().nextInit(Score.valueOf(1))
        );
        Frame nextFrame = finalFrame.getNextFrame();
        return Stream.of(
            Arguments.of(finalFrame, null),
            Arguments.of(finalFrame, nextFrame)
        );
    }
}
