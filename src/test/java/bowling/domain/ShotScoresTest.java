package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(shotScores.isSize(2))
                .isTrue();
    }

    @Test
    void isSize() {
        assertThat(shotScores.isSize(1))
                .isTrue();

        assertThat(shotScores.isSize(2))
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
    void totalScoreTest() {
        ShotScores shotScores = ShotScores.of(Arrays.asList(ShotScore.init(4), ShotScore.init(5)));
        assertThat(shotScores.totalScore())
                .isEqualTo(9);
    }

}
