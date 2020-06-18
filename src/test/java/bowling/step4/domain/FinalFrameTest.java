package bowling.step4.domain;

import bowling.step4.domain.frame.*;
import bowling.step4.domain.scores.*;
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
        Frame finalFrame = FinalFrame.of(Frames.LAST_FRAME, FinalScores.init());
        finalFrame.createNextFrameOfScores(
            finalFrame.getScores().nextInit(Score.valueOf(1))
        );
        Frame nextFrame = finalFrame.getNextFrame();
        return Stream.of(
            Arguments.of(finalFrame, null),
            Arguments.of(finalFrame, nextFrame)
        );
    }

    @DisplayName("Miss 점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("provideFrameAndMissScore")
    public void Miss_계산_테스트(FinalFrame frame, int expected) {
        assertEquals(expected, frame.calculateScore());
    }

    private static Stream<Arguments> provideFrameAndMissScore() {
        return Stream.of(
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.valueOf(1), Score.valueOf(2))),
                3
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.valueOf(0), Score.valueOf(9))),
                9
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.valueOf(8), Score.valueOf(0))),
                8
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.valueOf(5), Score.valueOf(4))),
                9
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.valueOf(0), Score.valueOf(0))),
                0
            )
        );
    }

    @DisplayName("Spared 점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("provideFrameAndSparedScore")
    public void Spared_계산_테스트(FinalFrame frame, int expected) {
        assertEquals(expected, frame.calculateScore());
    }

    private static Stream<Arguments> provideFrameAndSparedScore() {
        return Stream.of(
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.valueOf(0), Score.getStrike(), Score.valueOf(5))),
                15
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.valueOf(9), Score.valueOf(1), Score.getStrike())),
                20
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.valueOf(5), Score.valueOf(5), Score.valueOf(0))),
                10
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.valueOf(4), Score.valueOf(6), Score.valueOf(7))),
                17
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.valueOf(4), Score.valueOf(6), null)),
                Frame.EMPTY_CALC
            )
        );
    }

    @DisplayName("Strike 점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("provideFrameAndStrikeScore")
    public void Strike_계산_테스트(FinalFrame frame, int expected) {
        assertEquals(expected, frame.calculateScore());
    }

    private static Stream<Arguments> provideFrameAndStrikeScore() {
        return Stream.of(
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.getStrike(), Score.valueOf(5), Score.valueOf(5))),
                20
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.getStrike(), Score.valueOf(9), Score.getStrike())),
                29
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.getStrike(), Score.valueOf(5), Score.valueOf(0))),
                15
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.getStrike(), Score.valueOf(6), Score.valueOf(7))),
                23
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.getStrike(), Score.valueOf(5), null)),
                Frame.EMPTY_CALC
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.getStrike(), null, null)),
                Frame.EMPTY_CALC
            )
        );
    }

    @DisplayName("Two Strike 점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("provideFrameAndTwoStrikeScore")
    public void Two_Strike_계산_테스트(FinalFrame frame, int expected) {
        assertEquals(expected, frame.calculateScore());
    }

    private static Stream<Arguments> provideFrameAndTwoStrikeScore() {
        return Stream.of(
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.getStrike(), Score.getStrike(), Score.valueOf(5))),
                25
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.getStrike(), Score.getStrike(), Score.getStrike())),
                30
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.getStrike(), Score.getStrike(), Score.valueOf(0))),
                20
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.getStrike(), Score.getStrike(), Score.valueOf(7))),
                27
            ),
            Arguments.of(
                FinalFrame.of(Frames.LAST_FRAME, FinalScores.of(Score.getStrike(), Score.getStrike(), null)),
                Frame.EMPTY_CALC
            )
        );
    }
}
