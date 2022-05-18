package bowling.domain.frameresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @Test
    void calculateScore() {
        Spare spare = new Spare();
        Bonus bonus = new Bonus();

        assertThat(spare.calculateScore(bonus)).isEmpty();
        bonus.setSpareBonus(7);
        assertThat(spare.calculateScore(bonus)).contains(17);
    }
}
