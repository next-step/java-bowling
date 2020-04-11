package bowling;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("BowlingFrame 테스트")
public class BowlingFrameTests {

    @DisplayName("BowlingFrame 생성 테스트")
    @Test
    public void generateBowlingFrameTest() {
        assertThatCode(() -> BowlingFrame.newInstance(false));
        assertThatCode(() -> BowlingFrame.newInstance(true));
    }

    @DisplayName("BowlingFrame 종료 테스트")
    @ParameterizedTest
    @MethodSource("generateBowlingFrameOverTestCases")
    public void generateBowlingFrameOverTest(List<Integer> scores, boolean isLastFrame, boolean expectedResult) {
        BowlingFrame bowlingFrame = BowlingFrame.newInstance(scores, isLastFrame);
        assertThat(bowlingFrame.isOver()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> generateBowlingFrameOverTestCases() {
        return Stream.of(
                Arguments.of(Collections.singletonList(5), false, false),
                Arguments.of(Collections.singletonList(10), false, true),
                Arguments.of(Collections.singletonList(10), true, false),
                Arguments.of(Arrays.asList(5, 4), false, true),
                Arguments.of(Arrays.asList(5, 5), false, true),
                Arguments.of(Arrays.asList(5, 5), true, false),
                Arguments.of(Arrays.asList(10, 5), true, false),
                Arguments.of(Arrays.asList(5, 5, 5), true, true)
        );
    }
}
