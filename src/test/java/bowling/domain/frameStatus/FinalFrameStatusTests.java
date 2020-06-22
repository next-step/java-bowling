package bowling.domain.frameStatus;

import bowling.domain.FrameResult;
import bowling.domain.FrameResults;
import bowling.domain.NumberOfHitPin;
import bowling.domain.exceptions.InvalidTryBowlException;
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

class FinalFrameStatusTests {
    private static final int ZERO = 0;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int TEN = 10;

    @DisplayName("초구를 던져서 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        FinalFrameStatus finalFrameResult = FinalFrameStatus.bowlFirst(FIVE);

        assertThat(finalFrameResult)
                .isEqualTo(new FinalFrameStatus(
                        new NumberOfHitPin(FIVE), null, null));
    }

    @DisplayName("초구를 던지지 않은 상태에서 두번재 투구를 던질 경우 예외 발생")
    @Test
    void bowlValidationTest() {
        FinalFrameStatus finalFrameResult =
                new FinalFrameStatus(null, null, null);

        assertThatThrownBy(() -> finalFrameResult.bowl(FIVE))
                .isInstanceOf(InvalidTryBowlException.class);
    }

    @DisplayName("초구가 스트라이크면 무조건 3회 투구해야 해당 프레임 결과가 마무리된다.")
    @Test
    void completeWhenFirstStrike() {
        FinalFrameStatus finalFrameResult = FinalFrameStatus.bowlFirst(TEN);
        assertThat(finalFrameResult.isCompleted()).isFalse();

        FinalFrameStatus afterSecond = finalFrameResult.bowl(FIVE);
        assertThat(afterSecond.isCompleted()).isFalse();

        FinalFrameStatus afterThird = afterSecond.bowl(FIVE);
        assertThat(afterThird.isCompleted()).isTrue();
    }

    @DisplayName("초구가 스트라이크가 아니고 두번째에 스페어 처리한 경우 3회 투구해야 해당 프레임 결과가 마무리된다.")
    @Test
    void completeWhenSpare() {
        FinalFrameStatus finalFrameResult = FinalFrameStatus.bowlFirst(FIVE);
        assertThat(finalFrameResult.isCompleted()).isFalse();

        FinalFrameStatus afterSecond = finalFrameResult.bowl(FIVE);
        assertThat(afterSecond.isCompleted()).isFalse();

        FinalFrameStatus afterThird = afterSecond.bowl(FIVE);
        assertThat(afterThird.isCompleted()).isFalse();
    }

    @DisplayName("초구가 스트라이크가 아니고 두번째가 미스인 경우 2회만 투구해도 프레임 결과가 마무리된다.")
    @Test
    void completeWhenMiss() {
        FinalFrameStatus finalFrameResult = FinalFrameStatus.bowlFirst(FIVE);
        assertThat(finalFrameResult.isCompleted()).isFalse();

        FinalFrameStatus afterSecond = finalFrameResult.bowl(FOUR);
        assertThat(afterSecond.isCompleted()).isTrue();
    }

    @DisplayName("현재 진행상태에 따른 결과를 확인할 수 있다.")
    @ParameterizedTest
    @MethodSource("calculateResource")
    void calculateResultsTest(FinalFrameStatus finalFrameStatus, FrameResults frameResults) {
        assertThat(finalFrameStatus.calculateCurrentResult()).isEqualTo(frameResults);
    }
    public static Stream<Arguments> calculateResource() {
        return Stream.of(
                Arguments.of(
                        FinalFrameStatus.bowlFirst(TEN),
                        new FrameResults(Collections.singletonList(FrameResult.STRIKE))
                ),
                Arguments.of(
                        FinalFrameStatus.bowlFirst(FIVE),
                        new FrameResults(Collections.singletonList(FrameResult.FIVE))
                ),
                Arguments.of(
                        FinalFrameStatus.bowlFirst(ZERO),
                        new FrameResults(Collections.singletonList(FrameResult.GUTTER))
                ),

                Arguments.of(
                        FinalFrameStatus.bowlFirst(TEN).bowl(FIVE),
                        new FrameResults(Arrays.asList(FrameResult.STRIKE, FrameResult.FIVE))
                ),
                Arguments.of(
                        FinalFrameStatus.bowlFirst(FIVE).bowl(FIVE),
                        new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.SPARE))
                ),
                Arguments.of(
                        FinalFrameStatus.bowlFirst(FIVE).bowl(FOUR),
                        new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.FOUR))
                ),

                Arguments.of(
                        FinalFrameStatus.bowlFirst(TEN).bowl(TEN).bowl(TEN),
                        new FrameResults(Arrays.asList(FrameResult.STRIKE, FrameResult.STRIKE, FrameResult.STRIKE))
                ),
                Arguments.of(
                        FinalFrameStatus.bowlFirst(TEN).bowl(TEN).bowl(FIVE),
                        new FrameResults(Arrays.asList(FrameResult.STRIKE, FrameResult.STRIKE, FrameResult.FIVE))
                ),
                Arguments.of(
                        FinalFrameStatus.bowlFirst(TEN).bowl(FOUR).bowl(FIVE),
                        new FrameResults(Arrays.asList(FrameResult.STRIKE, FrameResult.FOUR, FrameResult.FIVE))
                ),
                Arguments.of(
                        FinalFrameStatus.bowlFirst(TEN).bowl(FIVE).bowl(FIVE),
                        new FrameResults(Arrays.asList(FrameResult.STRIKE, FrameResult.FIVE, FrameResult.SPARE))
                ),
                Arguments.of(
                        FinalFrameStatus.bowlFirst(FIVE).bowl(FIVE).bowl(FOUR),
                        new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.SPARE, FrameResult.FOUR))
                ),
                Arguments.of(
                        FinalFrameStatus.bowlFirst(FIVE).bowl(FIVE).bowl(TEN),
                        new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.SPARE, FrameResult.STRIKE))
                )
        );
    }
}
