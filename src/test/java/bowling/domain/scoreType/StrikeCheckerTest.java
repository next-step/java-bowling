package bowling.domain.scoreType;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeCheckerTest {

    @Test
    void availableFirstShots() {
        assertThat(new StrikeChecker().availableFirstShots(Score.of(10)))
                .isTrue();
        assertThat(new StrikeChecker().availableFirstShots(Score.of(9)))
                .isFalse();
    }

    @Test
    void availableSecondShots() {
        assertThat(new StrikeChecker().availableSecondShots(Score.of(0), Score.of(10)))
                .isFalse();
    }
}
