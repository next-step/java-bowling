package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class ScoreTest {

    @Test
    void 점수계산() {
        Score score = new Score(8, 0);
        assertThat(score.getValue()).isEqualTo(8);
    }

    @Test
    void 점수계산가능() {
        Score score = new Score(8, 0);
        assertThat(score.canCalculate()).isTrue();
    }

    @Test
    void 예외() {
        Score score = new Score(10, 2);
        assertThatIllegalStateException().isThrownBy(() -> score.getValue());
    }

    @Test
    void 더하기() {
        Score score = new Score(10, 2);

        Score next = score.add(PinCount.of(5));
        assertThat(next).isEqualTo(new Score(15, 1));

        Score next2 = next.add(PinCount.of(2));
        assertThat(next2).isEqualTo(new Score(17, 0));
    }
}
