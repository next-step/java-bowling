package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalScoresTest {
    @Test
    void create() {
        NormalScores normalScores = new NormalScores();
        assertThat(normalScores).isEqualTo(new NormalScores());
    }

    @DisplayName("첫번째 투구")
    @Test
    void firstTurn() {
        NormalScores normalScores = new NormalScores();
        normalScores.add(1);
        assertThat(normalScores.getScores().get(0)).isEqualTo(Score.MISS_1);
    }

    @DisplayName("miss 인 경우 2번째 투구를 한다.")
    @Test
    void secondTurn() {
        NormalScores normalScores = new NormalScores();
        normalScores.add(1);
        normalScores.add(9);
        assertThat(normalScores.getScores().get(1)).isEqualTo(Score.SPARE);
    }

    @DisplayName("strike 인 경우 2번째 투구를 하지 않는다.")
    @Test
    void secondTurn_strike() {
        NormalScores normalScores = new NormalScores();
        normalScores.add(10);
        assertThatThrownBy(() -> {
            normalScores.add(1);
        }).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("3번째 투구 시도시 에러 발생")
    @Test
    void thirdTurn_error() {
        NormalScores normalScores = new NormalScores();
        normalScores.add(1);
        normalScores.add(2);
        assertThatThrownBy(() -> {
            normalScores.add(1);
        }).isInstanceOf(IllegalStateException.class);
    }

}
