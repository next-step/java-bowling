package bowling.domain.bowlinggame;

import bowling.domain.KnockDownPins;
import bowling.domain.PlayerName;
import bowling.domain.frames.FrameViewDto;
import bowling.domain.frames.Frames;

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
    public Stream<FrameViewDto> framesViewDtoStream() {
        return frames.viewDtoStream();
    }

    @Override
    public PlayerName getPlayerName() {
        return playerName;
    }
}
