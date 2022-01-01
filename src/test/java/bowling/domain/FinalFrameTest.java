package bowling.domain;

import java.util.Arrays;
import java.util.stream.Stream;

import bowling.engine.Bonus;
import bowling.engine.Frame;
import bowling.engine.Shot;
import bowling.engine.Shots;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import static bowling.domain.FrameSequenceTest.fs;
import static bowling.domain.NormalFrameTest.fr;
import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResult.STRIKE;
import static bowling.domain.ShotResultTest.ONE;
import static bowling.domain.ShotResultTest.FOUR;
import static bowling.domain.ShotResultTest.FIVE;
import static bowling.domain.ShotResultTest.SIX;
import static bowling.domain.ShotResultTest.SEVEN;
import static bowling.domain.ShotResultTest.EIGHT;
import static bowling.domain.ShotResultTest.NINE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class FinalFrameTest {
    @Test
    public void create() {
        assertThat(NormalFrame.ready(fs(10), GUTTER)).isInstanceOf(FinalFrame.class);
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
        assertThatIllegalArgumentException().isThrownBy(() ->FinalFrame.of(shots, bonus));
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

    public static Stream<Arguments> parseNextShotFailed() {
        return Stream.of(
                Arguments.of(SIX)
        );
    }

    @ParameterizedTest(name = "next shot failed: {arguments}")
    @NullSource
    @MethodSource("parseNextShotFailed")
    public void nextShotFailed(Shot shot) {
        assertThatIllegalArgumentException().isThrownBy(() -> ff(STRIKE, FIVE).nextShot(shot));
    }

    public static Stream<Arguments> parseThirdFailed() {
        return Stream.of(
                Arguments.of(ff(GUTTER, GUTTER), GUTTER),
                Arguments.of(ff(EIGHT, ONE), SEVEN)
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

    public static Stream<Arguments> parseCompletedWithSecondShot() {
        return Stream.of(
                Arguments.of(GUTTER, GUTTER),
                Arguments.of(EIGHT, ONE),
                Arguments.of(FOUR, FIVE)
        );
    }

    @ParameterizedTest(name = "completed with second shot: {arguments}")
    @MethodSource("parseCompletedWithSecondShot")
    public void completedWithSecondShot(Shot first, Shot second) {
        Frame frame = ff(first);
        assertThat(frame.completed()).isFalse();
        assertThat(frame.nextShot(second).completed()).isTrue();
        assertThatIllegalStateException().isThrownBy(() -> frame.nextShot(second).nextShot(GUTTER));
    }

    public static Stream<Arguments> parseCompletedWithThirdShot() {
        return Stream.of(
                Arguments.of(STRIKE, STRIKE, STRIKE),
                Arguments.of(STRIKE, GUTTER, GUTTER),
                Arguments.of(NINE, ONE, SEVEN),
                Arguments.of(GUTTER, STRIKE, SIX)
        );
    }

    @ParameterizedTest(name = "completed with third shot: {arguments}")
    @MethodSource("parseCompletedWithThirdShot")
    public void completedWithThirdShot(Shot first, Shot second, Shot third) {
        Frame frame = ff(first);
        assertThat(frame.completed()).isFalse();
        Frame afterSecond = frame.nextShot(second);
        assertThat(afterSecond.completed()).isFalse();
        Frame afterThird = afterSecond.nextShot(third);
        assertThat(afterSecond.completed()).isTrue();
        assertThatIllegalStateException().isThrownBy(() -> afterThird.nextShot(GUTTER));
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
