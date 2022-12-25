package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {


    @DisplayName("점수가 생성된다.")
    @Test
    void bowl() {
        Score score = new Score(5, 0);
        assertThat(score).isEqualTo(new Score(5, 0));
    }

    @DisplayName("스페어는 다음 1번의 투구까지 점수를 합산")
    @Test
    void spare() {
        Score score = new Score(10, 1).bowl(5);
        assertThat(score).isEqualTo(new Score(15, 0));
    }

    @DisplayName("스트라이크는 다음 2번의 투구까지 점수를 합산")
    @Test
    void strike() {
        Score score = new Score(10, 2).bowl(10).bowl(10);
        assertThat(score).isEqualTo(new Score(30, 0));
    }
}
