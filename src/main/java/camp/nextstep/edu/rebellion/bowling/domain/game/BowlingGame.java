package camp.nextstep.edu.rebellion.bowling.domain.game;

import camp.nextstep.edu.rebellion.bowling.domain.frame.Frames;
import camp.nextstep.edu.rebellion.bowling.domain.player.Player;
import camp.nextstep.edu.rebellion.bowling.domain.score.PersonalScoreBoard;

public class BowlingGame {
    private final Frames frames;
    private final Player player;
    private final Round round;

    private Round previousRound;

    private BowlingGame(Player player) {
        this.player = player;
        this.frames = Frames.clear();
        this.round = Round.reset();
        this.previousRound = Round.reset();
    }

    public static BowlingGame start(Player player) {
        return new BowlingGame(player);
    }

    public void record(int hits) {
        frames.markScoreOnRound(round, hits);
        frames.markBonusOnPrevious(round, hits);

        keepCurrentRound();
        makeBonusAndGoNextRound();
    }

    private void makeBonusAndGoNextRound() {
        if (frames.meetEnd(round)) {
            frames.makeBonusChance(round);
            round.next();
        }
    }

    private void keepCurrentRound() {
        previousRound = Round.currentOf(round);
    }

    public boolean hasNext() {
        return round.hasNext() || hasBonusFrameChance();
    }

    public boolean meetHandOverCondition() {
        return frames.meetEnd(previousRound);
    }

    private boolean hasBonusFrameChance() {
        if (round.meetFinal() && frames.canMakeBonusFrame()) {
            frames.makeBonusFrame();
            round.addLastRound();

            return true;
        }

        return false;
    }

    public PersonalScoreBoard getScoreBoard() {
        return new PersonalScoreBoard(player, frames);
    }
}
