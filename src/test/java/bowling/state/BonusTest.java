package bowling.state;

import bowling.domain.PinCounts;
import bowling.domain.Score;
import bowling.domain.state.Bonus;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BonusTest {
    private final State strikeBonus = Bonus.ofStrike(new PinCounts(10, 5));
    private final State spareBonus = Bonus.ofSpare(new PinCounts(5, 5).knockOut(4));

    @Test
    void 추가점수계산이_2회인경우_대상으로_보너스인_경우_점수계산() {
        Score score = Score.ofStrike();

        score = strikeBonus.additionalCalculate(score);
        assertThat(score).isEqualTo(new Score(25, 0));
    }
    @Test
    void 추가점수계산이_1회인경우_대상으로_보너스인_경우_점수계산() {
        Score score = Score.ofSpare();

        score = spareBonus.additionalCalculate(score);
        assertThat(score).isEqualTo(new Score(15, 0));
    }
}
