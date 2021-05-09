package bowling.domain.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {
    public static final Spare SP1 = Spare.of(1);
    public static final Spare SPARE_3 = Spare.of(3);

    @Test
    void 스페어생성() {
        assertThat(SP1.isSpare()).isTrue();
        assertThat(SP1.isStrike()).isFalse();
        assertThat(SP1.eval().compareTo(10)).isEqualTo(0);
    }

    @Test
    void 스페어에_추가점수는_한번만가능하다() {
        RollResultType spare = Spare.of(3).next(5);
        assertThat(spare.eval().compareTo(15)).isEqualTo(0);
        assertThat(spare.next(10).eval().compareTo(15)).isEqualTo(0);
    }
}
