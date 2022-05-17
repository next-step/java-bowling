package bowling.domain.frameresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @Test
    void spareTest() {
        Spare spare = new Spare();

        assertThat(spare.isCalculated()).isFalse();
        spare.addBonus(5);
        assertThat(spare.isCalculated()).isTrue();
        assertThat(spare.calculateScore()).isEqualTo(15);
    }
}
