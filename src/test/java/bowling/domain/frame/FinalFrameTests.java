package bowling.domain.frame;

import bowling.domain.FrameResult;
import bowling.domain.FrameResults;
import bowling.domain.FrameScore;
import bowling.domain.FrameScoreStatus;
import bowling.domain.exceptions.InvalidTryBowlException;
import bowling.domain.frameStatus.FinalFrameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTests {
    private static final int TEN = 10;
    private static final int FIVE = 5;
    private static final int FOUR = 4;
    private static final int ZERO = 0;
    private static final NormalFrame NINTH_FRAME = NormalFrame.start(FIVE).bowl(FIVE);

    @DisplayName("초구로 맞춘 핀의 수와 아홉번째 프레임을 입력 받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        FinalFrame finalFrame = FinalFrame.bowlFirst(FIVE, NINTH_FRAME);

        assertThat(finalFrame).isEqualTo(new FinalFrame(10, FinalFrameStatus.bowlFirst(FIVE), NINTH_FRAME));
    }

    @DisplayName("종료될 때까지 마지막 프레임을 진행할 수 있다.")
    @Test
    void bowlTest() {
        FinalFrame finalFrame = FinalFrame.bowlFirst(FIVE, NINTH_FRAME);
        assertThat(finalFrame.isCompleted()).isFalse();

        FinalFrame secondBowled = finalFrame.bowl(FIVE);
        assertThat(secondBowled.isCompleted()).isFalse();

        FinalFrame thirdBowled = secondBowled.bowl(TEN);
        assertThat(thirdBowled.isCompleted()).isTrue();
    }

    @DisplayName("종료된 이후에는 프레임을 진행할 수 없다.")
    @Test
    void bowlValidationTest() {
        FinalFrame finalFrame = FinalFrame.bowlFirst(FIVE, NINTH_FRAME);
        assertThat(finalFrame.isCompleted()).isFalse();

        FinalFrame secondBowled = finalFrame.bowl(FOUR);
        assertThat(secondBowled.isCompleted()).isTrue();

        assertThatThrownBy(() -> secondBowled.bowl(FIVE))
                .isInstanceOf(InvalidTryBowlException.class);
    }

    @DisplayName("현재 진행 상태에 따른 결과를 확인할 수 있다.")
    @ParameterizedTest
    @MethodSource("calculateResource")
    void calculateResultsTest(FinalFrame finalFrame, FrameResults expectedResults) {
        assertThat(finalFrame.calculateCurrentResults()).isEqualTo(expectedResults);
    }
    public static Stream<Arguments> calculateResource() {
        return Stream.of(
                Arguments.of(
                        new FinalFrame(NINTH_FRAME, FinalFrameStatus.bowlFirst(TEN)),
                        new FrameResults(Collections.singletonList(FrameResult.STRIKE))
                ),
                Arguments.of(
                        new FinalFrame(NINTH_FRAME, FinalFrameStatus.bowlFirst(FIVE)),
                        new FrameResults(Collections.singletonList(FrameResult.FIVE))
                ),
                Arguments.of(
                        new FinalFrame(NINTH_FRAME, FinalFrameStatus.bowlFirst(ZERO)),
                        new FrameResults(Collections.singletonList(FrameResult.GUTTER))
                ),

                Arguments.of(
                        new FinalFrame(NINTH_FRAME, FinalFrameStatus.bowlFirst(TEN).bowl(FIVE)),
                        new FrameResults(Arrays.asList(FrameResult.STRIKE, FrameResult.FIVE))
                ),
                Arguments.of(
                        new FinalFrame(NINTH_FRAME, FinalFrameStatus.bowlFirst(FIVE).bowl(FIVE)),
                        new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.SPARE))
                ),
                Arguments.of(
                        new FinalFrame(NINTH_FRAME, FinalFrameStatus.bowlFirst(FIVE).bowl(FOUR)),
                        new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.FOUR))
                ),

                Arguments.of(
                        new FinalFrame(NINTH_FRAME, FinalFrameStatus.bowlFirst(TEN).bowl(TEN).bowl(TEN)),
                        new FrameResults(Arrays.asList(FrameResult.STRIKE, FrameResult.STRIKE, FrameResult.STRIKE))
                ),
                Arguments.of(
                        new FinalFrame(NINTH_FRAME, FinalFrameStatus.bowlFirst(TEN).bowl(TEN).bowl(FIVE)),
                        new FrameResults(Arrays.asList(FrameResult.STRIKE, FrameResult.STRIKE, FrameResult.FIVE))
                ),
                Arguments.of(
                        new FinalFrame(NINTH_FRAME, FinalFrameStatus.bowlFirst(TEN).bowl(FOUR).bowl(FIVE)),
                        new FrameResults(Arrays.asList(FrameResult.STRIKE, FrameResult.FOUR, FrameResult.FIVE))
                ),
                Arguments.of(
                        new FinalFrame(NINTH_FRAME, FinalFrameStatus.bowlFirst(TEN).bowl(FIVE).bowl(FIVE)),
                        new FrameResults(Arrays.asList(FrameResult.STRIKE, FrameResult.FIVE, FrameResult.SPARE))
                ),
                Arguments.of(
                        new FinalFrame(NINTH_FRAME, FinalFrameStatus.bowlFirst(FIVE).bowl(FIVE).bowl(FOUR)),
                        new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.SPARE, FrameResult.FOUR))
                ),
                Arguments.of(
                        new FinalFrame(NINTH_FRAME, FinalFrameStatus.bowlFirst(FIVE).bowl(FIVE).bowl(TEN)),
                        new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.SPARE, FrameResult.STRIKE))
                )
        );
    }

    @DisplayName("현재 상태의 점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("calculateCurrentScoreResource")
    void calculateCurrentScoreTest(FinalFrame finalFrame, FrameScoreStatus frameScoreStatus, Integer scoreValue) {
        assertThat(finalFrame.calculateCurrentScore())
                .isEqualTo(new FrameScore(frameScoreStatus, scoreValue));
    }
    public static Stream<Arguments> calculateCurrentScoreResource() {
        return Stream.of(
                Arguments.of(
                        FinalFrame.bowlFirst(10, null),
                        FrameScoreStatus.NOT_READY,
                        10
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(5, null),
                        FrameScoreStatus.NOT_READY,
                        5
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(10, NormalFrame.start(10)).bowl(5),
                        FrameScoreStatus.NOT_READY,
                        15
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(5, NormalFrame.start(10)).bowl(5),
                        FrameScoreStatus.NOT_READY,
                        10
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(5, NormalFrame.start(10)).bowl(2),
                        FrameScoreStatus.COMPLETE,
                        7
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(10, NormalFrame.start(10)).bowl(10),
                        FrameScoreStatus.NOT_READY,
                        20
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(5, NormalFrame.start(5)).bowl(5).bowl(10),
                        FrameScoreStatus.COMPLETE,
                        20
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(10, NormalFrame.start(5)).bowl(5).bowl(3),
                        FrameScoreStatus.COMPLETE,
                        18
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(10, NormalFrame.start(5)).bowl(5).bowl(5),
                        FrameScoreStatus.COMPLETE,
                        20
                )
        );
    }

    @DisplayName("이전 프레임 점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("calculatePreviousFrameResource")
    void calculatePreviousFrameTest(FinalFrame finalFrame, FrameScore expectedResult) {
        assertThat(finalFrame.calculatePreviousScore()).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> calculatePreviousFrameResource() {
        return Stream.of(
                // 이전 프레임이 스페어인 경우
                Arguments.of(
                        FinalFrame.bowlFirst(10, NormalFrame.start(5).bowl(5)),
                        new FrameScore(FrameScoreStatus.COMPLETE, 20)
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(10, NormalFrame.start(5).bowl(5)).bowl(10),
                        new FrameScore(FrameScoreStatus.COMPLETE, 20)
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(10, NormalFrame.start(5).bowl(5)).bowl(10).bowl(10),
                        new FrameScore(FrameScoreStatus.COMPLETE, 20)
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(10, NormalFrame.start(5).bowl(5)).bowl(10).bowl(5),
                        new FrameScore(FrameScoreStatus.COMPLETE, 20)
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(5, NormalFrame.start(5).bowl(5)).bowl(4),
                        new FrameScore(FrameScoreStatus.COMPLETE, 15)
                ),

                // 이전 프레임이 스트라이크인 경우
                Arguments.of(
                        FinalFrame.bowlFirst(5, NormalFrame.start(10)),
                        new FrameScore(FrameScoreStatus.NOT_READY, 15)
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(10, NormalFrame.start(10)),
                        new FrameScore(FrameScoreStatus.NOT_READY, 10)
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(5, NormalFrame.start(10)).bowl(5),
                        new FrameScore(FrameScoreStatus.COMPLETE, 20)
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(5, NormalFrame.start(10)).bowl(4),
                        new FrameScore(FrameScoreStatus.COMPLETE, 19)
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(5, NormalFrame.start(10)).bowl(5).bowl(10),
                        new FrameScore(FrameScoreStatus.COMPLETE, 20)
                ),

                // 스트라이크, 스페어도 아닌 일반 상태일 때
                Arguments.of(
                        FinalFrame.bowlFirst(5, NormalFrame.start(5).bowl(4)),
                        new FrameScore(FrameScoreStatus.COMPLETE, 9)
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(5, NormalFrame.start(5).bowl(4)).bowl(5),
                        new FrameScore(FrameScoreStatus.COMPLETE, 9)
                ),
                Arguments.of(
                        FinalFrame.bowlFirst(5, NormalFrame.start(5).bowl(4)).bowl(5).bowl(10),
                        new FrameScore(FrameScoreStatus.COMPLETE, 9)
                )
        );
    }
}
