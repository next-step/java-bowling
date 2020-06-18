package bowling.step3.domain;

import bowling.step3.domain.frame.Frame;

import java.util.stream.Stream;

public class PlayerFrames {
    private final Player player;
    private final Frame firstFrame;

    private PlayerFrames(Player player, Frame firstFrame) {
        this.player = player;
        this.firstFrame = firstFrame;
    }

    public static PlayerFrames of(Player player, Frame firstFrame) {
        return new PlayerFrames(player, firstFrame);
    }

    public Stream<Frame> getPreview() {
        return Frames.preview(firstFrame);
    }

    public Player getPlayer() {
        return player;
    }
}