package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.ArrayList;
import java.util.List;

public final class Bowling {
    private final User player;
    private final Frames frames;

    public Bowling(User user) {
        this(user, new Frames());
    }

    public Bowling(User user, Frames frames) {
        this.player = user;
        this.frames = frames;
    }

    public boolean isFinished() {
        return this.frames.isFinished();
    }

    public Bowling addScore(int score) throws Exception {
        return new Bowling(player, frames.addScore(score));
    }

    public int nowFrame() {
        return this.frames.nowFrame();
    }

    public Frame getFrame(int index) {
        return frames.getFrame(index);
    }

    public User getPlayer() {
        return this.player;
    }

    public List<String> toPrint() {
        List<String> result = new ArrayList<>();
        result.add(player.toPrint());
        result.addAll(frames.toPrint());
        return result;
    }
}
