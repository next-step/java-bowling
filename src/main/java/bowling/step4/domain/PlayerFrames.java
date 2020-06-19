package bowling.step4.domain;

import bowling.step4.domain.frame.FinalFrame;
import bowling.step4.domain.frame.Frame;
import bowling.step4.domain.scores.FinalScores;

import java.util.List;
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

    public String getPlayerName() {
        return player.toString();
    }

    public Frame getLastFrame() {
        return Frames.getLastFrameOf(firstFrame);
    }
}