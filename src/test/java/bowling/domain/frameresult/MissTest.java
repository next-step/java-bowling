package bowling.domain.frameresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @Test
    void calculateScore() {
        Miss miss = new Miss(5, 4);
        Bonus bonus = new Bonus();

        assertThat(miss.calculateScore(bonus)).contains(9);
    }
}
