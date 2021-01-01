package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    private Score score;

    @Test
    void hasNoTryLeftTest() {
        score = new Score(10,0);
        assertThat(score.hasNoTryLeft()).isTrue();
        assertThat(score.hasOneTryLeft()).isFalse();
        assertThat(score.hasTwoTriesLeft()).isFalse();
    }

    @Test
    void hasOneTryLeftTest() {
        score = new Score(10,1);
        assertThat(score.hasNoTryLeft()).isFalse();
        assertThat(score.hasOneTryLeft()).isTrue();
        assertThat(score.hasTwoTriesLeft()).isFalse();
    }

    @Test
    void hasTwoTriesLeftTest() {
        score = new Score(10,2);
        assertThat(score.hasNoTryLeft()).isFalse();
        assertThat(score.hasOneTryLeft()).isFalse();
        assertThat(score.hasTwoTriesLeft()).isTrue();
    }

    @Test
    void getFallenPinsTest() {
        score = new Score(5,1);
        assertThat(score.getFallenPins()).isEqualTo(5);
    }

    @Test
    void equalsTest() {
        score = new Score(10,1);
        Score spareScore = new Score(10,1);
        Score strikeScore = new Score(10,2);
        assertThat(score.equals(spareScore)).isTrue();
        assertThat(score.equals(strikeScore)).isFalse();
    }
}
