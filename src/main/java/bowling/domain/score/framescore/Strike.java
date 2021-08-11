package bowling.domain.score.framescore;

import bowling.domain.frame.Frame;
import bowling.domain.score.TurnScores;
import bowling.util.Pagination;

public class Strike extends FrameScore {
    private Strike(TurnScores turnScores) {
        super(turnScores);
    }

    public static Strike of(TurnScores turnScores) {
        return new Strike(turnScores);
    }

    public static Strike of(Pagination<Frame> pagination) {
        if (!isComputable(pagination)) {
            return InnerLazyClass.EMPTY;
        }

        Pagination<Frame> nextPage = pagination.next();
        TurnScores currentFrameScores = pagination.currentElement().turnScores();
        TurnScores nextFrameScores = nextPage.currentElement().turnScores();

        return new Strike(
                unionWithBonusScore(currentFrameScores, nextFrameScores)
        );
    }

    private static boolean isComputable(Pagination<Frame> pagination) {
        Pagination<Frame> nextPage = pagination.next();
        return !nextPage.isEmpty() && nextPage.currentElement().isCompleted();
    }

    private static TurnScores unionWithBonusScore(TurnScores currentFrameScores, TurnScores nextFrameScores) {
        return currentFrameScores.union(nextFrameScores);
    }

    private static class InnerLazyClass {
        private static final Strike EMPTY = new Strike(TurnScores.empty()) {
            @Override
            public boolean isEmpty() {
                return true;
            }
        };
    }
}
