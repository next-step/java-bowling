package bowling.domain;

import bowling.domain.score.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    void 스크트라이크() {
        //given
        Score score = Score.ofStrike();
        //when & then
        assertThat(score.score()).isEqualTo(10);
        assertThat(score.bonusCount()).isEqualTo(2);
    }

    @Test
    void 스페어() {
        //given
        Score score = Score.ofSpare();
        //when & then
        assertThat(score.score()).isEqualTo(10);
        assertThat(score.bonusCount()).isEqualTo(1);
    }

    @Test
    void 미스() {
        //given
        Score score = Score.ofMiss(5);
        //when & then
        assertThat(score.score()).isEqualTo(5);
        assertThat(score.bonusCount()).isEqualTo(0);
    }
}
