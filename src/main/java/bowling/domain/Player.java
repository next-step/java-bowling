package bowling.domain;

import bowling.dto.PlayerDto;

public class Player {
    private final Frames frames;
    private final PlayerName playerName;

    private Player(Frames frames, PlayerName playerName) {
        this.frames = frames;
        this.playerName = playerName;
    }

    public static Player init(PlayerName playerName) {
        return new Player(Frames.init(), playerName);
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        frames.setKnockDownPins(knockDownPins);
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public int getCurrentFrameNo() {
        return frames.getCurrentFrameNo();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public PlayerDto convertToDto() {
        return PlayerDto.of(frames.convertToDto(), playerName.getValue());
    }

    @Override
    public String toString() {
        return "BowlingGame{" +
                "frames=" + frames +
                ", playerName=" + playerName +
                '}';
    }
}
