package bowling.domain;

import java.util.List;
import java.util.stream.Stream;

import bowling.engin.Frame;
import bowling.engin.Sequence;
import bowling.engin.Shot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.domain.FrameSequenceTest.fs;
import static bowling.domain.ShotResult.FIVE;
import static bowling.domain.ShotResult.FOUR;
import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResult.NINE;
import static bowling.domain.ShotResult.ONE;
import static bowling.domain.ShotResult.SIX;
import static bowling.domain.ShotResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class NormalFrameTest {
    @Test
    public void create() {
        assertThat(NormalFrame.first(fs(1), GUTTER)).isInstanceOf(NormalFrame.class);
    }

    public static Stream<Arguments> parseCreateFailed() {
        return Stream.of(
                Arguments.of(null, GUTTER),
                Arguments.of(fs(1), null)
        );
    }

    @ParameterizedTest
    @MethodSource("parseCreateFailed")
    public void createFailed(Sequence sequence, Shot first) {
        assertThatIllegalArgumentException().isThrownBy(() -> NormalFrame.first(sequence, first));
    }

    @Test
    public void createByStrike() {
        assertThat(NormalFrame.strike(fs(1))).isInstanceOf(NormalFrame.class);
        assertThat(NormalFrame.strike(fs(1))).isEqualTo(NormalFrame.strike(fs(1)));
    }

    @ParameterizedTest
    @NullSource
    public void createFailedByStrike(Sequence sequence) {
        assertThatIllegalArgumentException().isThrownBy(() -> NormalFrame.strike(sequence));
    }

    @Test
    public void second() {
        final Frame frame = NormalFrame.first(fs(1), GUTTER);
        assertThat(frame.second(GUTTER)).isInstanceOf(NormalFrame.class);
        assertThat(frame.second(ONE).collect()).containsExactly(GUTTER, ONE);
    }

    public static Stream<Arguments> parseSecondFailed() {
        return Stream.of(
                Arguments.of(GUTTER, null),
                Arguments.of(STRIKE, GUTTER),
                Arguments.of(NINE, SIX)
        );
    }

    @ParameterizedTest
    @MethodSource("parseSecondFailed")
    public void secondFailed(Shot first, Shot second) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> NormalFrame.first(fs(1), first).second(second));
    }

    @Test
    public void build() {
        assertThatIllegalStateException()
                .isThrownBy(() -> NormalFrame.first(fs(1), GUTTER).second(GUTTER).second(GUTTER));
    }

    @Test
    public void thirdFailed() {
        final Frame frame = NormalFrame.first(fs(1), GUTTER);
        assertThatIllegalStateException().isThrownBy(() -> frame.third(GUTTER));
        assertThatIllegalStateException().isThrownBy(() -> frame.second(GUTTER).third(GUTTER));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 9})
    public void sequence(int sequence) {
        assertThat(NormalFrame.strike(fs(sequence)).sequence()).isEqualTo(fs(sequence));
        assertThat(NormalFrame.first(fs(sequence), GUTTER).sequence()).isEqualTo(fs(sequence));
        assertThat(NormalFrame.first(fs(sequence), GUTTER).second(GUTTER).sequence()).isEqualTo(fs(sequence));
    }

    public static Stream<Arguments> parseClear() {
        return Stream.of(
                Arguments.of(List.of(GUTTER, STRIKE)),
                Arguments.of(List.of(ONE, NINE)),
                Arguments.of(List.of(SIX, FOUR))
        );
    }

    @ParameterizedTest
    @MethodSource("parseClear")
    public void isClear(List<Shot> shots) {
        final Sequence sequence = fs(1);
        assertThat(NormalFrame.of(sequence, shots).isClear()).isTrue();
    }

    public static Stream<Arguments> parseNotClear() {
        return Stream.of(
                Arguments.of(List.of(GUTTER, GUTTER)),
                Arguments.of(List.of(GUTTER, NINE)),
                Arguments.of(List.of(FIVE, FOUR))
        );
    }

    @ParameterizedTest
    @MethodSource("parseNotClear")
    public void isNotClear(List<Shot> shots) {
        final Sequence sequence = fs(1);
        assertThat(NormalFrame.of(sequence, shots).isClear()).isFalse();
    }

    @Test
    public void isClearByStrike() {
        final Sequence sequence = fs(1);
        assertThat(NormalFrame.strike(sequence).isClear()).isTrue();
    }

    @Test
    public void completed() {
        final Sequence sequence = fs(1);
        assertThat(NormalFrame.strike(sequence).completed()).isTrue();
        assertThat(NormalFrame.first(sequence, GUTTER).second(GUTTER).completed()).isTrue();
    }

    @Test
    public void notCompleted() {
        final Sequence sequence = fs(1);
        assertThat(NormalFrame.first(sequence, GUTTER).completed()).isFalse();
    }

    public static Stream<Arguments> parseScore() {
        return Stream.of(
                Arguments.of(List.of(GUTTER, GUTTER), 0),
                Arguments.of(List.of(GUTTER, NINE), 9),
                Arguments.of(List.of(FIVE, FOUR), 9),
                Arguments.of(List.of(SIX, FOUR), 10),
                Arguments.of(List.of(STRIKE), 10)
        );
    }

    @ParameterizedTest
    @MethodSource("parseScore")
    public void Score(List<Shot> shots, int expected) {
        assertThat(NormalFrame.of(fs(1), shots).score()).isEqualTo(FrameScore.of(expected));
    }

    @ParameterizedTest
    @MethodSource("parseScore")
    public void sum(List<Shot> shots, int expected) {
        assertThat(NormalFrame.sum(shots.stream())).isEqualTo(expected);
    }

}
