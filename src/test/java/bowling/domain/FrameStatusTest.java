package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStatusTest {
    public static Stream<Arguments> provideScoresAndResult() {
        return Stream.of(
                Arguments.of(new Rolls(10), FrameStatus.STRIKE),
                Arguments.of(new Rolls(9, 1), FrameStatus.SPARE),
                Arguments.of(new Rolls(5, 2), FrameStatus.MISS),
                Arguments.of(new Rolls(1), FrameStatus.PROGRESS)
        );
    }

    @ParameterizedTest
    @MethodSource("provideScoresAndResult")
    void 프레임_정상_결과(Rolls rolls, FrameStatus result) {
        assertThat(FrameStatus.match(rolls)).isEqualTo(result);
    }
}
