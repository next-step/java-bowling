package bowling.domain;

import java.util.LinkedList;

public class Frames {
    private Player player;
    private LinkedList<Frame> frames;

    public Frames(Player player, LinkedList<Frame> frames) {
        this.player = player;
        this.frames = frames;
    }

    public static Frames getDefault(Player player, LinkedList<Frame> frames) {

        return new Frames(player, frames);
    }

    public long size() {
        return frames.size();
    }

    public String getResult(int frameIndex) {
        return frames.get(frameIndex)
                .getResult();
    }

    public String getPlayer() {
        return player.getName();
    }
}
