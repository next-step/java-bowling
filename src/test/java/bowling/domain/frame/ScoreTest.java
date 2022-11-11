package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreTest {
    @Test
    public void getScoreWhenStrike() {
        Score score = new Score(10, 2);
        assertThat(score.addBonusScore(10).addBonusScore(9).getValue()).isEqualTo(29);
    }

    @Test
    public void getScoreWhenSpare() {
        Score score = new Score(10, 1);
        assertThat(score.addBonusScore(9).getValue()).isEqualTo(19);
    }

    @Test
    public void getScoreWhenMiss() {
        Score score = new Score(7, 0);
        assertThat(score.getValue()).isEqualTo(7);
    }

    @Test
    public void addScoreWhenNoMoreLeft() {
        Score score = new Score(7, 0);
        assertThatThrownBy(() -> score.addBonusScore(4))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("점수를 더 이상 합산할 수 없습니다");
    }

    @Test
    public void getScoreWhenIllegalState() {
        assertThatThrownBy(() -> new Score(10, 2).getValue())
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("아직 스코어를 계산할 수 없는 상태입니다");
    }
}
