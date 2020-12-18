package bowling.domain;

import bowling.domain.pitchings.LastFramePitchings;
import bowling.domain.pitchings.NormalFramePitchings;
import bowling.domain.pitchings.Pitchings;
import bowling.dto.FrameDto;

public class Frame {
    private final Pitchings pitchings;
    private Frame nextFrame;

    public Frame(int frameNo) {
        pitchings = initPitchings(frameNo);
    }

    private Pitchings initPitchings(int frameNo) {
        if (frameNo == Frames.MAX_FRAME_SIZE) {
            return LastFramePitchings.getInstance();
        }

        return NormalFramePitchings.getInstance();
    }

    public static Frame getFirstFrame() {
        return new Frame(1);
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        pitchings.addPitching(knockDownPins);
    }

    public boolean isEnd() {
        return pitchings.isEnd();
    }

    public Frame getNextFrame(int frameNo) {
        nextFrame = new Frame(frameNo);
        return nextFrame;
    }

    public Score getScore() {
        Score score = pitchings.getScore();

        if (pitchings instanceof LastFramePitchings) {
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

    public FrameDto convertToFrameDto(Integer previousFrameTotalScore) {
        return FrameDto.of(pitchings, getTotalScore(previousFrameTotalScore));
    }

    Integer getTotalScore(Integer previousFrameTotalScore) {
        Score score = getScore();
        if (canNotCalculateTotalScore(previousFrameTotalScore, score)) {
            return null;
        }
        return previousFrameTotalScore + score.getValue();
    }

    private boolean canNotCalculateTotalScore(Integer previousFrameTotalScore, Score score) {
        return previousFrameTotalScore == null || score == null || !isEnd();
    }

    @Override
    public String toString() {
        return "Frame2{" +
                "pitchings=" + pitchings +
                ", nextFrame=" + nextFrame +
                '}';
    }
}
