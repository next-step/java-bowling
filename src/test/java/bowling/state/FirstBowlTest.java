package bowling.state;

import bowling.domain.Score;
import bowling.domain.state.FirstBowl;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FirstBowlTest {
    private final FirstBowl firstBowl = new FirstBowl(5);

    @Test
    void 첫번째투구상태에서_모두쓰러뜨리면_스페어를반환() {
        assertThat(firstBowl.bowl(5)).isInstanceOf(Spare.class);
    }

    @Test
    void 첫번째투구상태에서_10개를_넘게_쓰러뜨리면_예외() {
        assertThatThrownBy(() -> firstBowl.bowl(6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 첫번째투구상태에서_모두_쓰러뜨리지_못하면_미스를반환() {
        assertThat(firstBowl.bowl(4)).isInstanceOf(Miss.class);
    }

    @Test
    void 추가점수계산이_2회인경우_대상으로_스페어인_경우_점수계산() {
        Score score = Score.ofStrike();

        score = firstBowl.additionalCalculate(score);
        assertThat(score).isEqualTo(new Score(15, 1));
    }

    @Test
    void 추가점수계산이_1회인경우_대상으로_스페어인_경우_점수계산() {
        Score score = Score.ofSpare();

        score = firstBowl.additionalCalculate(score);
        assertThat(score).isEqualTo(new Score(15, 0));
    }
}
