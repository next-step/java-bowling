package bowling.domain.shot.type;

import bowling.domain.shot.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GutterCheckerTest {

    @Test
    void availableFirstShots() {
        assertThat(new GutterChecker(true).availableFirstShots(Score.of(0)))
                .isTrue();
        assertThat(new GutterChecker(false).availableFirstShots(Score.of(0)))
                .isFalse();
        assertThat(new GutterChecker(true).availableFirstShots(Score.of(1)))
                .isFalse();
    }

    @Test
    void availableSecondShots() {
        assertThat(new GutterChecker(false).availableSecondShots(Score.of(1), Score.of(0)))
                .isTrue();
        assertThat(new GutterChecker(true).availableSecondShots(Score.of(10), Score.of(0)))
                .isFalse();
        assertThat(new GutterChecker(false).availableSecondShots(Score.of(1), Score.of(1)))
                .isFalse();
    }
}
