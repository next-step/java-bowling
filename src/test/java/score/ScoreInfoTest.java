package score;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreInfoTest {

    @Test
    void firstScore() {
        ScoreInfo strike = ScoreInfo.firstScore(10);

        assertThat(strike).isEqualTo(new ScoreInfo(new Score(10), Status.STRIKE));
    }

    @Test
    void nextScore() {
        ScoreInfo miss = ScoreInfo.firstScore(4);
        ScoreInfo spare = miss.nextScore(6);

        assertThat(spare).isEqualTo(new ScoreInfo(new Score(6), Status.SPARE));
    }
}