package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final int LAST_FRAME = 10;

    private final String name;

    private boolean isDone;

    private List<FrameOld> frameOlds = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        frameOlds.add(FrameOld.first());
    }

    public void addPinCounts(int pintCount) {
        FrameOld frameOld = frameOlds.get(frameOlds.size() - 1);
        frameOld.addPinCounts(pintCount);
        if (frameOld.isDone()) {
            if (frameOld.number() == LAST_FRAME) {
                isDone = true;
            } else {
                frameOlds.add(frameOld.next());
            }

        }
    }

    public int currentFrameNumber() {
        return frameOlds.get(frameOlds.size() - 1).number();
    }

    public String name() {
        return name;
    }

    public List<FrameOld> frames() {
        return frameOlds;
    }

    public boolean isDone() {
        return isDone;
    }
}
