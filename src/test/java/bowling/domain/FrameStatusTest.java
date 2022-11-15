package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStatusTest {
    public static Stream<Arguments> provideRollsAndResult() {
        return Stream.of(
                Arguments.of(new Rolls(10), FrameStatus.STRIKE),
                Arguments.of(new Rolls(9, 1), FrameStatus.SPARE),
                Arguments.of(new Rolls(5, 2), FrameStatus.MISS),
                Arguments.of(new Rolls(1), FrameStatus.PROGRESS)
        );
    }

    public static Stream<Arguments> provideFrameAndScore() {
        return Stream.of(
                Arguments.of(FrameStatus.STRIKE, new Rolls(10), new Score(10, 2)),
                Arguments.of(FrameStatus.SPARE, new Rolls(9, 1), new Score(10, 1)),
                Arguments.of(FrameStatus.MISS, new Rolls(9, 0), new Score(9, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("provideRollsAndResult")
    void 프레임_정상_결과(Rolls rolls, FrameStatus result) {
        assertThat(FrameStatus.match(rolls)).isEqualTo(result);
    }

    @ParameterizedTest
    @MethodSource("provideFrameAndScore")
    void 프레임_점수_계산(FrameStatus status, Rolls rolls, Score score) {
        assertThat(status.calculateScore(rolls)).isEqualTo(score);
    }
}
