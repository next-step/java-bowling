package bowling.domain.framescore;

import bowling.domain.frame.Frame;
import bowling.domain.score.TurnScores;
import bowling.util.Pagination;

public class Strike extends FrameScore {
    private final boolean showScoreValue;

    private Strike(TurnScores turnScores, boolean showScoreValue) {
        super(turnScores);
        this.showScoreValue = showScoreValue;
    }

    public static Strike of(TurnScores turnScores) {
        return new Strike(turnScores, true);
    }

    public static Strike of(Pagination<Frame> pagination) {
        if (!isComputable(pagination)) {
            return new Strike(pagination.currentElement().turnScores(), false);
        }

        Pagination<Frame> nextPage = pagination.next();
        TurnScores currentFrameScores = pagination.currentElement().turnScores();
        TurnScores nextFrameScores = nextPage.currentElement().turnScores();

        return new Strike(
                unionWithBonusScore(currentFrameScores, nextFrameScores), true
        );
    }

    private static boolean isComputable(Pagination<Frame> pagination) {
        Pagination<Frame> nextPage = pagination.next();
        return !nextPage.isEmpty() && nextPage.currentElement().isCompleted();
    }

    private static TurnScores unionWithBonusScore(TurnScores currentFrameScores, TurnScores nextFrameScores) {
        return currentFrameScores.union(nextFrameScores);
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
