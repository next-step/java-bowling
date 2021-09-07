package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoresTest {
    @Test
    void create() {
        Scores scores = new Scores();
        assertThat(scores).isEqualTo(new Scores());
    }

    @DisplayName("첫번째 투구")
    @Test
    void add_first() {
        Scores scores = new Scores();
        scores.add(1);
        assertThat(scores.getScores().get(0)).isEqualTo(Score.MISS_1);
    }

    @DisplayName("miss 인 경우 두번째 투구")
    @Test
    void add_secondTurn() {
        Scores scores = new Scores();
        scores.add(1);
        scores.add(9);
        assertThat(scores.getScores().get(1)).isEqualTo(Score.SPARE);
    }

    @DisplayName("strike 인 경우 두번째 투구를 하지 않는다.")
    @Test
    void add_secondTurn_error() {
        Scores scores = new Scores();
        scores.add(10);
        assertThatThrownBy(() -> {
            scores.add(1);
        }).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("세번째 투구 시도시 에러 발생")
    @Test
    void add_thirdTurn_error() {
        Scores scores = new Scores();
        scores.add(1);
        scores.add(2);
        assertThatThrownBy(() -> {
            scores.add(1);
        }).isInstanceOf(IllegalStateException.class);
    }

}
