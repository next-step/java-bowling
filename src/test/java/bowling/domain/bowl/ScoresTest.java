package bowling.domain.bowl;

import bowling.dto.ScoreDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class ScoresTest {

    @Test
    @DisplayName("accumulate 가 누적되는지 테스트")
    void accumulate() {
        Scores scores = new Scores();
        scores.accumulate(8);
        scores.accumulate(15);
        scores.accumulate(29);
        scores.accumulate(3);
        assertThat(scores.exportScoresDto().getScores().stream().map(ScoreDto::getScore).collect(toList()))
                .isEqualTo(Arrays.asList(8, 23, 52, 55));
    }
}
