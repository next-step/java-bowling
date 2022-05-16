package bowling.domain.frameresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @Test
    void getScoreWithBonus() {
        assertThat(new Spare().getScoreWithBonus(9)).isEqualTo(19);
    }
}
