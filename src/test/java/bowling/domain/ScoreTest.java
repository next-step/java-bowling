package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    void createTest() {
        Score score = new Score();

        assertThat(score).isNotNull();
    }

    @Test
    void createSpareTest() {
        Score spare = Score.ofSpare();

        assertThat(spare).isEqualTo(new Score(10, 1));
    }

    @Test
    void createStrikeTest() {
        Score strike = Score.ofStrike();

        assertThat(strike).isEqualTo(new Score(10, 2));
    }

}
