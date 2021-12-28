package bowling.domain;

import java.util.Arrays;
import java.util.stream.Stream;

import bowling.engine.Frame;
import bowling.engine.Shot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static bowling.domain.FrameSequenceTest.fs;
import static bowling.domain.NormalFrameTest.fr;
import static bowling.domain.ShotResult.FIVE;
import static bowling.domain.ShotResult.FOUR;
import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResult.SIX;
import static bowling.domain.ShotResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class FinalFrameTest {
    @Test
    public void create() {
        assertThat(NormalFrame.first(fs(10), GUTTER)).isInstanceOf(FinalFrame.class);
    }

    public static Stream<Arguments> parseThird() {
        return Stream.of(
                Arguments.of(ff(STRIKE, FIVE)),
                Arguments.of(ff(GUTTER, STRIKE)),
                Arguments.of(ff(FIVE, FIVE)),
                Arguments.of(ff(STRIKE, STRIKE))
        );
    }

    @ParameterizedTest(name = "has third chance: {arguments}")
    @MethodSource("parseThird")
    public void hasThirdChance(Frame afterSecond) {
        assertThat(afterSecond.hasThirdChance()).isTrue();
    }

    @ParameterizedTest(name = "third shot: {arguments}")
    @MethodSource("parseThird")
    public void third(Frame afterSecond) {
        assertThat(afterSecond.nextShot(FIVE)).isInstanceOf(FinalFrame.class);
    }

    public static Stream<Arguments> parseNotForThird() {
        return Stream.of(
                Arguments.of(ff(GUTTER, GUTTER)),
                Arguments.of(ff(FOUR, FIVE))
        );
    }

    @ParameterizedTest(name = "has not third chance: {arguments}")
    @MethodSource("parseNotForThird")
    public void hasNotThirdChance(Frame afterSecond) {
        assertThat(afterSecond.hasThirdChance()).isFalse();
    }

    public static Stream<Arguments> parseThirdFailed() {
        return Stream.of(
                Arguments.of(ff(GUTTER, GUTTER), GUTTER),
                Arguments.of(ff(GUTTER, GUTTER), GUTTER),
                Arguments.of(ff(STRIKE, FIVE), SIX)
        );
    }

    @ParameterizedTest(name = "third failed: {arguments}")
    @MethodSource("parseThirdFailed")
    public void thirdFailed(Frame afterSecond, Shot third) {
        assertThatIllegalStateException().isThrownBy(() -> afterSecond.nextShot(third));
    }

    @Test
    public void buildFailed() {
        assertThatIllegalStateException().isThrownBy(() -> ff(STRIKE, STRIKE, STRIKE).nextShot(STRIKE));
    }

    @Test
    public void completedWithSecondShot() {
        Frame frame = ff(GUTTER);
        assertThat(frame.completed()).isFalse();
        assertThat(frame.nextShot(GUTTER).completed()).isTrue();
        assertThat(frame.nextShot(STRIKE).completed()).isFalse();
        assertThat(frame.nextShot(STRIKE).nextShot(GUTTER).completed()).isTrue();
    }

    @Test
    public void completedWithThirdShot() {
        Frame frame = ff(STRIKE);
        assertThat(frame.completed()).isFalse();
        assertThat(frame.nextShot(STRIKE).completed()).isFalse();
        assertThat(frame.nextShot(STRIKE).nextShot(STRIKE).completed()).isTrue();
    }

    @Test
    public void isFinal() {
        assertThat(ff(GUTTER).isFinal()).isTrue();
        assertThat(fr(10).isFinal()).isTrue();
    }

    public static FinalFrame ff(Shot ... shots) {
        return FinalFrame.of(Arrays.asList(shots));
    }
}
