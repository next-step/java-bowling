package bowling.domain.score;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    void 생성_테스트() {
        // given
        Score score = Score.of(5);
        Score score2 = Score.ofSpare();
        Score score3 = Score.ofStrike();
        // when & then
        assertThat(score).isEqualTo(Score.of(5, 0));
        assertThat(score2).isEqualTo(Score.of(10, 1));
        assertThat(score3).isEqualTo(Score.of(10, 2));
    }

    @Test
    void 더하기_테스트() {
        // given
        Score score = Score.of(5);
        Score score2 = Score.of(5);
        Score score3 = Score.of(5);
        // when & then
        assertThat(score.add(Score.of(5))).isEqualTo(Score.of(10, 0));
        assertThat(score2.add(Score.ofSpare())).isEqualTo(Score.of(15, 1));
        assertThat(score3.add(Score.ofStrike())).isEqualTo(Score.of(15, 2));
    }

    @Test
    void 누적_테스트() {
        // given
        Score score = Score.of(5);
        Score score2 = Score.ofSpare();
        Score score3 = Score.ofStrike();
        // when & then
        assertThat(score.accumulate(5)).isEqualTo(Score.of(10, -1));
        assertThat(score2.accumulate(5)).isEqualTo(Score.of(15, 0));
        assertThat(score3.accumulate(5)).isEqualTo(Score.of(15, 1));
    }
}
