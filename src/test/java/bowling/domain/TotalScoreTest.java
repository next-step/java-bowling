package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TotalScoreTest {

    @Test
    void createTest() {
        TotalScore totalScore = TotalScore.ofInitialStrike();

        assertThat(totalScore).isNotNull();
        assertThat(totalScore.get()).isEqualTo(10);
    }

}
