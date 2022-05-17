package bowling.domain.frameresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @Test
    void missTest() {
        assertThat(new Miss(5, 4).calculateScore()).isEqualTo(9);
        assertThat(new Miss(5, 4).isCalculated()).isTrue();
    }
}
