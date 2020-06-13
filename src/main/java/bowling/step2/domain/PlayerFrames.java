package bowling.step2.domain;

import bowling.step2.domain.frame.Frame;

import java.util.stream.Stream;

public class PlayerFrames {
    private final Player player;
    private final Frames frames;

    private PlayerFrames (Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static PlayerFrames of (Player player, Frames frames) {
        return new PlayerFrames(player, frames);
    }

    public static PlayerFrames ofFrame (Player player, Frame frame) {
        return of(player, Frames.ofLastFrame(frame));
    }

    public static PlayerFrames init (Player player) {
        return of(player, Frames.init());
    }

    public Stream<Frame> getPreview () {
        return frames.preview();
    }

    public Player getPlayer () {
        return player;
    }
}