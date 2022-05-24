package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void score10ShouldBeDone() {
        Score score = new Score(10);
        assertThat(score.done()).isTrue();
    }

    @Test
    void scoreLowerThan9ShouldNotDone() {
        Score score = new Score(1);
        assertThat(score.done()).isFalse();
    }

    @Test
    void payloadForEmpty() {
        assertThat(Score.payload(new Score())).isEqualTo(Score.format(" "));
    }

    @Test
    void payloadForStrike() {
        assertThat(Score.payload(new Score(10))).isEqualTo(Score.format("X"));
    }

    @Test
    void payloadForSecond() {
        assertThat(Score.payload(new Score(2))).isEqualTo(Score.format("2"));
    }

    @Test
    void payloadForGutter() {
        assertThat(Score.payload(new Score(0, 0))).isEqualTo(Score.format("-|-"));
        assertThat(Score.payload(new Score(1, 0))).isEqualTo(Score.format("1|-"));
        assertThat(Score.payload(new Score(0, 1))).isEqualTo(Score.format("-|1"));
    }

    @Test
    void payloadForMiss() {
        assertThat(Score.payload(new Score(1, 2))).isEqualTo(Score.format("1|2"));
    }

    @Test
    void payloadForSpare() {
        assertThat(Score.payload(new Score(1, 9))).isEqualTo(Score.format("1|/"));
    }

    @Test
    void onlyWithSecondScoreShouldBeDone() {
        assertThat(new Score(1).done()).isFalse();
        assertThat(new Score(1, 2).done()).isTrue();
    }
}
