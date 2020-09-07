package camp.nextstep.edu.rebellion.bowling.domain.game;

import camp.nextstep.edu.rebellion.bowling.domain.frame.Frames;
import camp.nextstep.edu.rebellion.bowling.domain.score.ScoreBoard;

public class BowlingGame {
    private final Frames frames;
    private final Player player;

    private Round gameRound;

    private BowlingGame(Player player) {
        this.player = player;
        this.frames = Frames.clear();
        this.gameRound = Round.reset();
    }

    public static BowlingGame start(Player player) {
        return new BowlingGame(player);
    }

    public void record(int hits) {
        frames.markScoreOnRound(gameRound, hits);
        frames.markBonusOnPrevious(gameRound, hits);

        if (frames.meetEnd(gameRound)) {
            frames.makeBonusChance(gameRound);
            gameRound.next();
        }
    }

    public int currentRound() {
        return gameRound.getCurrent() + 1;
    }

    public boolean hasNext() {
        return gameRound.hasNext() || hasBonusChance();
    }

    private boolean hasBonusChance() {
        if (gameRound.meetLast() && frames.isFinalFrameStrikeOrSpare()) {
            frames.makeBonusFrame();
            return true;
        }

        return false;
    }

    public ScoreBoard getScoreBoard() {
        return new ScoreBoard(player, frames);
    }
}
