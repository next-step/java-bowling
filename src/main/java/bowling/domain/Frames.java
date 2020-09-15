package bowling.domain;

import java.util.LinkedList;

public class Frames {
    private Player player;
    private LinkedList<Frame> frames;

    public Frames(Player player, LinkedList<Frame> frames) {
        this.player = player;
        this.frames = frames;
    }

    public static Frames init(Player player) {
        return new Frames(player, new LinkedList<>());
    }

    public static Frames of(Frames previousFrames, Frame frame) {
        LinkedList<Frame> nowFrames = previousFrames.frames;
        nowFrames.add(frame);

        return new Frames(previousFrames.player, nowFrames);
    }

    public String getResult(int frameNumber) {
        return frames.get(frameNumber)
                .getResult();
    }

    public String getPlayerName() {
        return player.getName();
    }
}
