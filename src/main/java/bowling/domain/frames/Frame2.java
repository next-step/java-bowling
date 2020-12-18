package bowling.domain.frames;

import bowling.domain.KnockDownPins;
import bowling.domain.Score;
import bowling.domain.pitchings.LastFramePitchings2;
import bowling.domain.pitchings.NormalFramePitchings2;
import bowling.domain.pitchings.Pitchings2;
import bowling.dto.Frame2Dto;

public class Frame2 {
    private Pitchings2 pitchings;
    private final int index;
    private Frame2 nextFrame;

    public Frame2(int index) {
        this.index = index;
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

    public Frame2 getNextFrame() {
        nextFrame = new Frame2(index + 1);
        return nextFrame;
    }

    public Score getScore() {
        if (index == Frames2.MAX_FRAME_SIZE) {
            return pitchings.getScore();
        }

        if (pitchings.leftBonusApplyChance()) {
            nextFrame.applyBonusScore(pitchings);
        }

        return pitchings.getScore();
    }

    private void applyBonusScore(Pitchings2 pitchings) {
        this.pitchings.addBonusScoreTo(pitchings);

        if (pitchings.leftBonusApplyChance() && nextFrame != null) {
            nextFrame.applyBonusScore(pitchings);
        }
    }

    public Frame2Dto convertToFrameDto(Integer previousFrameTotalScore) {
        return Frame2Dto.of(pitchings, getTotalScore(previousFrameTotalScore));
    }

    private Integer getTotalScore(Integer previousFrameTotalScore) {
        return previousFrameTotalScore + getScore().getValue();
    }

    @Override
    public String toString() {
        return "Frame2{" +
                "pitchings=" + pitchings +
                ", index=" + index +
                ", nextFrame=" + nextFrame +
                '}';
    }
}
