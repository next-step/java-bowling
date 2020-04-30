package bowling.domain;

import bowling.domain.shot.type.ScoreType;
import bowling.domain.shot.Shot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ShotsTest {
    private Shots shots;

    @BeforeEach
    void setting() {
        shots = Shots.of(new ArrayList<>());
        shots.add(4);
    }

    @Test
    void add() {
        shots.add(6);
        assertThat(shots.hasSize(2))
                .isTrue();
    }

    @Test
    void isSize() {
        assertThat(shots.hasSize(1))
                .isTrue();

        assertThat(shots.hasSize(2))
                .isFalse();
    }

    @Test
    void isClear() {
        assertThat(shots.hasClear())
                .isFalse();

        shots.add(6);

        assertThat(shots.hasClear())
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

        Shots shotScores = Shots.of();
        for (int i = 0; i < shots.size(); i++) {
            shotScores.add(shots.get(i));
            assertThat(shotScores.getLastType())
                    .isEqualTo(expectTypes.get(i));
        }
    }

}
