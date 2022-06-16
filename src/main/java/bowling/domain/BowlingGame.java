package bowling.domain;

import bowling.domain.frame.Frame;

public class BowlingGame implements Comparable<BowlingGame> {

    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player) {
        this.player = player;
        this.frames = Frames.initialize();
    }

    public void bowling(int hit) {
        frames.bowling(hit);
    }

    public Frame currentFrame() {
        return frames.currentFrame();
    }

    public boolean isFinish() {
        return frames.isFinish();
    }

    public boolean isProgressing() {
        return frames.isProgressing();
    }

    public boolean isSamePlayer(Player player) {
        return this.player.equals(player);
    }

    public Player player() {
        return player;
    }

    public Frames frames() {
        return frames;
    }

    @Override
    public int compareTo(BowlingGame o) {
        return this.currentFrame().frameNo().compareTo(o.currentFrame().frameNo());
    }
}
