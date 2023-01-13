package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class ScoreTest {

    @Test
    void Strike_초기_Score_생성() {
        assertThat(Score.ofStrike()).isEqualTo(new Score(10, 2));
    }

    @Test
    void Spare_초기_Score_생성() {
        assertThat(Score.ofSpare()).isEqualTo(new Score(10, 1));
    }

    @Test
    void Miss_초기_Score_생성() {
        assertThat(Score.ofMiss(5)).isEqualTo(new Score(5, 0));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 생성_invalid(int initialScore) {
        assertThatThrownBy(() -> new Score(initialScore, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    void 생성_valid(int initialScore) {
        assertThatCode(() -> new Score(initialScore, 0))
                .doesNotThrowAnyException();
    }

    @Test
    void 게임진행_계산불가() {
        Score score = new Score(10, 2);
        score.bowl(10);

        assertThatThrownBy(score::score)
                .isInstanceOf(IllegalStateException.class);
        assertThat(score.canCalculateScore()).isFalse();
    }

    @Test
    void 게임진행_계산가능() {
        Score score = new Score(10, 1);
        score.bowl(10);

        assertThat(score.score()).isEqualTo(20);
        assertThat(score.canCalculateScore()).isTrue();
    }
}