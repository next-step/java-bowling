package bowling.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import bowling.engine.Bonus;
import bowling.engine.Result;
import bowling.engine.Shot;
import bowling.engine.Shots;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResult.STRIKE;
import static bowling.domain.ShotResultTest.ONE;
import static bowling.domain.ShotResultTest.THREE;
import static bowling.domain.ShotResultTest.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameResultTest {
    @Test
    public void create() {
        assertThat(FrameResult.of(GUTTER)).isInstanceOf(FrameResult.class);
    }

    public static Stream<Arguments> parseCreateFailed() {
        return Stream.of(
                Arguments.of(null, FrameBonus.NONE),
                Arguments.of(FrameShots.emptyShot(), null)
        );
    }

    @ParameterizedTest(name = "create failed: {arguments}")
    @MethodSource("parseCreateFailed")
    public void createFailed(Shots shots, Bonus bonus) {
        assertThatIllegalArgumentException().isThrownBy(() -> FrameResult.of(shots, bonus));
    }

    @Test
    public void nextShot() {
        final Result result = r(GUTTER);
        assertThat(result.next(GUTTER)).isInstanceOf(FrameResult.class);
        assertThat(result.next(ONE).score()).isEqualTo(BowlingScore.of(1));
    }

    @Test
    public void nextShotWithBonus() {
        final Result result = r(STRIKE);
        result.next(TWO);
        assertThat(result.score()).isEqualTo(BowlingScore.of(12));
        result.next(ONE);
        assertThat(result.score()).isEqualTo(BowlingScore.of(13));
    }

    @Test
    public void completed() {
        assertThat(r(STRIKE).completed()).isTrue();
        assertThat(r(GUTTER, GUTTER).completed()).isTrue();
    }

    @Test
    public void notCompleted() {
        assertThat(r(GUTTER).completed()).isFalse();
    }


    @Test
    public void completedBonusByNotClear() {
        Result result = r(GUTTER);
        assertThat(result.completedBonus()).isTrue();
        assertThat(result.next(GUTTER).completedBonus()).isTrue();
    }

    @Test
    public void completedBonusBySpare() {
        Result result = r( GUTTER).next(STRIKE);
        assertThat(result.completedBonus()).isFalse();
        Result next = result.next(GUTTER);
        assertThat(next.completedBonus()).isTrue();
    }

    @Test
    public void completedBonusByStrike() {
        Result result = r( STRIKE);
        assertThat(result.completedBonus()).isFalse();
        result.next(GUTTER);
        assertThat(result.completedBonus()).isFalse();
        result.next(GUTTER);
        assertThat(result.completedBonus()).isTrue();
    }

    @Test
    public void completedBonusByTurkey() {
        Result result = r(STRIKE);
        assertThat(result.completedBonus()).isFalse();
        Result next = result.next(STRIKE);
        assertThat(result.completedBonus()).isFalse();
        next.next(STRIKE);
        assertThat(result.completedBonus()).isTrue();
    }


    @Test
    public void size() {
        assertThat(r(TWO, THREE).size()).isEqualTo(2);
    }

    @Test
    public void stream() {
        assertThat(r(TWO, THREE).stream()).containsExactly(TWO, THREE);
    }

    public static Result r(Shot... shots) {
        return FrameResult.of(Arrays.asList(shots), Collections.emptyList());
    }

    public static Result r(List<Shot> shots) {
        return FrameResult.of(shots, Collections.emptyList());
    }
}
