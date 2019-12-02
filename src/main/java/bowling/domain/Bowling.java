package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Bowling {

    private static final int FINAL_FRAME_MIN_SIZE = 2;

    private NormalFrames normalFrames;
    private FinalFrames finalFrames;

    public Bowling() {
        this.normalFrames = NormalFrames.of(new ArrayList<>());
        this.finalFrames = FinalFrames.of(new ArrayList<>());
    }

    public void go(int countOfHit) {
        if (getCurrentFrame() <= 8) {
            normalFrames.next(countOfHit);
        } else {
            finalFrames.next(countOfHit);
        }
    }

    public List<Frame> getFrames() {
        return new ArrayList<>(normalFrames.getFrames());
    }

    public List<Frame> getFinalFrames() {
        return new ArrayList<>(finalFrames.getFrames());
    }

    public long getCurrentFrame() {
        return normalFrames.getCurrentFrame();
    }

    public boolean isFinal() {
        return finalFrames.size() < FINAL_FRAME_MIN_SIZE;
    }

    public boolean isBonus() {
        return finalFrames.isBonus();
    }
}
