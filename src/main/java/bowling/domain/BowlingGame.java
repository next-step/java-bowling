package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.stream.Stream;

public class BowlingGame {
    private final Frames frames;
    private final PlayerName playerName;

    private BowlingGame(Frames frames, PlayerName playerName) {
        this.frames = frames;
        this.playerName = playerName;
    }

    public static BowlingGame init(PlayerName playerName) {
        return new BowlingGame(Frames.init(), playerName);
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        frames.setKnockDownPins(knockDownPins);
    }

    public int getCurrentFrameIndex() {
        return frames.getCurrentFrameIndex();
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public Stream<Frame> framesStream() {
        return frames.stream();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }
}
