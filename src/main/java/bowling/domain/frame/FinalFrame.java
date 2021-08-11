package bowling.domain.frame;

import bowling.domain.score.TurnScore;
import bowling.domain.Turn;

public final class FinalFrame extends Frame {
    private Turn bonusTurn;

    public FinalFrame() {
        super(Frames.MAX_FRAME_NUMBER);
        bonusTurn = Turn.empty();
    }

    @Override
    public void bowl(final TurnScore score) {
        if (currentBonusTurn()) {
            bonusTurn = new Turn(score);
            return;
        }
        super.bowl(score);
    }

    private boolean currentBonusTurn() {
        return super.isCompleted()            // 기본 턴이 완료 되어야 하고
                && isAvailableBonusTurn()     // 스트라이크나 스페어여야 하며
                && bonusTurn.isEmpty();       // 보너스 턴을 이미 진행하지 않았어야 한다.
    }

    @Override
    public boolean isCompleted() {
        if (!super.isCompleted()) {
            return false;
        }
        if (isAvailableBonusTurn()) {
            return !bonusTurn.isEmpty();
        }
        return true;
    }

    private boolean isAvailableBonusTurn() {
        FrameScoreGrade frameScore = frameScoreGrade();
        return frameScore == FrameScoreGrade.STRIKE || frameScore == FrameScoreGrade.SPARE;
    }

    public TurnScore bonusScore() {
        return bonusTurn.value();
    }
}
