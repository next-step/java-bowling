package bowling.domain.player;

import bowling.domain.frame.Frames;
import bowling.domain.pin.FallenPin;

import java.util.Objects;

public class Player {
    private final PlayerName playerName;
    private Frames frames;

    public Player(PlayerName playerName) {
        this(playerName, Frames.init());
    }

    public Player(PlayerName playerName, Frames frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    public void bowl(int fallenPins) {
        frames = frames.bowl(FallenPin.of(fallenPins));
    }

    public boolean isFramesFinished() {
        return frames.isOver();
    }

    public boolean isCurrentFrameFinished() {
        return frames.isCurrentFrameFinished();
    }

    public Frames getFrames() {
        return frames;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (!Objects.equals(playerName, player.playerName)) return false;
        return Objects.equals(frames, player.frames);
    }

    @Override
    public int hashCode() {
        int result = playerName != null ? playerName.hashCode() : 0;
        result = 31 * result + (frames != null ? frames.hashCode() : 0);
        return result;
    }
}
