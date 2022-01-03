package bowling.domain;

import java.util.List;

import bowling.engine.Frame;
import org.junit.jupiter.api.Test;

import static bowling.domain.frame.FinalFrameTest.ff;
import static bowling.domain.FrameSequenceTest.fs;
import static bowling.domain.frame.NormalFrameTest.fr;
import static bowling.domain.shot.ShotResult.GUTTER;
import static bowling.domain.shot.ShotResult.STRIKE;
import static bowling.domain.shot.ShotResultTest.NINE;
import static bowling.domain.shot.ShotResultTest.ONE;
import static org.assertj.core.api.Assertions.assertThat;

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
    public void empty() {
        final BowlingScoreBoard board = BowlingScoreBoard.of("n1");
        assertThat(board.empty(0)).isTrue();
        assertThat(board.empty(1)).isTrue();
        board.nextShot(ONE);
        assertThat(board.empty(0)).isFalse();
        assertThat(board.empty(1)).isTrue();
    }

    @Test
    public void remainBonus() {
        final int index = 0;
        final BowlingScoreBoard board = BowlingScoreBoard.of("n1");
        board.nextShot(ONE);
        assertThat(board.remainBonuses(index)).isFalse();
        board.nextShot(NINE);
        assertThat(board.remainBonuses(index)).isTrue();
        board.nextShot(ONE);
        assertThat(board.remainBonuses(index)).isFalse();
    }

    @Test
    public void remainBonusByStrike() {
        final int index = 0;
        final BowlingScoreBoard board = BowlingScoreBoard.of("n1");
        board.nextShot(STRIKE);
        assertThat(board.remainBonuses(index)).isTrue();
        board.nextShot(NINE);
        assertThat(board.remainBonuses(index)).isTrue();
        board.nextShot(ONE);
        assertThat(board.remainBonuses(index)).isFalse();
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

    @Test
    public void isEnd() {
        final List<Frame> oneShotRemain = List.of(fr(1), fr(2), fr(3),
                fr(4), fr(5), fr(6), fr(7),
                fr(8), fr(9), ff(GUTTER));
         final BowlingScoreBoard board = BowlingScoreBoard.of("n1", oneShotRemain);
         assertThat(board.isEnded()).isFalse();
         board.nextShot(GUTTER);
         assertThat(board.isEnded()).isTrue();
    }
}
