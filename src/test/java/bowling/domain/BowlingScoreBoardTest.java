package bowling.domain;

import bowling.engine.Sequence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static bowling.domain.FrameSequenceTest.fs;
import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class BowlingScoreBoardTest {
    @Test
    public void create() {
        assertThat(BowlingScoreBoard.of("n1")).isInstanceOf(BowlingScoreBoard.class);
    }

    @Test
    public void name() {
        final String name = "n1";
        assertThat(BowlingScoreBoard.of(name).name()).isEqualTo(Player.of(name));
    }

    @Test
    public void score() {
        final Sequence sequence = fs(1);
        final BowlingScoreBoard board = BowlingScoreBoard.of("n1");
        board.nextShot(STRIKE);
        assertThat(board.score(sequence)).isEqualTo(FrameScore.of(STRIKE.toInt()));
    }

    @Test
    public void currentFrame() {
        final BowlingScoreBoard board = BowlingScoreBoard.of("n1");
        assertThat(board.current()).isEqualTo(fs(1));
        board.nextShot(STRIKE);
        assertThat(board.current()).isEqualTo(fs(2));
        board.nextShot(GUTTER);
        assertThat(board.current()).isEqualTo(fs(2));
        board.nextShot(GUTTER);
        assertThat(board.current()).isEqualTo(fs(3));
    }

    @ParameterizedTest(name = "score failed by illegal argument: {arguments}")
    @NullSource
    public void scoreFailedByIllegalArguments(Sequence sequence) {
        final BowlingScoreBoard board = BowlingScoreBoard.of("n1");
        assertThatIllegalArgumentException().isThrownBy(() -> board.score(sequence));
    }

    @Test
    @DisplayName("score failed by not started frame")
    public void scoreFailedByIllegalState() {
        final Sequence sequence = fs(6);
        final BowlingScoreBoard board = BowlingScoreBoard.of("n1");
        assertThatIllegalStateException().isThrownBy(() -> board.score(sequence));

    }
}
