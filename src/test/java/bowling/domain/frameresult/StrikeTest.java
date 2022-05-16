package bowling.domain.frameresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    void getScoreWithBonus() {
        assertThat(new Strike().getScoreWithBonus(9)).isEqualTo(19);
    }
}
