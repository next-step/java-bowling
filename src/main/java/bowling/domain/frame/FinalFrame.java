package bowling.domain.frame;

import bowling.domain.score.framescore.BonusSpare;
import bowling.domain.score.framescore.FrameScore;
import bowling.domain.score.framescore.Spare;
import bowling.domain.score.framescore.Strike;
import bowling.domain.score.TurnScore;
import bowling.domain.Turn;
import bowling.domain.score.TurnScores;

import java.util.List;
import java.util.stream.Collectors;

public final class FinalFrame extends Frame {
    private Turn bonusTurn;

    public FinalFrame() {
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

    @Override
    public FrameScore frameScore() {
        FrameScore frameScore = super.frameScore();

        if (!isCompleted()) {
            return frameScore;
        }

        if (frameScore instanceof Spare) {
            return new BonusSpare(turnScores());
        }

        return new FrameScore(
                turnScores()
        );
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
        FrameScore frameScore = super.frameScore();
        return frameScore instanceof Spare || frameScore instanceof Strike;
    }

    @Override
    public TurnScores turnScores() {
        List<TurnScore> turnScores = turns.stream()
                .map(Turn::value)
                .collect(Collectors.toList());
        if (!bonusTurn.isEmpty()) {
            turnScores.add(bonusTurn.value());
        }

        return new TurnScores(turnScores);
    }
}
