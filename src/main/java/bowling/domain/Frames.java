package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    public final int FIRST_FRAME = 1;
    public final int FINAL_FRAME = 10;

    private List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
        frames.add(new Frame(FIRST_FRAME));
    }

    public boolean hasNext() {
        return !(frames.size() == FINAL_FRAME && getLatestFrame().isFinished());
    }

    private Frame getLatestFrame() {
        return frames.get(frames.size() - 1);
    }

    public int getNeedBallTurn() {
        return frames.size();
    }

    public void bowl(int pin) {
        Frame latest = getLatestFrame();
        latest.bowl(pin);
        if (latest.isFinished()) {
            frames.add(latest.ofNext());
        }
    }


}
