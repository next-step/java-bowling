package bowling.domain;

import bowling.dto.ScoreDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class ScoresTest {

    private Scores scores;

    @BeforeEach
    void setUp() {
        scores = new Scores();
    }

    @ParameterizedTest
    @DisplayName("size 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void size(int size) {
        scores.accumulate(IntStream
                .range(0, size)
                .boxed()
                .collect(toList()));
        assertThat(scores.size())
                .isEqualTo(size);
    }

    @Test
    @DisplayName("scores 가 누적되어 추가되어야 한다.")
    void exportScoresDto() {
        List<Integer> scoreList = Arrays.asList(0, 1, 3, 6, 12);
        scores.accumulate(scoreList.stream()
                .collect(toList()));
        assertThat(scores
                .exportScoresDto()
                .getScores()
                .stream()
                .map(ScoreDto::getScore)
                .collect(toList()))
                .isEqualTo(Arrays.asList(0, 1, 4, 10, 22));
    }
}
