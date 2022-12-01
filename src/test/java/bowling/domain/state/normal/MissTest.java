package bowling.domain.state.normal;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.domain.state.normal.FirstBowl;
import bowling.domain.state.normal.Miss;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @Test
    void 생성() {
        FirstBowl firstBowl = new FirstBowl(PinCount.of(5));
        State state = firstBowl.next(PinCount.of(2));
        assertThat(state instanceof Miss);
    }

    @Test
    void 종료() {
        State miss = Miss.from(5, 2);
        assertThat(miss.isFinish()).isTrue();
    }

    @Test
    void 점수() {
        State miss = Miss.from(5, 2);
        assertThat(miss.getScore()).isEqualTo(new Score(7, 0));
    }

    @Test
    void 보너스점수계산() {
        State miss = Miss.from(5, 2);
        assertThat(miss.calculateBonusScore(new Score(10, 2))).isEqualTo(new Score(17, 0));
    }

    @Test
    void 보너스점수계산2() {
        State miss = Miss.from(5, 2);
        assertThat(miss.calculateBonusScore(new Score(10, 1))).isEqualTo(new Score(15, 0));
    }
}
