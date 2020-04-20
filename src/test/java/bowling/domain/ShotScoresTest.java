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
        shotScores.add(4, false);
    }

    @Test
    void add() {
        shotScores.add(6, false);
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
        assertThat(shotScores.isClear())
                .isFalse();

        shotScores.add(6, false);

        assertThat(shotScores.isClear())
                .isTrue();
    }

    @Test
    void totalScore(){
        ShotScores shotScores = ShotScores.of(Arrays.asList(ShotScore.of(4),ShotScore.of(5)));
        assertThat(shotScores.totalScore(2))
                .isEqualTo(9);

        assertThat(shotScores.totalScore(1))
                .isEqualTo(4);
    }

    @Test
    void totalScoreTest(){
        ShotScores shotScores = ShotScores.of(Arrays.asList(ShotScore.of(4),ShotScore.of(5)));
        assertThat(shotScores.totalScore())
                .isEqualTo(9);
    }

}
