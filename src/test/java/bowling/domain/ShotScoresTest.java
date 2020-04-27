package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ShotScoresTest {
    private ShotScores shotScores;

    @BeforeEach
    void setting() {
        shotScores = ShotScores.of(new ArrayList<>());
        shotScores.add(ShotScore.init(4));
    }

    @Test
    void add() {
        shotScores.add(ShotScore.init(6));
        assertThat(shotScores.hasSize(2))
                .isTrue();
    }

    @Test
    void isSize() {
        assertThat(shotScores.hasSize(1))
                .isTrue();

        assertThat(shotScores.hasSize(2))
                .isFalse();
    }

    @Test
    void isClear() {
        assertThat(shotScores.hasClear())
                .isFalse();

        shotScores.add(shotScores.getNext(6));

        assertThat(shotScores.hasClear())
                .isTrue();
    }

    @Test
    void totalScore() {
        ShotScores shotScores = ShotScores.of(Arrays.asList(ShotScore.init(4), ShotScore.init(5)));
        assertThat(shotScores.totalScore())
                .isEqualTo(9);
    }

    @Test
    void isScoreCalculated() {
        ShotScore firstShot = ShotScore.init(4);
        ShotScores shotScores = ShotScores.of(new ArrayList<>(Collections.singletonList(firstShot)));
        assertThat(shotScores.isScoreCalculated())
                .isTrue();

        shotScores.add(firstShot.next(6));
        assertThat(shotScores.isScoreCalculated())
                .isFalse();

        shotScores.getNext(3);
        assertThat(shotScores.isScoreCalculated())
                .isTrue();
    }

    @Test
    void getFrameScoreException() {
        ShotScore firstShot = ShotScore.init(4);
        ShotScores shotScores = ShotScores.of(new ArrayList<>(Collections.singletonList(firstShot)));
        assertThatThrownBy(shotScores::getCalculateScore)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void getFrameScore() {
        ShotScore firstShot = ShotScore.init(4);
        ShotScores shotScores = ShotScores.of(new ArrayList<>(Collections.singletonList(firstShot)));
        shotScores.add(shotScores.getNext(6));
        assertThat(shotScores.getCalculateScore())
                .matches(v -> !v.isScoreCalculated());
    }

}
