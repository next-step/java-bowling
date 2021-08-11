package bowling.domain.score.framescore;

import bowling.domain.frame.Frame;
import bowling.domain.score.TurnScore;
import bowling.domain.score.TurnScores;
import bowling.util.Pagination;

import java.util.Collections;

public class Spare extends FrameScore {
    private Spare(TurnScores turnScores) {
        super(turnScores);
    }

    public static Spare of(TurnScores turnScores) {
        return new Spare(turnScores);
    }

    public static Spare of(Pagination<Frame> pagination) {
        if (!isComputable(pagination)) {
            return InnerLazyClass.EMPTY;
        }

        Pagination<Frame> nextPage = pagination.next();
        TurnScores currentFrameScores = pagination.currentElement().turnScores();
        TurnScores nextFrameScores = nextPage.currentElement().turnScores();

        return new Spare(
                unionWithBonusScore(currentFrameScores, nextFrameScores)
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

    private static class InnerLazyClass {
        private static final Spare EMPTY = new Spare(TurnScores.empty()) {
            @Override
            public boolean isEmpty() {
                return true;
            }
        };
    }
}
