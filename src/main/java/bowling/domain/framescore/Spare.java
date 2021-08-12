package bowling.domain.framescore;

import bowling.domain.frame.Frame;
import bowling.domain.score.TurnScore;
import bowling.domain.score.TurnScores;
import bowling.util.Pagination;

import java.util.Collections;

public class Spare extends FrameScore {
    private final boolean showScoreValue;

    protected Spare(TurnScores turnScores, boolean showScoreValue) {
        super(turnScores);
        this.showScoreValue = showScoreValue;
    }

    public static Spare of(TurnScores turnScores) {
        return new Spare(turnScores, true);
    }

    public static Spare of(Pagination<Frame> pagination) {
        if (!isComputable(pagination)) {
            return new Spare(pagination.currentElement().turnScores(), false);
        }

        Pagination<Frame> nextPage = pagination.next();
        TurnScores currentFrameScores = pagination.currentElement().turnScores();
        TurnScores nextFrameScores = nextPage.currentElement().turnScores();

        return new Spare(
                unionWithBonusScore(currentFrameScores, nextFrameScores), true
        );
    }

    private static boolean isComputable(Pagination<Frame> pagination) {
        Pagination<Frame> nextPage = pagination.next();
        return !nextPage.isEmpty() && nextPage.currentElement().isCompletedFirstTurn();
    }

    private static TurnScores unionWithBonusScore(TurnScores currentFrameScores, TurnScores nextFrameScores) {
        TurnScore turnScore = nextFrameScores.first();

        return currentFrameScores.union(
                new TurnScores(
                        Collections.singletonList(turnScore)
                )
        );
    }

    @Override
    public boolean isShowScoreValue() {
        return showScoreValue;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
