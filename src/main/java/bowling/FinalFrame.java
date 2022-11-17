package bowling;

import java.util.List;

public class FinalFrame implements Frame {

    public static final int FINAL_FRAME_NUMBER = 10;

    private final Score score;

    private FinalFrame(Score score) {
        this.score = score;
    }

    public static FinalFrame start(Pin falledPins) {
        Score score = new Score();
        score.add(falledPins);
        return new FinalFrame(score);
    }

    @Override
    public void bowl(Pin falledPins) {
        if (score.isStrike() || score.isSpare()) {
            score.bonusAdd(falledPins);
            return;
        }
        score.add(falledPins);
    }

    @Override
    public Frame nextFrame(Pin falledPins) {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public boolean isFinished() {
        if (score.size() == Score.BONUS_ROUND) {
            return true;
        }

        if (score.size() == Score.SECOND_ROUND && !isContinue()) {
            return true;
        }

        return false;
    }

    private boolean isContinue() {
        return score.isStrike() || score.isSpare();
    }

    @Override
    public Score getScores() {
        return score;
    }
}
