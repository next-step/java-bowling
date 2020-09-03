package camp.nextstep.edu.rebellion.bowling.domain;

import camp.nextstep.edu.rebellion.bowling.domain.frame.Frame;
import camp.nextstep.edu.rebellion.bowling.domain.frame.Frames;

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
        Frame current = frames.findByRound(gameRound);
        current.markScore(hits);

        if (current.meetEnd()) {
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
        if (gameRound.meetLast() && frames.isFinalFrameStrike()) {
            frames.makeBonusFrame();
            return true;
        }

        return false;
    }

    public ScoreBoard getScoreBoard() {
        return new ScoreBoard(player, frames);
    }
}
