package bowling;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    void 스코어는_누적된다() {
        Score score = Score.ofStrike().addScore(10).addScore(10);
        assertThat(score.getScore()).isEqualTo(30);
    }

    @Test
    void 시도_횟수가_남아있다면_에러() {
        Score score = Score.ofStrike();
        assertThatThrownBy(() -> score.getScore())
            .isInstanceOf(CannotCalculateException.class);
    }

    @Test
    void 미스() {
        Score score = Score.ofMiss(5);
        assertThat(score.getScore()).isEqualTo(5);
    }

}
