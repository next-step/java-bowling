package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {

    @Test
    void validation() {
        assertThatThrownBy(() -> new Spare(5, 4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void score_Exception() {
        Score spareScore = new Spare(5, 5).score();

        assertThatThrownBy(spareScore::getScore)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void additionalScore_ForSpare() {
        Spare spare = new Spare(5, 5);
        Score beforeScore = Score.ofSpare();

        Score afterScore = spare.additionalScore(beforeScore);

        // 10 + 5
        assertThat(afterScore.getScore()).isEqualTo(15);
    }

    @Test
    void additionalScore_ForStrike() {
        Score beforeScore = Score.ofStrike();
        Spare spare = new Spare(5, 5);

        Score afterScore = spare.additionalScore(beforeScore);

        // 10 + 5 + 5
        assertThat(afterScore.getScore()).isEqualTo(20);
    }

    @Test
    void expression() {
        assertThat(new Spare(8, 2).expression()).isEqualTo("8|/");
    }
}
