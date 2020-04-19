package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTypeTest {
    @Test
    void isIn() {
        ScoreType scoreType = ScoreType.STRIKE;
        assertThat(scoreType.isIn(ScoreType.STRIKE, ScoreType.SPARE))
                .isTrue();

        assertThat(scoreType.isIn(ScoreType.SPARE, ScoreType.MISS, ScoreType.GUTTER))
                .isFalse();
    }

}
