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

    public boolean isFameNo(int frameNo) {
        return frames.getCurrentFrameNo() == frameNo;
    }

    public boolean isFrameEnd(int frameNo) {
        return frames.getCurrentFrameNo() > frameNo;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public PlayerDto convertToDto() {
        return PlayerDto.of(frames.convertToDto(), playerName.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        return getPlayerName().equals(player.getPlayerName());
    }

    @Override
    public int hashCode() {
        return getPlayerName().hashCode();
    }

    @Override
    public String toString() {
        return "Player{" +
                "frames=" + frames +
                ", playerName=" + playerName +
                '}';
    }
}
