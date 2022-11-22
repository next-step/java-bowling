package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score2;
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
        Spare spare = new Spare(PinCount.of(5), PinCount.of(5));
        assertThat(spare.getScore()).isEqualTo(new Score2(10, 1));
    }
}
