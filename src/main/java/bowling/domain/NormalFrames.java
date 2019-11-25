package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrames {

    public static final int NO_FRAME_SIZE = 0;
    public static final int MAX_FRAME_SIZE = 9;

    private List<NormalFrame> normalFrames = new ArrayList<>();

    public void createFrame(int score) {
        if (this.normalFrames.size() == NO_FRAME_SIZE) {
            this.normalFrames.add(NormalFrame.first(score));
            return;
        }

        if (this.getCurrentFrame().isEnd()) {
            this.normalFrames.add(getCurrentFrame().next(score));
            return;
        }

        getCurrentFrame().next(score);
    }

    private NormalFrame getCurrentFrame() {
        return this.normalFrames.get(normalFrames.size() - 1);
    }

    public boolean isNormalEnd() {
        return normalFrames.size() == MAX_FRAME_SIZE && normalFrames.get(MAX_FRAME_SIZE - 1).isEnd();
    }

    public int getCurrentFrameIndex() {
        if (normalFrames.size() == NO_FRAME_SIZE || getCurrentFrame().isEnd()) {
            return normalFrames.size() + 1;
        }

        return normalFrames.size();
    }

    public List<NormalFrame> getNormalFrames() {
        return normalFrames;
    }
}
