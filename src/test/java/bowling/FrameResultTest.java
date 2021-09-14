package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameResultTest {
    @ParameterizedTest
    @MethodSource("generateFrameResults")
    @DisplayName("보너스 점수 타입 확인")
    void isBonusResultTest(FrameResult frameResult, boolean expected) {
        assertThat(frameResult.isBonusResult()).isEqualTo(expected);
    }

    private static Stream<Arguments> generateFrameResults() {
        return Stream.of(
                Arguments.of(FrameResult.SPARE, true),
                Arguments.of(FrameResult.STRIKE, true),
                Arguments.of(FrameResult.NONE, false),
                Arguments.of(FrameResult.MISS, false)
        );
    }
}
