package bowling.domain.frame;

import bowling.domain.KnockDownPins;
import bowling.domain.PlayerName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Frames {
    public static final int MAX_FRAME_SIZE = 10;
    private final PlayerName playerName;
    private final List<Frame> value;
    private Frame currentFrame;

    private Frames(PlayerName playerName) {
        this.playerName = playerName;
        value = initFrames();
        currentFrame = value.get(0);
    }

    public static Frames init(PlayerName playerName) {
        return new Frames(playerName);
    }

    public List<Frame> initFrames() {
        List<Frame> frames = new ArrayList<>();
        Frame frame = NormalFrame.getFirstFrame();
        frames.add(frame);
        while (frames.size() < MAX_FRAME_SIZE) {
            frame = frame.initNextFrame();
            frames.add(frame);
        }
        return frames;
    }

    public int size() {
        return value.size();
    }

    public boolean isEnd() {
        Frame lastFrame = value.get(MAX_FRAME_SIZE - 1);
        return lastFrame.isEnd();
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        currentFrame = currentFrame.setKnockDownPins(knockDownPins);
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public int getCurrentFrameIndex() {
        return currentFrame.getIndex();
    }

    public Stream<Frame> stream() {
        return value.stream();
    }
}
