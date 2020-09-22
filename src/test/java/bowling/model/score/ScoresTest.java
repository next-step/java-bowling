package bowling.model.score;

import bowling.model.frame.Frames;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {
    @ParameterizedTest
    @MethodSource("createScoreTestCase")
    public void of(Scores scores, List<Score> excepted) {
        assertThat(scores.getScores()).containsExactlyElementsOf(excepted);
    }

    private static Stream<Arguments> createScoreTestCase() {
        return Stream.of(
                // Full score
                Arguments.of(
                        initScores(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
                        initScoreList(30, 60, 90, 120, 150, 180, 210, 240, 270, 300)),

                // Zero score
                Arguments.of(
                        initScores(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                        initScoreList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),

                // Spare
                Arguments.of(
                        initScores(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
                        initScoreList(15, 30, 45, 60, 75, 90, 105, 120, 135, 150)),

                // UNKNOWN
                Arguments.of(
                        initScores(5, 0, 7, 1, 2, 4),
                        initScoreList(5, 13, 19, -1, -1, -1, -1, -1, -1, -1))
        );
    }

    private static Scores initScores(int... results) {
        Frames frames = new Frames();
        for (int result : results) {
            frames.addResult(result);
        }
        return frames.getScores();
    }

    private static List<Score> initScoreList(int... scores) {
        return Arrays.stream(scores)
                .boxed()
                .map(i -> {
                    if (i == -1) {
                        return Score.UNKNOWN;
                    }
                    return Score.of(i);
                })
                .collect(Collectors.toList());
    }
}
