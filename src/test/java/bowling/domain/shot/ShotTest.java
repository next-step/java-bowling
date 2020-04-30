package bowling.domain.shot;

import bowling.domain.shot.type.ScoreType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class ShotTest {
    @Test
    void of() {
        assertThatCode(() -> Shot.init(5))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void ofException(int score) {
        assertThatThrownBy(() -> Shot.init(score))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void singleScore() {
        Shot shot = Shot.init(10);
        assertThat(shot.singleScore())
                .isEqualTo(10);
    }

    @Test
    void next() {
        Shot shot = Shot.init(5);
        assertThat(shot.next(5).scoreType())
                .isEqualTo(ScoreType.SPARE);
    }

    @Test
    void nextException() {
        Shot shot = Shot.init(5);
        assertThatThrownBy(() -> shot.next(6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isClear() {
        Shot shot = Shot.init(10);
        assertThat(shot.isClear())
                .isTrue();

        shot = Shot.init(4);
        assertThat(shot.next(6).isClear())
                .isTrue();
    }
}
