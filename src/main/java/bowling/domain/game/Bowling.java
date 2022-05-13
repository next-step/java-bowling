package bowling.domain.game;

import bowling.domain.frame.Frames;

public class Bowling {

    private final Player player;
    private final Frames frames;

    public Bowling(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public boolean isFinished() {
        return frames.isFinished();
    }

    public int currentFrame() {
        return frames.currentFrame();
    }

    public void addPin(int pinNo) {
        frames.addPin(pinNo);
    }
}
