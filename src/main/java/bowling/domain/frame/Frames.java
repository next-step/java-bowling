package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;

import bowling.domain.score.FinalScores;
import bowling.domain.score.Scores;
import bowling.domain.strategy.BowlingStrategy;

public class Frames {
    public static final FrameNumber MAX_FRAME = FrameNumber.FRAME_10;
    private final List<Frame> frames;

    public Frames() {
        frames = new ArrayList<>();
    }

    public int pitch(FrameNumber frame, BowlingStrategy bowlingStrategy) {
        if (frames.size() < frame.number()) {
            addFrame(frame);
        }

        return frames.get(frame.number() - 1).pitch(bowlingStrategy);
    }

    private void addFrame(FrameNumber frame) {
        if (frame == MAX_FRAME) {
            frames.add(new FinalFrame(new FinalScores()));
            return;
        }

        frames.add(new NormalFrame(new Scores()));
    }


    public String score(FrameNumber frame) {
        if (frames.size() < frame.number()) {
            return Strings.EMPTY;
        }
        return frames.get(frame.number() - 1).score();
    }

    public boolean availablePitching(FrameNumber frame) {
        if (frames.size() < frame.number()) {
            return true;
        }

        return frames.get(frame.number() - 1).availablePitching();
    }
}
