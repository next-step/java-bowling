package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.domain.state.Pin;

import java.util.LinkedList;

public class Bowling {
    private LinkedList<Frame> frames;
    private Player player;

    public Bowling(LinkedList<Frame> frames, Player player) {
        this.frames = frames;
        this.player = player;
    }

    public void bowl(Pin fallenPins) {
        Frame frame = frames.getLast();
        validateNextFrame(frame, fallenPins);
    }

    private void validateNextFrame(Frame currentFrame, Pin fallenPins) {
        Frame frame = currentFrame.bowl(fallenPins);
        if (!currentFrame.equals(frame)) {
            frames.add(frame);
        }
    }

    public LinkedList<Frame> getFrames() {
        return new LinkedList<>(frames);
    }

    public String getName() {
        return player.getName();
    }

}
