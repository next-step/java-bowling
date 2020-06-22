package bowling.domain.frame;

import bowling.domain.FrameResult;
import bowling.domain.FrameResults;
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
        FinalFrame finalFrame = FinalFrame.firstBowl(FIVE, NINTH_FRAME);

        assertThat(finalFrame).isEqualTo(new FinalFrame(10, FinalFrameStatus.bowlFirst(FIVE), NINTH_FRAME));
    }

    @DisplayName("종료될 때까지 마지막 프레임을 진행할 수 있다.")
    @Test
    void bowlTest() {
        FinalFrame finalFrame = FinalFrame.firstBowl(FIVE, NINTH_FRAME);
        assertThat(finalFrame.isCompleted()).isFalse();

        FinalFrame secondBowled = finalFrame.bowl(FIVE);
        assertThat(secondBowled.isCompleted()).isFalse();

        FinalFrame thirdBowled = secondBowled.bowl(TEN);
        assertThat(thirdBowled.isCompleted()).isTrue();
    }

    @DisplayName("종료된 이후에는 프레임을 진행할 수 없다.")
    @Test
    void bowlValidationTest() {
        FinalFrame finalFrame = FinalFrame.firstBowl(FIVE, NINTH_FRAME);
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
}
