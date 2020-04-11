package bowling.domain;

import java.util.List;

public class Game {
    private static final int DEFAULT_FRAME_SIZE = 10;
    private Player player;
    private Frames frames;


    protected Game(String playerName, Frames frames) {
        this.player = new Player(playerName);
        this.frames = frames;
    }

    protected Game(String playerName, int frameSize) {
       this.player = new Player(playerName);
       this.frames = new Frames(frameSize);
    }

    public Game(String playerName) {
        this(playerName, DEFAULT_FRAME_SIZE);
    }

    public boolean isFinished() {
        return !frames.isAddable();
    }

    public void addPin(int count) {
        frames.add(count);
    }

    public List<Integer> getFramePinCount(int index) {
        return frames.getFramePinCount(index);
    }
}
