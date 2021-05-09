package bowling.domain.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {
    public static final Spare SPARE_1 = Spare.of(1);
    public static final Spare SPARE_3 = Spare.of(3);

    @Test
    void 스페어생성() {
        assertThat(SPARE_1.isSpare()).isTrue();
        assertThat(SPARE_1.isStrike()).isFalse();
        assertThat(SPARE_1.eval().isClear()).isTrue();
        assertThat(SPARE_1.isCalculated()).isFalse();
    }

    @Test
    void 스페어는_추가점수계산이가능하다() {
        RollResultType next = SPARE_1.next(3);
//        assertThat(next.eval()).isEqualTo(Score.of(13, 1));
        assertThat(next.isCalculated()).isTrue();
    }

    @Test
    void 스페어에_추가점수는_한번만가능하다() {
        RollResultType spare = SPARE_1.next(5);
        assertThat(spare.eval().compareTo(15)).isEqualTo(0);
        assertThat(spare.next(10).eval().compareTo(15)).isEqualTo(0);
    }

}
