package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    public static final int MAP_ROUND_TO_INDEX_CONSTANT = 1;

    private final List<Frame> frames = new ArrayList<>();
    private final String player;

    public Frames(String player) {
        this.player = player;
    }

    public boolean playFrame(final int numberOfFallenPins) {
        addFrame(new Frame()); // Issue: Is it correct to inject from outside?
        Frame frame = play(numberOfFallenPins);
        return frame.endFrame();
    }

    private void addFrame(final Frame frame) {
        if (frames.size() == 0) {
            frames.add(frame);
        }
        if (lastFrame().endFrame()) {
            frames.add(frame);
        }
    }

    private Frame play(final int numberOfFallenPins) {
        return lastFrame().play(lastIndex(), numberOfFallenPins);
    }

    public Frame lastFrame() {
        return frames.get(lastIndex());
    }

    private int lastIndex() {
        return frames.size() - MAP_ROUND_TO_INDEX_CONSTANT;
    }

    public List<Frame> getGameRecords() {
        return Collections.unmodifiableList(frames);
    }

    public String getPlayer() {
        return player;
    }
}
