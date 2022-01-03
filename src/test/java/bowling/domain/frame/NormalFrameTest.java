package bowling.domain.frame;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import bowling.domain.BowlingScore;
import bowling.domain.result.FrameResult;
import bowling.engine.Frame;
import bowling.engine.Result;
import bowling.engine.Sequence;
import bowling.engine.Shot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.domain.result.FrameResultTest.r;
import static bowling.domain.FrameSequenceTest.fs;
import static bowling.domain.shot.ShotResult.GUTTER;
import static bowling.domain.shot.ShotResult.STRIKE;
import static bowling.domain.shot.ShotResultTest.NINE;
import static bowling.domain.shot.ShotResultTest.ONE;
import static bowling.domain.shot.ShotResultTest.SIX;
import static bowling.domain.shot.ShotResultTest.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class NormalFrameTest {
    @Test
    public void create() {
        assertThat(BowlingFrame.ready(fs(1), GUTTER)).isInstanceOf(NormalFrame.class);
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
        assertThatIllegalArgumentException().isThrownBy(() -> BowlingFrame.ready(sequence, shot));
    }

    public static Stream<Arguments> parseCreateFailed() {
        return Stream.of(
                Arguments.of(null, FrameResult.EMPTY_RESULT),
                Arguments.of(fs(1), null)
        );
    }

    @ParameterizedTest(name = "create failed: {arguments}")
    @MethodSource("parseCreateFailed")
    public void createFailed(Sequence sequence, Result result) {
        assertThatIllegalArgumentException().isThrownBy(() -> BowlingFrame.of(sequence, result));
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
    public void isFinal() {
        assertThat(fr(1).isFinal()).isFalse();
        assertThat(fr(9).isFinal()).isFalse();
    }


    public static Frame fr(int sequence, Shot ... shots) {
        return BowlingFrame.of(fs(sequence), r(Arrays.asList(shots)));
    }

    public static Frame fr(int sequence, List<Shot> shots) {
        return BowlingFrame.of(fs(sequence), r(shots));
    }
}
