package domain;

import domain.frame.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGame {

    private Player player;
    private Frames frames;

    private BowlingGame(Player player) {
        this.player = player;
        this.frames = Frames.from(Arrays.asList(NormalFrame.initFrame()));
    }

    public static BowlingGame from(Player player) {
        return new BowlingGame(player);
    }

    public void play(Pins fallenPins) {
        frames.play(fallenPins);
    }

    public FrameIndex currentFrameIndex() {
        return frames.currentFrameIndex();
    }

    public boolean isGameOver() {
        return frames.isGameOver();
    }

    public Player getPlayer() {
        return player;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public int sizeOfFrames() {
        return frames.sizeOfFrames();
    }

    public FrameResults getFrameResults() {
        return frames.getResults();
    }
}
