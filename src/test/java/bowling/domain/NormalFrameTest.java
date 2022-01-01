package bowling.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import bowling.engine.BonusScores;
import bowling.engine.Frame;
import bowling.engine.Sequence;
import bowling.engine.Shot;
import bowling.engine.Shots;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.domain.FrameSequenceTest.fs;
import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResult.STRIKE;
import static bowling.domain.ShotResultTest.NINE;
import static bowling.domain.ShotResultTest.ONE;
import static bowling.domain.ShotResultTest.SIX;
import static bowling.domain.ShotResultTest.THREE;
import static bowling.domain.ShotResultTest.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class NormalFrameTest {
    @Test
    public void create() {
        assertThat(NormalFrame.ready(fs(1), GUTTER)).isInstanceOf(NormalFrame.class);
    }

    public static Stream<Arguments> parseReadyFailed() {
        return Stream.of(
                Arguments.of(null, GUTTER),
                Arguments.of(fs(1), null)
        );
    }

    @ParameterizedTest(name = "create failed: {arguments}")
    @MethodSource("parseReadyFailed")
    public void readyFailed(Sequence sequence, Shot shot) {
        assertThatIllegalArgumentException().isThrownBy(() -> NormalFrame.ready(sequence, shot));
    }


    public static Stream<Arguments> parseCreateFailed() {
        return Stream.of(
                Arguments.of(null, FrameShots.of(List.of(GUTTER)), Collections.emptyList()),
                Arguments.of(fs(1), null, Collections.emptyList()),
                Arguments.of(fs(1), FrameShots.of(List.of(GUTTER)), null)
        );
    }

    @ParameterizedTest(name = "create failed: {arguments}")
    @MethodSource("parseCreateFailed")
    public void createFailed(Sequence sequence, Shots shots, List<BonusScores> bonusScores) {
        assertThatIllegalArgumentException().isThrownBy(() -> NormalFrame.of(sequence, shots, bonusScores));
    }

    @Test
    public void nextShot() {
        final Frame frame = fr(1, GUTTER);
        assertThat(frame.nextShot(GUTTER)).isInstanceOf(NormalFrame.class);
        assertThat(frame.nextShot(ONE).score()).isEqualTo(BowlingScore.of(1));
    }

    @Test
    public void nextShotWithBonus() {
        final Frame frame = fr(1, STRIKE);
        frame.nextShot(TWO);
        assertThat(frame.score()).isEqualTo(BowlingScore.of(12));
        frame.nextShot(ONE);
        assertThat(frame.score()).isEqualTo(BowlingScore.of(13));
    }

    public static Stream<Arguments> parseNextShotFailedIllegalArguments() {
        return Stream.of(
                Arguments.of(GUTTER, null),
                Arguments.of(NINE, SIX)
        );
    }

    @ParameterizedTest(name = "next shot failed by illegal arguments: {arguments}")
    @MethodSource("parseNextShotFailedIllegalArguments")
    public void secondFailedByIllegalArguments(Shot first, Shot second) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> fr(1, first).nextShot(second));
    }


    @Test
    public void nextFrame() {
        assertThat(fr(1, STRIKE).nextShot(GUTTER).sequence()).isEqualTo(fs(2));
        assertThat(fr(1, GUTTER, GUTTER).nextShot(GUTTER).sequence()).isEqualTo(fs(2));
    }

    @ParameterizedTest(name = "sequence: {arguments}")
    @ValueSource(ints = {1, 9})
    public void sequence(int sequence) {
        assertThat(fr(sequence, STRIKE).sequence()).isEqualTo(fs(sequence));
        assertThat(fr(sequence, GUTTER).sequence()).isEqualTo(fs(sequence));
        assertThat(fr(sequence, GUTTER).nextShot(GUTTER).sequence()).isEqualTo(fs(sequence));
    }

    @Test
    public void completed() {
        assertThat(fr(1, STRIKE).completed()).isTrue();
        assertThat(fr(1, GUTTER, GUTTER).completed()).isTrue();
    }

    @Test
    public void notCompleted() {
        assertThat(fr(1, GUTTER).completed()).isFalse();
    }

    @Test
    public void hasThirdChance() {
        assertThat(fr(1).hasThirdChance()).isFalse();
    }

    @Test
    public void isFinal() {
        assertThat(fr(1).isFinal()).isFalse();
        assertThat(fr(9).isFinal()).isFalse();
    }

    @Test
    public void completedBonusByNotClear() {
        Frame frame = fr(1, GUTTER);
        assertThat(frame.complectedBonus()).isTrue();
        assertThat(frame.nextShot(GUTTER).complectedBonus()).isTrue();
    }

    @Test
    public void completedBonusBySpare() {
        Frame frame = fr(1, GUTTER).nextShot(STRIKE);
        assertThat(frame.complectedBonus()).isFalse();
        Frame next = frame.nextShot(GUTTER);
        assertThat(next.complectedBonus()).isTrue();
    }

    @Test
    public void completedBonusByStrike() {
        Frame frame = fr(1, STRIKE);
        assertThat(frame.complectedBonus()).isFalse();
        frame.nextShot(GUTTER);
        assertThat(frame.complectedBonus()).isFalse();
        frame.nextShot(GUTTER);
        assertThat(frame.complectedBonus()).isTrue();
    }

    @Test
    public void completedBonusByTurkey() {
        Frame frame = fr(1, STRIKE);
        assertThat(frame.complectedBonus()).isFalse();
        Frame next = frame.nextShot(STRIKE);
        assertThat(frame.complectedBonus()).isFalse();
        next.nextShot(STRIKE);
        assertThat(frame.complectedBonus()).isTrue();
    }

    @Test
    public void stream() {
        assertThat(fr(1, TWO, THREE).stream()).containsExactly(TWO, THREE);
    }

    public static Frame fr(int sequence, Shot ... shots) {
        return NormalFrame.of(fs(sequence), Arrays.asList(shots), Collections.emptyList());
    }

    public static Frame fr(int sequence, List<Shot> shots) {
        return NormalFrame.of(fs(sequence), shots, Collections.emptyList());
    }
}
