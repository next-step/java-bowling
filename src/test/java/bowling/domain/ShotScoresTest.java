package bowling.domain;

import bowling.domain.scoreType.ScoreType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @ParameterizedTest
    @CsvSource(value = {
            "4,6:MISS_FIRST,SPARE",
            "0,0:GUTTER_FIRST,GUTTER_SECOND",
            "3,3:MISS_FIRST,MISS_SECOND",
            "10:STRIKE",
            "4,6,5:MISS_FIRST,SPARE,MISS_FIRST",
            "10,4,3:STRIKE,MISS_FIRST,MISS_SECOND"
    }, delimiter = ':')
    void getLastScoreType(String shotString, String expectTypeString) {
        List<Integer> shots = Arrays.stream(shotString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        List<ScoreType> expectTypes = Arrays.stream(expectTypeString.split(",")).map(ScoreType::valueOf).collect(Collectors.toList());

        ShotScores shotScores = ShotScores.of();
        for (int i = 0; i < shots.size(); i++) {
            shotScores.add(shotScores.getNext(shots.get(i)));
            assertThat(shotScores.getLastType())
                    .isEqualTo(expectTypes.get(i));
        }
    }

}
