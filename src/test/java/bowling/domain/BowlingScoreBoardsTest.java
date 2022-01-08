package bowling.domain;

import java.util.List;
import java.util.stream.Stream;

import bowling.engine.Frame;
import bowling.engine.ScoreBoard;
import bowling.engine.ScoreBoards;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static bowling.domain.frame.FinalFrameTest.ff;
import static bowling.domain.FrameSequenceTest.fs;
import static bowling.domain.frame.NormalFrameTest.fr;
import static bowling.domain.shot.ShotResult.GUTTER;
import static bowling.domain.shot.ShotResult.STRIKE;
import static bowling.domain.shot.ShotResultTest.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class BowlingScoreBoardsTest {
    public static Stream<Arguments> parseCreate() {
        return Stream.of(
                Arguments.of(List.of("n1")),
                Arguments.of(List.of("n1", "n2"))
        );
    }

    @ParameterizedTest(name = "create: {arguments}")
    @MethodSource("parseCreate")
    public void create(List<String> names) {
        assertThat(BowlingScoreBoards.of(names)).isInstanceOf(BowlingScoreBoards.class);
    }

    @ParameterizedTest(name = "create failed: {arguments}")
    @NullAndEmptySource
    public void createFailed(List<String> names) {
        assertThatIllegalArgumentException().isThrownBy(() -> BowlingScoreBoards.of(names));
    }

    @Test
    public void nextAtFirst() {
        final ScoreBoards boards = BowlingScoreBoards.of(List.of("1", "2"));
        ScoreBoard board = boards.first();
        assertThat(board.name()).isEqualTo(Player.of("1"));
        assertThat(board.current()).isEqualTo(fs(1));
    }

    @Test
    public void next() {
        final ScoreBoards boards = BowlingScoreBoards.of(List.of("1", "2"));
        ScoreBoard board = boards.first();

        board.nextShot(ONE);
        board = boards.next(board);
        assertThat(board.name()).isEqualTo(Player.of("1"));
        assertThat(board.current()).isEqualTo(fs(1));

        board.nextShot(ONE);
        board = boards.next(board);
        assertThat(board.name()).isEqualTo(Player.of("2"));
        assertThat(board.current()).isEqualTo(fs(1));
    }

    @Test
    public void nextByStrike() {
        final ScoreBoards boards = BowlingScoreBoards.of(List.of("1", "2"));
        ScoreBoard board = boards.first();

        board.nextShot(STRIKE);
        board = boards.next(board);
        assertThat(board.name()).isEqualTo(Player.of("2"));
        assertThat(board.current()).isEqualTo(fs(1));
    }

    @Test
    public void nextFrame() {
        final ScoreBoards boards = BowlingScoreBoards.of(List.of("1", "2"));
        ScoreBoard board = boards.first();

        board.nextShot(STRIKE);
        board = boards.next(board);

        board.nextShot(STRIKE);
        board = boards.next(board);

        assertThat(board.name()).isEqualTo(Player.of("1"));
        assertThat(board.current()).isEqualTo(fs(2));
    }

    @Test
    public void isEnded() {
        final List<Frame> frames = List.of(
                fr(1, GUTTER, GUTTER),
                fr(2, GUTTER, GUTTER),
                fr(3, GUTTER, GUTTER),
                fr(4, GUTTER, GUTTER),
                fr(5, GUTTER, GUTTER),
                fr(6, GUTTER, GUTTER),
                fr(7, GUTTER, GUTTER),
                fr(8, GUTTER, GUTTER),
                fr(9, GUTTER, GUTTER),
                ff(GUTTER, GUTTER)
        );
        final ScoreBoard board = BowlingScoreBoard.of("1", frames);
        final ScoreBoards boards = BowlingScoreBoards.fromBoard(List.of(board));
        assertThat(boards.isEnded()).isTrue();
    }

    @Test
    public void notEnded() {
        final ScoreBoards boards = BowlingScoreBoards.of(List.of("1", "2"));
        assertThat(boards.isEnded()).isFalse();
    }
}
