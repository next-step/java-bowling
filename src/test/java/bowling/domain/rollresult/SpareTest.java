package bowling.domain.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {
    public static final Spare SP1 = Spare.of(1);

    @Test
    void 스페어생성() {
        assertThat(SP1.isSpare()).isTrue();
        assertThat(SP1.isStrike()).isFalse();
        assertThat(SP1.eval()).isEqualTo(10);
    }

    @Test
    void 스페어에_추가점수는_한번만가능하다() {
        RollResultType spare = Spare.of(3).next(5);
        assertThat(spare.eval()).isEqualTo(15);
        assertThat(spare.next(10).eval()).isEqualTo(15);
    }
}
