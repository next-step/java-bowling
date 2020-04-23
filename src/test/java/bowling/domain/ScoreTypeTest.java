package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTypeTest {
    @Test
    void isIn() {
        ScoreType scoreType = ScoreType.STRIKE;
        assertThat(scoreType.finish())
                .isTrue();

        assertThat(Arrays.asList(ScoreType.SPARE, ScoreType.MISS_FIRST, ScoreType.GUTTER_FIRST).contains(scoreType))
                .isFalse();
    }

}
