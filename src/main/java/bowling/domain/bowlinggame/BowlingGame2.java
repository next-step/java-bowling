package bowling.domain.bowlinggame;

import bowling.domain.KnockDownPins;
import bowling.domain.PlayerName;
import bowling.domain.frames.Frames2;
import bowling.dto.BowlingGame2Dto;

public class BowlingGame2 {
    private final Frames2 frames;
    private final PlayerName playerName;

    private BowlingGame2(Frames2 frames, PlayerName playerName) {
        this.frames = frames;
        this.playerName = playerName;
    }

    public static BowlingGame2 init(PlayerName playerName) {
        return new BowlingGame2(Frames2.init(), playerName);
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

    public BowlingGame2Dto convertToDto() {
        return BowlingGame2Dto.of(frames.convertToDto(), playerName.getValue());
    }
}
