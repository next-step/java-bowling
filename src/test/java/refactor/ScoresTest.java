package refactor;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {
    @Test
    void pitch두번하면done() {
        Scores scores = new Scores();
        Scores playing = scores.pitch(5);
        assertThat(playing.done()).isFalse();
        Scores done = playing.pitch(4);
        assertThat(done.done()).isTrue();
    }

    @Test
    void strike이면한번에done() {
        Scores scores = new Scores();
        Scores done = scores.pitch(10);
        assertThat(done.done()).isTrue();
    }

    @Test
    void sum은9점() {
        Scores scores = new Scores();
        Scores playing = scores.pitch(5);
        Scores done = playing.pitch(4);
        assertThat(done.sum()).isEqualTo(9);
    }

    @Test
    void evaluateBonus_SPARE_BONUS() {
        Scores scores = new Scores(new ArrayList<>(List.of(1, 9)), 0);
        scores = scores.evaluateLastBonus();
        assertThat(scores).isEqualTo(new Scores(new ArrayList<>(List.of(1, 9)), 1));
        scores = scores.pitch(8);
        assertThat(scores).isEqualTo(new Scores(new ArrayList<>(List.of(1, 9, 8)), 0));
    }

    @Test
    void evaluateBonus_STRIKE_BONUS_BONUS() {
        Scores scores = new Scores(new ArrayList<>(List.of(10)), 0);
        scores = scores.evaluateLastBonus();
        assertThat(scores).isEqualTo(new Scores(new ArrayList<>(List.of(10)), 1));
        scores = scores.pitch(8);
        scores = scores.evaluateLastBonus();
        assertThat(scores).isEqualTo(new Scores(new ArrayList<>(List.of(10, 8)), 1));
        scores = scores.pitch(1);
        scores = scores.evaluateLastBonus();
        assertThat(scores).isEqualTo(new Scores(new ArrayList<>(List.of(10, 8, 1)), 0));
    }

    @Test
    void evaluateBonus_STRIKE_STRIKE_STRIKE() {
        Scores scores = new Scores(new ArrayList<>(List.of(10)), 0);
        scores = scores.evaluateLastBonus();
        assertThat(scores).isEqualTo(new Scores(new ArrayList<>(List.of(10)), 1));
        scores = scores.pitch(10);
        assertThat(scores).isEqualTo(new Scores(new ArrayList<>(List.of(10, 10)), 0));
        scores = scores.evaluateLastBonus();
        assertThat(scores).isEqualTo(new Scores(new ArrayList<>(List.of(10, 10)), 1));
        scores = scores.pitch(10);
        scores = scores.evaluateLastBonus();
        assertThat(scores).isEqualTo(new Scores(new ArrayList<>(List.of(10, 10, 10)), 0));
    }

    @Test
    void evaluateBonus_MISS() {
        Scores scores = new Scores(new ArrayList<>(List.of(1, 2)), 0);
        scores = scores.evaluateLastBonus();
        assertThat(scores).isEqualTo(new Scores(new ArrayList<>(List.of(1, 2)), 0));
    }

    @Test
    void name() {
        Scores scores = new Scores();
        scores.pitch(6);
        scores = scores.evaluateLastBonus();
        scores = scores.pitch(4);
        scores = scores.evaluateLastBonus();
        scores = scores.pitch(0);
        scores = scores.evaluateLastBonus();
        assertThat(scores).isEqualTo(new Scores(new ArrayList<>(List.of(6, 4, 0)), 0));
    }
}
