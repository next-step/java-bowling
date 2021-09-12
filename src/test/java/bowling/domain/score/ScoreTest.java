package bowling.domain.score;

import bowling.exception.BowlingScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {
    @Test
    void create() {
        Score score = new Score(1);
        assertThat(score).isEqualTo(new Score(1));
    }

    @Test
    void strike(){
        assertThat(new Score(10).getScoreType()).isEqualTo(ScoreType.STRIKE);
    }

    @Test
    void spare(){
        assertThat(new Score(new Score(1),9).getScoreType()).isEqualTo(ScoreType.SPARE);
    }

    @Test
    void miss(){
        assertThat(new Score(1).getScoreType()).isEqualTo(ScoreType.MISS);
        assertThat(new Score(9).getScoreType()).isEqualTo(ScoreType.MISS);
    }

    @Test
    void gutter(){
        assertThat(new Score(0).getScoreType()).isEqualTo(ScoreType.GUTTER);
    }

    @DisplayName("점수가 0점 미만이면 에러")
    @Test
    void error_min() {
        assertThatThrownBy(()->new Score(-1)).isInstanceOf(BowlingScoreException.class);
    }

    @DisplayName("점수가 10점을 초과하면 에러")
    @Test
    void error_max() {
        assertThatThrownBy(()->new Score(11)).isInstanceOf(BowlingScoreException.class);
    }

    @DisplayName("이전 투구와 현재 투구의 합이 10을 넘어가면 에러")
    @Test
    void error_sum() {
        assertThatThrownBy(()->new Score(new Score(5),6)).isInstanceOf(BowlingScoreException.class);
    }

    @DisplayName("이전 투구가 strike이거나 spare였으면 이전투구와 현재 투구의 합이 10을 넘어가도 된다.")
    @Test
    void sum() {
        assertThat(new Score(new Score(10),6).getScoreType()).isEqualTo(ScoreType.MISS);
        assertThat(new Score(new Score(new Score(5),5),2).getScoreType()).isEqualTo(ScoreType.MISS);
    }
}
