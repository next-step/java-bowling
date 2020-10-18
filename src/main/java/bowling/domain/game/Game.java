package bowling.domain.game;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.pin.Pin;
import bowling.domain.player.Player;

public class Game {
    private static final int FINAL_FRAME = 9;

    private final Frames frames;
    private final Player player;

    public Game(Player player) {
        this.frames = new Frames();
        this.player = player;
    }

    public Frame roll(Frame frame, Pin pin) {
        frame.roll(pin.getPin());
        frames.calculateScores(pin);
        frame.addScore();
        return frames.getNextFrame();
    }

    public Player getPlayer() {
        return player;
    }

    public Frame getFrame(int index) {
        return frames.getFrame(index);
    }

    public Frames getFrames() {
        return frames;
    }

    public Frame getFirstFrame() {
        return frames.getFirstFrame();
    }


}
