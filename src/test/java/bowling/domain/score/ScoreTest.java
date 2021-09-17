package bowling.domain.score;

import bowling.domain.score.Score;
import bowling.exception.BowlingStateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {
    @Test
    void create() {
        Score score = new Score(1,0);
        assertThat(score).isEqualTo(new Score(1,0));
    }

    @Test
    void strike(){
        assertThat(new Score(10, 2)).isEqualTo(Score.ofStrike());
    }

    @Test
    void spare(){
        assertThat(new Score(10,1)).isEqualTo(Score.ofSpare());
    }

    @Test
    void miss(){
        assertThat(new Score(3, 0)).isEqualTo(Score.ofMiss(1,2));
    }

    @DisplayName("이전 투구와 현재 투구의 합이 10을 넘어가면 에러")
    @Test
    void error_sum() {
        assertThatThrownBy(()->Score.ofMiss(5, 6)).isInstanceOf(BowlingStateException.class);
    }
}
