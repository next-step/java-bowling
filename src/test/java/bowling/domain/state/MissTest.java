package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score2;
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
        assertThat(miss.getScore()).isEqualTo(new Score2(7, 0));
    }
}
