package bowling.frame;

import bowling.pitching.Pitching;
import bowling.pitching.PitchingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bowling.global.utils.CommonConstant.NUMBER_ZERO;

public class Frames {

    private static final int INIT_PINS_NUMBER = 10;
    private static final int FINAL_FRAME_NUMBER = 8;

    private List<Frame> frames;

    private Frames() {
        this.frames = new ArrayList<>();
    }

    public static Frames startBowling() {
        return new Frames();
    }

    public List<Frame> playBowling(int number, Pitching pitching, int remainingPins) { // 일단 첫번째 투구를 받는다.
        Frame frame = pitchingFrame(number, pitching, remainingPins); // n 프레임중 1 프레임이 시작된다.
        this.frames.add(frame);
        return frames;
    }

    public Frame pitchingFrame(int number, Pitching pitching, int remainingPins) {
        if (number == 10) {
            return FinalFrame.pitchi(pitching, remainingPins, 2);
        }
        return NormalFrame.pitchi(number, pitching, remainingPins);
    }

    public List<Frame> pitchingResult(List<Frame> frameList) {
        Frame frame = frameList.get(frameList.size() - 1);
        PitchingResult prevPitchingResult = frame.getPrevPitchingResult();

        if (prevPitchingResult.getFirstPitchingCount() && prevPitchingResult.getPitchResultState().equals("X")) {
            this.frames.add(frame.nextFrame());
        }

        if (prevPitchingResult.getPitchingCount() == 2) {
            this.frames.add(frame.nextFrame());
        }

        if (prevPitchingResult.getPitchingCount() == 2 && getFrameNumber() == FINAL_FRAME_NUMBER) {
            this.frames.add(FinalFrame.from());
        }
        return frames;
    }

    public int getFrameNumber() {
        if (isFramesNotBlank()) {
            return currentFrame().getNumber();
        }
        return 8;
    }

    public int getPitchingCount() {
        if (isFramesNotBlank() && isPitchingResultsNotBlank()) {
            return prevPitchingResult().getPitchingCount();
        }
        return 0;
    }

    public int getRemainingPins() {
        if (isFramesNotBlank() && isPitchingResultsNotBlank()) {
            return prevPitchingResult().getRemainingPins();
        }
        return INIT_PINS_NUMBER;
    }

    public PitchingResult prevPitchingResult() {
        return currentFrame().getPrevPitchingResult();
    }

    public boolean isFramesNotBlank() {
        return (frames != null && frames.size() != NUMBER_ZERO);
    }

    public boolean isPitchingResultsNotBlank() {
        return (currentFrame().getPitchingResults() != null);
    }

    public boolean isFinal() {
        if (frames == null || frames.size() == NUMBER_ZERO) {
            return false;
        }
        return currentFrame().isFinal();
    }

    public Frame currentFrame() {
        return frames.get(this.frames.size() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }

    @Override
    public String toString() {
        return "Frames{" +
                "frames=" + frames +
                '}';
    }
}
