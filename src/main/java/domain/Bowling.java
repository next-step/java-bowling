package domain;

import View.OutView;
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

    public boolean nowPlaying() {

        if(frame.isFinalFrame()) {
            return frame.nowPlaying() ? Boolean.TRUE : Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Frame getFrame(int frameNumber) {
        Optional<Frame> maybeNextFrame = Optional.ofNullable(firstFrame);
        for (int number = ONE; number < frameNumber; number++) {
            maybeNextFrame = Optional.ofNullable(maybeNextFrame.orElse(firstFrame).getNext());
        }
        return maybeNextFrame.orElse(null);
    }
}
