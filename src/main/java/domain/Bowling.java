package domain;

import View.PointResultFormatter;
import View.ScoreResultFormatter;

import java.util.Optional;

import static domain.Frame.ONE;

public class Bowling {
    public static final int TOTAL_FRAME_COUNT = 10;
    public static final int MAX_BOWL_COUNT = 2 * 9 + 3;

    private Frame firstFrame;
    private Frame frame;

    public Bowling() {
        frame = firstFrame = new Frame();
    }

    public void playBowling(int point) {
        frame = frame.doBowling(point);
    }

    public String getFormattedPointResult() {
        PointResultFormatter pointResultFormatter = new PointResultFormatter();
        return pointResultFormatter.format(this);
    }

    public String getFormattedScoreResult() {
        ScoreResultFormatter scoreResultFormatter = new ScoreResultFormatter();
        return scoreResultFormatter.format(this);
    }

    public boolean isBowlingEnd() {
        return frame.isFinalFrame() ? Boolean.TRUE : Boolean.FALSE;
    }

    public Frame getFrame(int frameNumber) {
        if (getFrameCount() < frameNumber) {
            return null;
        }

        Optional<Frame> maybeTargetFrame = Optional.ofNullable(firstFrame);
        for (int number = ONE; number < frameNumber; number++) {
            maybeTargetFrame = Optional.ofNullable(maybeTargetFrame.get().getNext());
        }
        return maybeTargetFrame.orElse(null);
    }

    private int getFrameCount() {
        int count = ONE;
        Frame target = firstFrame;
        while (target.getNext() != null) {
            target = target.getNext();
            count++;
        }
        return count;
    }

    public void getNextFrame() {
        if (frame.isFrameEnd()) {
            frame = frame.createNext();
        }
    }
}
