package bowling;

import bowling.frame.Frame;
import bowling.frame.Frames;

import java.util.List;
import java.util.Objects;

public class BowlingGame {

    private final Frames frames;
    private final Player player;

    public BowlingGame(Player player) {
        this.frames = Frames.first();
        this.player = player;
    }

    public void bowl(Pins fallenPins) {
        frames.bowl(fallenPins);
    }

    public int recentFrameNo() {
        return frames.recentFrameNo();
    }

    public boolean hasNextBowl() {
        return frames.hasNextBowl();
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public String getPlayerName() {
        return player.getName();
    }

    public boolean isEnd() {
        return frames.isEnd();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return Objects.equals(getFrames(), that.getFrames()) && Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrames(), player);
    }

    @Override
    public String toString() {
        return "BowlingGame{" +
                "frames=" + frames +
                ", player=" + player +
                '}';
    }
}
