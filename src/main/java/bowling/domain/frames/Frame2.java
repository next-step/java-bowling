package bowling.domain.frames;

import bowling.domain.KnockDownPins;
import bowling.domain.Score;
import bowling.domain.pitchings.LastFramePitchings2;
import bowling.domain.pitchings.NormalFramePitchings2;
import bowling.domain.pitchings.Pitchings2;
import bowling.dto.Frame2Dto;

public class Frame2 {
    private final Pitchings2 pitchings;
    private Frame2 nextFrame;

    public Frame2(int index) {
        pitchings = initPitchings(index);
    }

    private Pitchings2 initPitchings(int index) {
        if (index == Frames2.MAX_FRAME_SIZE) {
            return LastFramePitchings2.getInstance();
        }

        return NormalFramePitchings2.getInstance();
    }

    public static Frame2 getFirstFrame() {
        return new Frame2(1);
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        pitchings.addPitching(knockDownPins);
    }

    public boolean isEnd() {
        return pitchings.isEnd();
    }

    public Frame2 getNextFrame(int index) {
        nextFrame = new Frame2(index);
        return nextFrame;
    }

    public Score getScore() {
        Score score = pitchings.getScore();

        if (pitchings instanceof LastFramePitchings2) {
            return score;
        }

        if (canApplyBonusScore(score)) {
            return nextFrame.applyBonusScoreTo(score);
        }
        return score;
    }

    private boolean canApplyBonusScore(Score score) {
        return score.leftBonusApplyChance() && nextFrame != null;
    }

    private Score applyBonusScoreTo(Score score) {
        Score bonusAppliedScore = pitchings.applyBonusScoreTo(score);
        if (canApplyBonusScore(bonusAppliedScore)) {
            bonusAppliedScore = nextFrame.applyBonusScoreTo(bonusAppliedScore);
        }
        return bonusAppliedScore;
    }

    public Frame2Dto convertToFrameDto(Integer previousFrameTotalScore) {
        return Frame2Dto.of(pitchings, getTotalScore(previousFrameTotalScore));
    }

    Integer getTotalScore(Integer previousFrameTotalScore) {
        if (previousFrameTotalScore == null || getScore() == null) {
            return null;
        }
        if (!isEnd()) {
            return null;
        }
        return previousFrameTotalScore + getScore().getValue();
    }

    @Override
    public String toString() {
        return "Frame2{" +
                "pitchings=" + pitchings +
                ", nextFrame=" + nextFrame +
                '}';
    }
}
