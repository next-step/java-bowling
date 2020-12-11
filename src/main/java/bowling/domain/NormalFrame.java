package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {
    public static final int NORMAL_FRAME_MAX_PITCHING_SIZE = 2;
    private final int index;
    private final List<Pitching> pitchings;
    private Frame nextFrame;

    private NormalFrame(int index) {
        this.index = index;
        pitchings = new ArrayList<>();
    }

    public static Frame getFirstFrame() {
        return new NormalFrame(1);
    }

    @Override
    public Frame getNextFrame() {
        return nextFrame;
    }

    @Override
    public Frame initNextFrame() {
        int nextFrameIndex = index + 1;
        int lastFrameIndex = Frames.MAX_FRAME_SIZE;
        if (nextFrameIndex == lastFrameIndex) {
            nextFrame = LastFrame.getInstance(lastFrameIndex);
            return nextFrame;
        }

        nextFrame = new NormalFrame(nextFrameIndex);
        return nextFrame;
    }

    @Override
    public void setKnockDownPins(KnockDownPins knockDownPins) {
        if (pitchings.isEmpty()) {
            setFirstPitching(knockDownPins);
            return;
        }

        setSecondPitching(knockDownPins);
    }

    private void setFirstPitching(KnockDownPins knockDownPins) {
        Pitching pitching = Pitching.getPitching(knockDownPins);
        pitchings.add(pitching);
    }

    private void setSecondPitching(KnockDownPins knockDownPins) {
        int lastIndex = pitchings.size() - 1;
        Pitching previousPitching = pitchings.get(lastIndex);
        Pitching pitching = Pitching.getPitching(knockDownPins, previousPitching);
        pitchings.add(pitching);
    }

    @Override
    public List<Pitching> getPitchings(){
        return new ArrayList<>(pitchings);
    }

    @Override
    public boolean isEnd() {
        if (pitchings.isEmpty()) {
            return false;
        }

        if (isFirstPitchingStrike()) {
            return true;
        }

        return pitchings.size() == NORMAL_FRAME_MAX_PITCHING_SIZE;
    }

    private boolean isFirstPitchingStrike() {
        Pitching firstPitching = pitchings.get(0);
        return firstPitching == Pitching.STRIKE;
    }

    @Override
    public boolean isLastFrame() {
        return false;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", pitchings=" + pitchings +
                '}';
    }
}
