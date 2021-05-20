package bowling.domain;

import bowling.domain.frame.Frames;

public class Bowling implements Playable{

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
//        frames.updateScores();
    }

    @Override
    public boolean isEnd() {
        return frames.isEnd();
    }

    public int closedScores() {
        return frames.closedFrames();
    }

    public int frameCount() {
        return frames.frameCount();
    }

    public Frames frames() {
        return frames;
    }
}
