package bowling.domain.frame;

import bowling.domain.pin.Pin;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Frames {

    private LinkedList<Frame> frames;

    private Frames() {
        Frame firstFrame = Frame.init();
        this.frames = new LinkedList<>();
        frames.add(firstFrame);
    }

    public static Frames init() {
        return new Frames();
    }

    public void bowl(Pin felledPin) {
        Frame currentFrame = frames.getLast().bowl(felledPin);
        if(!currentFrame.equals(frames.getLast())) {
            frames.add(currentFrame);
        }
    }

    public boolean isGameOver() {
        return frames.getLast().isEnd();
    }

    public Index getLastIndex() {
        return frames.getLast().getIndex();
    }

    public List<Frame> getFrames() {
        return this.frames;
    }

    public Frame getFrame(Index idx) {
        return frames.get(idx.getIndex()-1);
    }

    public List<Integer> getFrameScore() {
        return frames.stream()
                .map(frame -> frame.getScore(this))
                .filter(score -> !score.isPending())
                .map(Score::getScore)
                .collect(toList());
    }

    public Frame getNextFrame(Index idx) {
        Frame nextFrame = null;
        if(this.getFrames().size() > idx.getIndex()) {
            nextFrame = this.frames.get(idx.getIndex());
        }
        return nextFrame;
    }

}
