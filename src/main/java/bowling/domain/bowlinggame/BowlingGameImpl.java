package bowling.domain.bowlinggame;

import bowling.domain.KnockDownPins;
import bowling.domain.PlayerName;
import bowling.domain.frames.FrameViewDto;
import bowling.domain.frames.FramesImpl;

import java.util.stream.Stream;

public class BowlingGameImpl implements BowlingGame, BowlingGameViewDto {
    private final FramesImpl frames;
    private final PlayerName playerName;

    private BowlingGameImpl(FramesImpl frames, PlayerName playerName) {
        this.frames = frames;
        this.playerName = playerName;
    }

    public static BowlingGameImpl init(PlayerName playerName) {
        return new BowlingGameImpl(FramesImpl.init(), playerName);
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
