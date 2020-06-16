package bowling.step3.domain;

import bowling.step3.domain.frame.Frame;
import bowling.step3.domain.frame.NormalFrame;
import bowling.step3.domain.scores.FinalScores;
import bowling.step3.domain.scores.NormalScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalFrameTest {

    @DisplayName("현재 프레임이 종료되지 않은 상태에서 createNextFrame을 했을 때 null로 할당하는지 확인")
    @Test
    public void 다음_프레임_NULL_생성_테스트() {
        Frame frame = NormalFrame.of(1, NormalScores.init(), null);
        frame.createNextFrameOfScores(frame.getScores().nextInit(Score.valueOf(1)));
        assertEquals(null, frame.getNextFrame());
    }

    @DisplayName("현재 프레임이 완료 되었을 때 다음 프레임이 정상적으로 생성되는지 확인")
    @ParameterizedTest
    @MethodSource("provideFrameAndNextFrame")
    public void 다음_프레임_생성_테스트(NormalFrame frame) {
        assertEquals(true, frame.getNextFrame() != null);
    }

    private static Stream<Arguments> provideFrameAndNextFrame() {
        Frame frame1 = NormalFrame.of(1, NormalScores.init(), null);
        Frame frame2 = NormalFrame.of(2, NormalScores.init(), null);

        frame1.createNextFrameOfScores(frame1.getScores().nextInit(Score.getStrike()));
        frame2.createNextFrameOfScores(frame2.getScores().nextInit(Score.valueOf(1)));
        frame2.createNextFrameOfScores(frame2.getScores().nextInit(Score.valueOf(2)));
        return Stream.of(
            Arguments.of(frame1),
            Arguments.of(frame2)
        );
    }

    @DisplayName("Miss 점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("provideFrameAndMissScore")
    public void Miss_계산_테스트(NormalFrame frame, int expected) {
        assertEquals(expected, frame.calculateScore());
    }

    private static Stream<Arguments> provideFrameAndMissScore() {
        return Stream.of(
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.valueOf(1), Score.valueOf(2)), null),
                3
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.valueOf(0), Score.valueOf(9)), null),
                9
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.valueOf(8), Score.valueOf(0)), null),
                8
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.valueOf(5), Score.valueOf(4)), null),
                9
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.valueOf(0), Score.valueOf(0)), null),
                0
            )
        );
    }

    @DisplayName("Spared 점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("provideFrameAndSparedScore")
    public void Spared_계산_테스트(NormalFrame frame, int expected) {
        assertEquals(expected, frame.calculateScore());
    }

    private static Stream<Arguments> provideFrameAndSparedScore() {
        return Stream.of(
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.valueOf(0), Score.getStrike()),
                    NormalFrame.of(2, NormalScores.of(Score.valueOf(5), null), null)),
                15
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.valueOf(9), Score.valueOf(1)),
                    NormalFrame.of(2, NormalScores.of(Score.getStrike(), null), null)),
                20
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.valueOf(5), Score.valueOf(5)),
                    NormalFrame.of(2, NormalScores.of(Score.valueOf(0), null), null)),
                10
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.valueOf(4), Score.valueOf(6)),
                    NormalFrame.of(2, FinalScores.of(Score.valueOf(7), null), null)),
                17
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.valueOf(4), Score.valueOf(6)),
                    NormalFrame.of(2, FinalScores.of(null, null), null)),
                Frame.EMPTY_CALC
            )
        );
    }

    @DisplayName("Strike 점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("provideFrameAndStrikeScore")
    public void Strike_계산_테스트(NormalFrame frame, int expected) {
        assertEquals(expected, frame.calculateScore());
    }

    private static Stream<Arguments> provideFrameAndStrikeScore() {
        return Stream.of(
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.getStrike(), null),
                    NormalFrame.of(2, NormalScores.of(Score.valueOf(5), Score.valueOf(4)), null)),
                19
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.getStrike(), null),
                    NormalFrame.of(2, NormalScores.of(Score.valueOf(0), Score.getStrike()), null)),
                20
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.getStrike(), null),
                    NormalFrame.of(2, NormalScores.of(Score.valueOf(0), Score.valueOf(5)), null)),
                15
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.getStrike(), null),
                    NormalFrame.of(2, NormalScores.of(Score.valueOf(5), null), null)),
                Frame.EMPTY_CALC
            )
        );
    }
}
