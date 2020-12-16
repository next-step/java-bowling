package bowling.domain.bowlinggame;

import bowling.domain.KnockDownPins;
import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.stream.Stream;

public class BowlingGame implements BowlingGameService, BowlingGameViewDto {
    private final Frames frames;
    private final PlayerName playerName;

    private BowlingGame(Frames frames, PlayerName playerName) {
        this.frames = frames;
        this.playerName = playerName;
    }

    public static BowlingGame init(PlayerName playerName) {
        return new BowlingGame(Frames.init(), playerName);
    }

    @Override
    public void setKnockDownPins(KnockDownPins knockDownPins) {
        frames.setKnockDownPins(knockDownPins);
    }

    @Override
    public boolean isEnd() {
        return frames.isEnd();
    }

    @Override
    public int getCurrentFrameIndex() {
        return frames.getCurrentFrameIndex();
    }

    @Override
    public Stream<Frame> framesStream() {
        return frames.stream();
    }

    @Override
    public PlayerName getPlayerName() {
        return playerName;
    }
}
