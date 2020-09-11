package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    public static final Frame PREVIOUS = NormalFrame.create();
    public static final Frame NORMAL_FRAME = NormalFrame.create();
    public static final Frame FINAL_FRAME = NormalFrame.create();
    public static final int STRIKE_BOWL_COUNT = 10;

    @DisplayName("NormalFrame 초구 결과 테스트")
    @ParameterizedTest
    @MethodSource("makeFirstBowlResultData")
    void bowl_first_result(int firstValue, BowlResult expectedResult) {
        Frame frame = FINAL_FRAME;

        BowlResult actual = frame.bowl(firstValue);

        assertThat(actual).isEqualTo(expectedResult);
        assertThat(frame.canBowl()).isFalse();
    }

    private static Stream<Arguments> makeFirstBowlResultData() {
        return Stream.of(
                Arguments.of(0, BowlResult.GUTTER),
                Arguments.of(10, BowlResult.STRIKE),
                Arguments.of(3, BowlResult.DEFAULT)
        );
    }

    @DisplayName("NormalFrame 투구 결과 테스트")
    @ParameterizedTest
    @MethodSource("makeBowlResultData")
    void bowl_total_result(int firstValue, BowlResult expectedFirstResult, int secondValue, BowlResult expectSecondResult) {
        Frame frame = NORMAL_FRAME;

        BowlResult firstResult = frame.bowl(firstValue);
        BowlResult secondResult = frame.bowl(secondValue);

        assertThat(firstResult).isEqualTo(expectedFirstResult);
        assertThat(secondResult).isEqualTo(expectSecondResult);
    }

    private static Stream<Arguments> makeBowlResultData() {
        return Stream.of(
                Arguments.of(3, BowlResult.DEFAULT, 7, BowlResult.SPARE),
                Arguments.of(0, BowlResult.GUTTER, 3, BowlResult.MISS),
                Arguments.of(7, BowlResult.DEFAULT, 0, BowlResult.GUTTER)
        );
    }

}
