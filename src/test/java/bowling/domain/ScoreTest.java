package bowling.domain;

import org.junit.jupiter.api.Test;

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

    @Test
    void 생성_invalid() {
        assertThatThrownBy(() -> new Score(-1, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 생성_valid() {
        assertThatCode(() -> new Score(0, 0)).doesNotThrowAnyException();
        assertThat(new Score(10, 0).score()).isEqualTo(10);
    }

    @Test
    void 게임진행_계산불가() {
        Score score = new Score(10, 2).bowl(10);

        assertThat(score).isEqualTo(new Score(20, 1));
        assertThat(score.canCalculateScore()).isFalse();
        assertThatThrownBy(score::score)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 게임진행_계산가능() {
        Score score = new Score(10, 1).bowl(10);

        assertThat(score).isEqualTo(new Score(20, 0));
        assertThat(score.canCalculateScore()).isTrue();
        assertThat(score.score()).isEqualTo(20);
    }

    @Test
    void 게임진행_leftBowlCount_가_0인_경우() {
        Score score = new Score(10, 0).bowl(10);

        assertThat(score).isEqualTo(new Score(10, 0));
    }
}