package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;


public class NormalScoreTest {

    @Test
    void create() {
        assertThat(new NormalScore()).isInstanceOf(NormalScore.class);
    }

    @Test
    void isNotNextScore() {
        Score score = new NormalScore();
        score = score.firstScore(Pins.of(10));
        assertThat(score.isNextScore()).isFalse();
    }

    @Test
    void isNextScore() {
        Score score = new NormalScore();
        score = score.firstScore(Pins.of(9));
        assertThat(score.isNextScore()).isTrue();
    }

}
