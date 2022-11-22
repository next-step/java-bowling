package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score2;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class Score2Test {

    @Test
    void 점수계산() {
        Score2 score2 = new Score2(8, 0);
        assertThat(score2.getValue()).isEqualTo(8);
    }

    @Test
    void 점수계산가능() {
        Score2 score2 = new Score2(8, 0);
        assertThat(score2.canCalculate()).isTrue();
    }

    @Test
    void 예외() {
        Score2 score2 = new Score2(10, 2);
        assertThatIllegalStateException().isThrownBy(() -> score2.getValue());
    }

    @Test
    void 더하기() {
        Score2 score = new Score2(10, 2);

        Score2 next = score.add(PinCount.of(5));
        assertThat(next).isEqualTo(new Score2(15, 1));

        Score2 next2 = next.add(PinCount.of(2));
        assertThat(next2).isEqualTo(new Score2(17, 0));
    }
}
