package bowling.domain.game;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.List;
import java.util.Optional;

public class Bowling {

    private final Player player;
    private final Frames frames;

    public Bowling(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public void addNormalFrame(int firstNo, int secondNo) {
        frames.addNormalFrame(firstNo, secondNo);
    }

    public void addFinalFrame(int firstNo, int secondNo) {
        frames.addFinalFrame(firstNo, secondNo);
    }

    public void addExtra(int extraNo) {
        frames.addExtra(extraNo);
    }

    public String getPlayerName() {
        return player.getName();
    }

    public List<Frame> getNormalFrames() {
        return frames.getNormalFrames();
    }

    public Optional<Frame> getFinalFrame() {
        return frames.getFinalFrame();
    }
}
