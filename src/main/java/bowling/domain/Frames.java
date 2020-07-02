package bowling.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private Player player;
    private LinkedList<Frame> frames;

    public Frames(Player player) {
        this.player = player;
        this.frames = new LinkedList<>();
        frames.add(Frame.first());
    }

    public FrameState bowling(Pin pin) {
        FrameState frameState = getLastFrame().bowling(pin);
        if (frameState.isNew()) {
            frames.add(getLastFrame().next());
        }
        return frameState;
    }

    private Frame getLastFrame() {
        return frames.getLast();
    }

    public List<ShotHistory> generateShotHistories() {
        return frames.stream()
                .map(Frame::getShotHistory)
                .collect(Collectors.toList());
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getCurrentFrameNo() {
        return frames.size();
    }
}
