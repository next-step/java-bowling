package bowling.domain.frameresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    void calculateScore() {
        Strike strike = new Strike();
        Bonus bonus = new Bonus();

        assertThat(strike.calculateScore(bonus)).isEmpty();
        bonus.setStrikeBonus(10);
        assertThat(strike.calculateScore(bonus)).contains(20);
    }
}
