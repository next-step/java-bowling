package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.domain.state.Pin;

import java.util.LinkedList;

public class Bowling {
    private static final int FINAL_FRAME_NUMBER = 10;
    private static final int START_FRAME_NUMBER = 1;
    private static final int READY_FRAME = 0;

    private LinkedList<Frame> frames;
    private Player player;

    public Bowling(LinkedList<Frame> frames, Player player) {
        this.frames = frames;
        this.player = player;
    }

    public void bowl(Pin fallenPins) {
        if (frames.size() == READY_FRAME) {
            Frame frame = new NormalFrame(START_FRAME_NUMBER);
            frame.bowl(fallenPins);
            frames.add(frame);
            return;
        }
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
        return frames;
    }

    public String getName() {
        return player.getName();
    }

}
