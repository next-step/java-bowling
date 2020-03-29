package bowling.application;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.LinkedList;
import java.util.List;

public class BowlingService {

    private LinkedList<Frame> bowlFrames;

    public BowlingService() {
        this.bowlFrames = new LinkedList<>();
        this.bowlFrames.add(new Frame(1));
    }

    public List<Frame> bowl(int pins) {
        Frames frames = new Frames(bowlFrames);
        frames.bowl(pins);
        return frames.getFramesByCalculationScore();
    }
}
