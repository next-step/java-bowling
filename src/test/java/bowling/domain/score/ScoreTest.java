package bowling.domain.score;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    void 생성_테스트() {
        // given
        Score score = Score.of(5);
        // when & then
        assertThat(score).isEqualTo(Score.of(5, 0));
    }

    @Test
    void 더하기_테스트() {
        // given
        Score score = Score.of(5);
        // when & then
        assertThat(score.add(Score.of(5))).isEqualTo(Score.of(10, 0));
    }

    @Test
    void 누적_테스트() {
        // given
        Score score = Score.of(5);
        // when & then
        assertThat(score.accumulate(5)).isEqualTo(Score.of(10, -1));
    }
}
