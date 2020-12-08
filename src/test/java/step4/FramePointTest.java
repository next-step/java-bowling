package step4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step4.domain.BowlingGame;
import step4.domain.Frames;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FramePointTest {

    @DisplayName("올 스트라이크 점수 테스트")
    @ParameterizedTest
    @MethodSource("provideAllStrikeFrames")
    void allStrikePoint(Frames frames, int totalPoint, List<String> strings) {
        assertThat(frames.getScores()).isEqualTo(totalPoint);

        assertThat(frames.getScores(frames.getHead()))
                .isEqualTo(strings);
    }

    private static Stream<Arguments> provideAllStrikeFrames() {
        Frames frames = BowlingGame.build();
        while (!frames.isFinished()) {
            BowlingGame.pitches(frames, 10);
        }

        List<String> strings = Arrays.asList("30", "60", "90", "120", "150", "180", "210", "240", "270", "300");

        return Stream.of(
                Arguments.of(frames, 300, strings)
        );
    }

    @DisplayName("볼링 점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("provideBowlingScoreInfo")
    void calculateBowlingScores(Frames frames, int totalScore, List<String> result) {
        assertThat(frames.getScores()).isEqualTo(totalScore);
        assertThat(frames.getScores(frames.getHead())).isEqualTo(result);

    }

    private static Stream<Arguments> provideBowlingScoreInfo() {
        Frames frames = BowlingGame.build();
        List<Integer> pitchesCounts = Arrays.asList(1, 2, 3, 4, 5, 4, 5, 4, 3, 7, 10, 6, 4, 2, 8, 0, 2, 10, 5, 4);
        pitchesCounts.forEach(pitchesCount -> BowlingGame.pitches(frames, pitchesCount));
        List<String> pitchesCountStrings = Arrays.asList("3", "10", "19", "28", "48", "68", "80", "90", "92", "111");

        return Stream.of(
                Arguments.of(frames, 111, pitchesCountStrings)
        );
    }


}
