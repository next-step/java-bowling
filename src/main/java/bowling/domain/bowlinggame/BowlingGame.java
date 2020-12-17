package bowling.domain.bowlinggame;

import bowling.domain.KnockDownPins;
import bowling.domain.PlayerName;
import bowling.domain.frames.FramesImpl;
import bowling.dto.BowlingGameDto;

public class BowlingGame {
    private final FramesImpl frames;
    private final PlayerName playerName;

    private BowlingGame(FramesImpl frames, PlayerName playerName) {
        this.frames = frames;
        this.playerName = playerName;
    }

    public static BowlingGame init(PlayerName playerName) {
        return new BowlingGame(FramesImpl.init(), playerName);
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        frames.setKnockDownPins(knockDownPins);
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public int getCurrentFrameIndex() {
        return frames.getCurrentFrameIndex();
    }

    public BowlingGameDto convertToDto() {
        return BowlingGameDto.of(frames, playerName);
    }
}
