package bowling.domain.state.normal;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SpareTest {

    @Test
    void 생성() {
        FirstBowl firstBowl = new FirstBowl(PinCount.of(5));
        State next = firstBowl.next(PinCount.of(5));
        assertThat(next instanceof Spare).isTrue();
        assertThat(next.isFinish()).isTrue();
    }

    @Test
    void invalid() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Spare(PinCount.of(5), PinCount.of(2)));
    }

    @Test
    void 점수() {
        Spare spare = new Spare(5, 5);
        assertThat(spare.getScore()).isEqualTo(new Score(10, 1));
    }

    @Test
    void 보너스점수계산() {
        State spare = new Spare(6, 4);
        assertThat(spare.calculateBonusScore(new Score(3, 2))).isEqualTo(new Score(13, 0));
    }
}
