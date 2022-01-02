package bowling.domain;

import java.util.stream.Stream;

import bowling.engine.Frame;
import bowling.engine.Result;
import bowling.engine.Shot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import static bowling.domain.FinalFrameResultTest.rf;
import static bowling.domain.FrameSequenceTest.fs;
import static bowling.domain.NormalFrameTest.fr;
import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResult.STRIKE;
import static bowling.domain.ShotResultTest.EIGHT;
import static bowling.domain.ShotResultTest.FIVE;
import static bowling.domain.ShotResultTest.ONE;
import static bowling.domain.ShotResultTest.SEVEN;
import static bowling.domain.ShotResultTest.SIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class FinalFrameTest {
    @Test
    public void create() {
        assertThat(NormalFrame.ready(fs(10), GUTTER)).isInstanceOf(FinalFrame.class);
    }

    @ParameterizedTest(name = "create failed: {arguments}")
    @NullSource
    public void createFailed(Result result) {
        assertThatIllegalArgumentException().isThrownBy(() ->FinalFrame.of(result));
    }

    public static Stream<Arguments> parseThird() {
        return Stream.of(
                Arguments.of(ff(STRIKE, FIVE)),
                Arguments.of(ff(GUTTER, STRIKE)),
                Arguments.of(ff(FIVE, FIVE)),
                Arguments.of(ff(STRIKE, STRIKE))
        );
    }

    @ParameterizedTest(name = "third shot: {arguments}")
    @MethodSource("parseThird")
    public void third(Frame afterSecond) {
        assertThat(afterSecond.nextShot(FIVE)).isInstanceOf(FinalFrame.class);
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

    @Test
    public void isFinal() {
        assertThat(ff(GUTTER).isFinal()).isTrue();
        assertThat(fr(10).isFinal()).isTrue();
    }

    public static Frame ff(Shot ... shots) {
        return FinalFrame.of(rf(shots));
    }
}
