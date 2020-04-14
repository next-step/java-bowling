package bowling.domain;

import lombok.Getter;

public class Bowling {
    @Getter
    private Player player;
    @Getter
    private Frames frames;

    public Bowling(String playerName) {
        this.player = new Player(playerName);
        this.frames = new Frames();
    }

    public void play(int clearPinCount) {
        this.frames.play(clearPinCount);
    }

    public int getLastFrameNumber() {
        return this.frames.getFrameNumber();
    }

    public boolean isEnd() {
        return this.frames.isEnd();
    }
}
