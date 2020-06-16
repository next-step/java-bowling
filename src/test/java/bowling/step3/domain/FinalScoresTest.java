package bowling.step3.domain;

import bowling.step3.domain.frame.FinalFrame;
import bowling.step3.domain.frame.Frame;
import bowling.step3.domain.frame.NormalFrame;
import bowling.step3.domain.scores.FinalScores;
import bowling.step3.domain.scores.NormalScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinalScoresTest {

    @DisplayName("스코어의 첫 번째 값이 스트라이크인지 확인하는 테스트")
    @ParameterizedTest
    @MethodSource("provideStrikeScores")
    void 스트라이크_테스트(FinalScores scores) {
        assertEquals(true, scores.isType(ScoreType.STRIKE));
    }

    private static Stream<Arguments> provideStrikeScores() {
        return Stream.of(
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(10))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(10))
                           .nextInit(Score.valueOf(1))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(10))
                           .nextInit(Score.valueOf(2))
            )
        );
    }

    @DisplayName("첫 번째 스코어와 두 번째 스코어가 채워졌는지 확인하는 테스트")
    @ParameterizedTest
    @MethodSource("provideFullyFirstAndSecondScore")
    void 스코어_채워짐_확인_테스트(FinalScores scores) {
        assertEquals(true, scores.isFull());
    }

    private static Stream<Arguments> provideFullyFirstAndSecondScore() {
        return Stream.of(
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(1))
                           .nextInit(Score.valueOf(2))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(3))
                           .nextInit(Score.valueOf(4))
                           .nextInit(null)
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(5))
                           .nextInit(Score.valueOf(6))
                           .nextInit(Score.valueOf(7))
            )
        );
    }

    @DisplayName("보너스 스코어가 채워졌는지 확인")
    @ParameterizedTest
    @MethodSource("provideFullyBonusScore")
    void 보너스_채워짐_확인_테스트(FinalScores scores) {
        assertEquals(true, scores.filledBonus());
    }

    private static Stream<Arguments> provideFullyBonusScore() {
        return Stream.of(
            Arguments.of(FinalScores.of(null, null, Score.valueOf(1))),
            Arguments.of(FinalScores.of(null, Score.valueOf(2), Score.valueOf(3))),
            Arguments.of(FinalScores.of(Score.valueOf(4), null, Score.valueOf(5))),
            Arguments.of(FinalScores.of(Score.valueOf(6), Score.valueOf(7), Score.valueOf(8)))
        );
    }

    @DisplayName("스코어가 스페어 되었는지 확인하는 테스트")
    @ParameterizedTest
    @MethodSource("provideSparedScores")
    void 스코어_스페어_테스트(FinalScores scores) {
        assertEquals(true, scores.isType(ScoreType.SPARED));
    }

    private static Stream<Arguments> provideSparedScores() {
        return Stream.of(
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(1))
                           .nextInit(Score.valueOf(9))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(2))
                           .nextInit(Score.valueOf(8))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(3))
                           .nextInit(Score.valueOf(7))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(4))
                           .nextInit(Score.valueOf(6))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(5))
                           .nextInit(Score.valueOf(5))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(1))
                           .nextInit(Score.valueOf(9))
                           .nextInit(Score.valueOf(1))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(2))
                           .nextInit(Score.valueOf(8))
                           .nextInit(Score.valueOf(2))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(3))
                           .nextInit(Score.valueOf(7))
                           .nextInit(Score.valueOf(3))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(4))
                           .nextInit(Score.valueOf(6))
                           .nextInit(Score.valueOf(4))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(5))
                           .nextInit(Score.valueOf(5))
                           .nextInit(Score.valueOf(5))
            )
        );
    }

    @DisplayName("스코어들의 합계를 확인하는 테스트")
    @ParameterizedTest
    @MethodSource("provideScoresAndSum")
    void 스코어_합계_테스트(FinalScores scores, int expected) {
        assertEquals(expected, scores.totalScore());
    }

    private static Stream<Arguments> provideScoresAndSum() {
        return Stream.of(
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(1)),
                1
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(2))
                           .nextInit(Score.valueOf(8)),
                10
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(0))
                           .nextInit(Score.valueOf(7)),
                7
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(0))
                           .nextInit(Score.valueOf(0)),
                0
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(2))
                           .nextInit(Score.valueOf(8))
                           .nextInit(Score.valueOf(0)),
                10
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(0))
                           .nextInit(Score.valueOf(7))
                           .nextInit(Score.valueOf(7)),
                7
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(0))
                           .nextInit(Score.valueOf(0))
                           .nextInit(Score.valueOf(0)),
                0
            )
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
                FinalFrame.of(10, FinalScores.of(Score.valueOf(1), Score.valueOf(2))),
                3
            ),
            Arguments.of(
                FinalFrame.of(10, FinalScores.of(Score.valueOf(0), Score.valueOf(9))),
                9
            ),
            Arguments.of(
                FinalFrame.of(10, FinalScores.of(Score.valueOf(8), Score.valueOf(0))),
                8
            ),
            Arguments.of(
                FinalFrame.of(10, FinalScores.of(Score.valueOf(5), Score.valueOf(4))),
                9
            ),
            Arguments.of(
                FinalFrame.of(10, FinalScores.of(Score.valueOf(0), Score.valueOf(0))),
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
                FinalFrame.of(1, FinalScores.of(Score.valueOf(0), Score.getStrike(), Score.valueOf(5))),
                15
            ),
            Arguments.of(
                FinalFrame.of(1, FinalScores.of(Score.valueOf(9), Score.valueOf(1), Score.getStrike())),
                20
            ),
            Arguments.of(
                FinalFrame.of(1, FinalScores.of(Score.valueOf(5), Score.valueOf(5), Score.valueOf(0))),
                10
            ),
            Arguments.of(
                FinalFrame.of(1, FinalScores.of(Score.valueOf(4), Score.valueOf(6), Score.valueOf(7))),
                17
            ),
            Arguments.of(
                FinalFrame.of(1, FinalScores.of(Score.valueOf(4), Score.valueOf(6), null)),
                Frame.EMPTY_CALC
            ),
            Arguments.of(
                FinalFrame.of(9, FinalScores.of(Score.valueOf(4), Score.valueOf(6), null)),
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
                    NormalFrame.of(2, NormalScores.init(), null)),
                Frame.EMPTY_CALC
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.getStrike(), null),
                    NormalFrame.of(2, NormalScores.of(Score.valueOf(5), null), null)),
                Frame.EMPTY_CALC
            )
        );
    }

    @DisplayName("Two Strike 점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("provideFrameAndTwoStrikeScore")
    public void Two_Strike_계산_테스트(NormalFrame frame, int expected) {
        assertEquals(expected, frame.calculateScore());
    }

    private static Stream<Arguments> provideFrameAndTwoStrikeScore() {
        return Stream.of(
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.getStrike(), null),
                    NormalFrame.of(2, NormalScores.of(Score.getStrike(), null),
                        NormalFrame.of(3, NormalScores.of(Score.getStrike(), null), null))),
                30
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.getStrike(), null),
                    NormalFrame.of(2, NormalScores.of(Score.getStrike(), null),
                        NormalFrame.of(3, NormalScores.of(Score.valueOf(0), null), null))),
                20
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.getStrike(), null),
                    NormalFrame.of(2, NormalScores.of(Score.getStrike(), null),
                        NormalFrame.of(3, NormalScores.of(Score.valueOf(5), null), null))),
                25
            ),
            Arguments.of(
                NormalFrame.of(9, NormalScores.of(Score.getStrike(), null),
                    FinalFrame.of(10, FinalScores.of(Score.getStrike(), Score.valueOf(9)))),
                29
            ),
            Arguments.of(
                NormalFrame.of(9, NormalScores.of(Score.getStrike(), null),
                    FinalFrame.of(10, FinalScores.of(Score.getStrike(), Score.getStrike()))),
                30
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.getStrike(), null),
                    NormalFrame.of(2, NormalScores.init(), null)),
                Frame.EMPTY_CALC
            ),
            Arguments.of(
                NormalFrame.of(1, NormalScores.of(Score.getStrike(), null),
                    NormalFrame.of(2, NormalScores.init(),
                        NormalFrame.of(3, NormalScores.init(), null))),
                Frame.EMPTY_CALC
            ),
            Arguments.of(
                NormalFrame.of(9, NormalScores.of(Score.getStrike(), null),
                    FinalFrame.of(10, FinalScores.init())),
                Frame.EMPTY_CALC
            ),
            Arguments.of(
                NormalFrame.of(9, NormalScores.of(Score.getStrike(), null),
                    FinalFrame.of(10, FinalScores.of(Score.getStrike(), null))),
                Frame.EMPTY_CALC
            )
        );
    }
}
