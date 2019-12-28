package bowling.retry;

import java.util.LinkedList;

public class Bowling {

    private static final int INITIAL_FRMAE_INDEX = 0;

    private LinkedList<Frame> frames;

    public Bowling() {
        this.frames = new LinkedList<>();
        frames.add(new Frame(INITIAL_FRMAE_INDEX));
    }

    public void go(int countOfHit) {
        Frame frame = frames.peekLast();
        if (frame.isNext()) {
            frames.add(createNextFrame(countOfHit));
            return;
        }
        frame.bowl(countOfHit);
    }

    private Frame createNextFrame(int countOfHit) {
        Frame frame = new Frame(frames.size());
        frame.bowl(countOfHit);
        return frame;
    }

    public LinkedList<Frame> getFrames() {
        return new LinkedList<>(frames);
    }
}
