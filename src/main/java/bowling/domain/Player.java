package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.PlayerFrames;

import java.util.Objects;

public class Player {
    private final String name;
    private final PlayerFrames playerFrames;
    private final Frame currentFrame;

    Player(String name, PlayerFrames playerFrames, Frame currentFrame) {
        this.name = name;
        this.playerFrames = playerFrames;
        this.currentFrame = currentFrame;
    }

    public static Player createByName(String playerName) {
        return new Player(playerName, PlayerFrames.createEmpty(), null);
    }

    public Player bowlFirst(int numberOfHitPin) {
        NormalFrame firstFrame = NormalFrame.start(numberOfHitPin);

        return new Player(name, playerFrames.lastValue(firstFrame), firstFrame);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(playerFrames, player.playerFrames) &&
                Objects.equals(currentFrame, player.currentFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, playerFrames, currentFrame);
    }
}
