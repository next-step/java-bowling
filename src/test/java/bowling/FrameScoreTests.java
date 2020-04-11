package bowling;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("FrameScoreTests")
public class FrameScoreTests {
    /*
     * FrameScore
     * 볼링 프레임의 점수를 관리한다.
     * Score 리스트를 관리한다.
     * Score 의 합을 반환한다.
     * 남은 투구 기회가 있는지 반환한다.
     */

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void generateFrameScoreTest() {
        assertThatCode(() -> FrameScore.newInstance(Arrays.asList(Score.of(5), Score.of(4))));
    }

    @DisplayName("FrameScore 생성 테스트")
    @NullAndEmptySource
    @Test
    public void generateFrameScoreAbnormalTest(List<Score> scores) {
        assertThatCode(() -> FrameScore.newInstance(scores)));
    }

    @DisplayName("FrameScore 합 테스트")
    @Test
    public void generateFrameScoreSumTest() {
        FrameScore frameScore = FrameScore.newInstance(Arrays.asList(Score.of(5), Score.of(4)));
        assertThat(frameScore.sum()).isEqualTo(9);
    }

    @DisplayName("FrameScore 종료 테스트")
    @ParameterizedTest
    @MethodSource("generateFrameScoreOverTestCases")
    public void generateFrameScoreOverTest(List<Score> scores, boolean isLastFrame, boolean expectedResult) {
        FrameScore frameScore = FrameScore.newInstance(scores);
        assertThat(frameScore.isOver(isLastFrame)).isEqualsTo(expectedResult);
    }

    private static Stream<Arguments> generateFrameScoreOverTestCases() {
        return Stream.of(
                Arguments.of(Collections.singletonList(Score.of(5)), false, false),
                Arguments.of(Arrays.asList(Score.of(5), Score.of(4)), false, true),
                Arguments.of(Arrays.asList(Score.of(5), Score.of(5)), false, true),
                Arguments.of(Arrays.asList(Score.of(5), Score.of(5)), true, false),
                Arguments.of(Arrays.asList(Score.of(5), Score.of(5), Score.of(5)), true, true)
        );
    }
}
