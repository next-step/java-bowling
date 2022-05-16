package bowling.domain.frameresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @Test
    void getScoreWithBonus() {
        assertThat(new Miss(5, 4).getScoreWithBonus(0)).isEqualTo(9);
    }
}
