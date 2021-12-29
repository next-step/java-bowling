package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @DisplayName("Score 생성")
    @Test
    void create() {
        assertThat(Score.init().getScore()).isEqualTo(0);
    }

    @DisplayName("bonus 가 0인 경우 점수를 표시할 수 있다.")
    @Test
    void canCalculate() {
        assertThat(Score.of(7).canCalculate()).isTrue();
    }

    @DisplayName("Score를 더할 때마다 bonus 수는 하나씩 감소한다.")
    @Test
    void addScore() {
        assertThat(Score.strike()
                .addScore(Score.next(ScoreValue.of(10), ScoreBonus.oneMore()))
                .canCalculate()
        ).isTrue();
    }

}
