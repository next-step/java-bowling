package bowling.domain;

import java.util.stream.Stream;

import bowling.engin.Frame;
import bowling.engin.Shot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import static bowling.domain.FrameSequenceTest.fs;
import static bowling.domain.ShotResult.FIVE;
import static bowling.domain.ShotResult.FOUR;
import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResult.SIX;
import static bowling.domain.ShotResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class FinalFrameTest {
    @Test
    public void create() {
        assertThat(FinalFrame.first(GUTTER)).isInstanceOf(FinalFrame.class);
        assertThat(FinalFrame.strike()).isInstanceOf(FinalFrame.class);
        assertThat(NormalFrame.first(fs(10), GUTTER)).isInstanceOf(FinalFrame.class);
    }

    @ParameterizedTest
    @NullSource
    public void createFailed(Shot first) {
        assertThatIllegalArgumentException().isThrownBy(() -> FinalFrame.first(first));
    }

    public static Stream<Arguments> parseThird() {
        return Stream.of(
                Arguments.of(FinalFrame.strike().second(FIVE)),
                Arguments.of(FinalFrame.first(GUTTER).second(STRIKE)),
                Arguments.of(FinalFrame.first(FIVE).second(FIVE)),
                Arguments.of(FinalFrame.strike().second(STRIKE))
        );
    }

    @ParameterizedTest
    @MethodSource("parseThird")
    public void third(Frame afterSecond) {
        assertThat(afterSecond.third(FIVE)).isInstanceOf(FinalFrame.class);
    }

    public static Stream<Arguments> parseThirdFailed() {
        return Stream.of(
                Arguments.of(FinalFrame.first(GUTTER).second(GUTTER), GUTTER),
                Arguments.of(FinalFrame.first(FIVE).second(FOUR), GUTTER),
                Arguments.of(FinalFrame.strike().second(FIVE), SIX)
        );
    }

    @ParameterizedTest
    @MethodSource("parseThirdFailed")
    public void thirdFailed(Frame afterSecond, Shot third) {
        assertThatIllegalStateException().isThrownBy(() -> afterSecond.third(third));
    }

    @Test
    public void buildFailed() {
        assertThatIllegalStateException().isThrownBy(() -> FinalFrame.first(STRIKE).third(STRIKE));
        assertThatIllegalStateException().isThrownBy(() -> FinalFrame.first(STRIKE).third(STRIKE).second(STRIKE));
        assertThatIllegalStateException().isThrownBy(() -> FinalFrame.first(STRIKE).third(STRIKE).third(STRIKE));

    }

    @Test
    public void completedWithSecondShot() {
        Frame frame = FinalFrame.first(GUTTER);
        assertThat(frame.completed()).isFalse();
        assertThat(frame.second(GUTTER).completed()).isTrue();
        assertThat(frame.second(STRIKE).completed()).isFalse();
        assertThat(frame.second(STRIKE).third(GUTTER).completed()).isTrue();
    }

    @Test
    public void completedWithThirdShot() {
        Frame frame = FinalFrame.strike();
        assertThat(frame.completed()).isFalse();
        assertThat(frame.second(STRIKE).completed()).isFalse();
        assertThat(frame.second(STRIKE).third(STRIKE).completed()).isTrue();
    }
}
