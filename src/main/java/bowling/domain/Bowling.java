package bowling.domain;

import bowling.domain.frame.Frames;

public class Bowling implements Playable {

    private Player player;
    private Frames frames;

    public Bowling(String name) {
        player = new Player(name);
        frames = new Frames();
    }

    @Override
    public void play(int point) {
        frames.moveFrameIfNeeded();
        frames.bowl(point);
        frames.updateScores(point);
    }

    @Override
    public boolean isEnd() {
        return frames.isEnd();
    }

    public int frameCount() {
        return frames.frameCount();
    }

    public int closedScores() {
        return frames.closedScores();
    }

    public Player player() {
        return player;
    }

    public Frames frames() {
        return frames;
    }

}
