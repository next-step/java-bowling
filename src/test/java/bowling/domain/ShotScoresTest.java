package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ShotScoresTest {
    private ShotScores shotScores;

    @BeforeEach
    void setting() {
        shotScores = new ShotScores(new ArrayList<>());
        shotScores.add(4, false);
    }

    @Test
    void getLast() {
        assertThat(shotScores.getDtoList())
                .anyMatch(shotScoreDto -> shotScoreDto.getScore() == 4 &&
                        shotScoreDto.getScoreType().equals(ScoreType.MISS));
    }

    @Test
    void add() {
        shotScores.add(6, false);
        assertThat(shotScores.getDtoList())
                .anyMatch(shotScoreDto -> shotScoreDto.getScore() == 6 &&
                        shotScoreDto.getScoreType().equals(ScoreType.SPARE));
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
    void getDtoList() {
        assertThat(shotScores.getDtoList())
                .anyMatch(shotScoreDto ->
                        shotScoreDto.getScore() == 4 && shotScoreDto.getScoreType().equals(ScoreType.MISS));
    }
}