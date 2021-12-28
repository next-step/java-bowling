package bowling.state;

import bowling.domain.KnockedPinCount;
import bowling.domain.Score;
import bowling.domain.state.Bonus;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {
    private final State spare = new Spare(new KnockedPinCount(5), new KnockedPinCount(5));

    @Test
    void 스페어에서_한번더투구하면_보너스를반환() {
        assertThat(spare.bowl(5)).isInstanceOf(Bonus.class);
    }

    @Test
    void 추가점수계산이_2회인경우_대상으로_스페어인_경우_점수계산() {
        Score score = Score.ofStrike();

        score = spare.additionalCalculate(score);
        assertThat(score).isEqualTo(new Score(20, 0));
    }

    @Test
    void 추가점수계산이_1회인경우_대상으로_스페어인_경우_점수계산() {
        Score score = Score.ofSpare();

        score = spare.additionalCalculate(score);
        assertThat(score).isEqualTo(new Score(15, 0));
    }
}
