package bowling.domain.score;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ScoresTest {
    @Test
    void 누적_테스트() {
        // given
        Scores scores = new Scores();
        // when
        scores.accumulate(Score.of(5));
        scores.accumulate(Score.ofSpare());
        // then
        List<Score> scoreList = scores.toList();
        assertThat(scoreList.get(0)).isEqualTo(Score.of(5));
        assertThat(scoreList.get(1)).isEqualTo(Score.of(15, 1));
    }
}
