package bowling.domain;

import java.util.Arrays;
import java.util.stream.Stream;

import bowling.engine.Frame;
import bowling.engine.Shot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.domain.FrameSequenceTest.fs;
import static bowling.domain.NormalFrameTest.fr;
import static bowling.domain.ShotResult.EIGHT;
import static bowling.domain.ShotResult.FIVE;
import static bowling.domain.ShotResult.FOUR;
import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResult.NINE;
import static bowling.domain.ShotResult.ONE;
import static bowling.domain.ShotResult.SEVEN;
import static bowling.domain.ShotResult.SIX;
import static bowling.domain.ShotResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
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


    @Test
    public void nextShotFailed() {
        assertThatIllegalArgumentException().isThrownBy(() -> ff(STRIKE, FIVE).nextShot(SIX));
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
        assertThat(frame.nextShot(second).completed()).isFalse();
        assertThat(frame.nextShot(second).nextShot(third).completed()).isTrue();
        assertThatIllegalStateException().isThrownBy(() -> frame.nextShot(second).nextShot(third).nextShot(GUTTER));
    }

    @Test
    public void isFinal() {
        assertThat(ff(GUTTER).isFinal()).isTrue();
        assertThat(fr(10).isFinal()).isTrue();
    }

    @ParameterizedTest(name = "is spare challenge")
    @ValueSource(ints = {1, 5, 9})
    public void isSpareChallenge(int score) {
        assertThat(ff(STRIKE).nextShot(ShotResult.of(score)).isSpareChallenge()).isTrue();
    }

    @Test
    public void isNotSpareChallenge() {
        assertThat(ff(STRIKE).isSpareChallenge()).isFalse();
    }

    public static Stream<Arguments> parseNotSpareChallenge() {
        return Stream.of(
                Arguments.of(NINE, ONE),
                Arguments.of(GUTTER, STRIKE)
        );
    }

    @ParameterizedTest(name = "is not spare challenge: {arguments}")
    @MethodSource("parseNotSpareChallenge")
    public void isNotSpareChallengeBySpare(Shot first, Shot second) {
        assertThat(ff(first).nextShot(second).isSpareChallenge()).isFalse();
    }

    public static FinalFrame ff(Shot ... shots) {
        return FinalFrame.of(Arrays.asList(shots));
    }
}
