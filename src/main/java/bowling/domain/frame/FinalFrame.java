package bowling.domain.frame;

import bowling.domain.Turn;
import bowling.domain.framescore.FinalFrameScore;
import bowling.domain.framescore.FrameScore;
import bowling.domain.framescore.Spare;
import bowling.domain.framescore.Strike;
import bowling.domain.score.TurnScore;
import bowling.domain.score.TurnScores;
import bowling.util.Pagination;

import java.util.Collections;

public class FinalFrame implements Frame {
    private final NormalFrame normalFrame;
    private Turn bonusTurn;

    public FinalFrame() {
        normalFrame = new NormalFrame();
        normalFrame.pagination = new Pagination<>(
                Frames.MAX_FRAME_NUMBER, normalFrame, Pagination.empty()
        );

        bonusTurn = Turn.empty();
    }

    @Override
    public void bowl(final TurnScore score) {
        if (currentBonusTurn()) {
            bonusTurn = new Turn(score);
            return;
        }
        normalFrame.bowl(score);
    }

    private boolean currentBonusTurn() {
        return normalFrame.isCompleted()            // 기본 턴이 완료 되어야 하고
                && isAvailableBonusTurn()     // 스트라이크나 스페어여야 하며
                && bonusTurn.isEmpty();       // 보너스 턴을 이미 진행하지 않았어야 한다.
    }

    private boolean isAvailableBonusTurn() {
        FrameScore frameScore = normalFrame.frameScore();
        return frameScore instanceof Spare || frameScore instanceof Strike;
    }

    @Override
    public TurnScores turnScores() {
        TurnScores turnScores = normalFrame.turnScores();

        if (!bonusTurn.isEmpty()) {
            turnScores = turnScores.union(
                    new TurnScores(
                            Collections.singletonList(bonusTurn.value())
                    )
            );
        }

        return turnScores;
    }

    @Override
    public FrameScore frameScore() {
        return new FinalFrameScore(normalFrame.frameScore(), turnScores(), isCompleted());
    }

    @Override
    public boolean isCompleted() {
        if (!normalFrame.isCompleted()) {
            return false;
        }
        if (isAvailableBonusTurn()) {
            return !bonusTurn.isEmpty();
        }
        return true;
    }

    @Override
    public int currentFrameNumber() {
        return Frames.MAX_FRAME_NUMBER;
    }
}
