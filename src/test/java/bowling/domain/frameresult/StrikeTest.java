package bowling.domain.frameresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    void strikeTest() {
        Strike strike = new Strike();

        assertThat(strike.isCalculated()).isFalse();
        strike.addBonus(5);
        assertThat(strike.isCalculated()).isTrue();
        assertThat(strike.calculateScore()).isEqualTo(15);
    }
}
