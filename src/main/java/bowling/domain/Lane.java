package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.pin.FallenPin;
import bowling.domain.player.PlayerName;

import java.util.Objects;

public class Lane {
    private final PlayerName playerName;
    private Frames frames;

    public Lane(PlayerName playerName) {
        this(playerName, Frames.init());
    }

    public Lane(PlayerName playerName, Frames frames) {
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
        if (!(o instanceof Lane)) return false;

        Lane lane = (Lane) o;

        if (!Objects.equals(playerName, lane.playerName)) return false;
        return Objects.equals(frames, lane.frames);
    }

    @Override
    public int hashCode() {
        int result = playerName != null ? playerName.hashCode() : 0;
        result = 31 * result + (frames != null ? frames.hashCode() : 0);
        return result;
    }
}
