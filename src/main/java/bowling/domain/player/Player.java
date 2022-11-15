package bowling.domain.player;

import bowling.domain.Frame;
import bowling.domain.Frames;

import java.util.Objects;

public class Player {
    private final Name name;
    private final Frames frames;

    public Player(String name) {
        this(new Name(name));
    }

    public Player(Name name) {
        this.name = name;
        this.frames = Frames.init();
    }

    public Name getName() {
        return name;
    }

    public Frames getFrames() {
        return frames;
    }

    public boolean isGameEnd() {
        return frames.isLast();
    }

    public boolean canBowl() {
        return frames.lastFrame().canBowl();
    }

    public void bowl(int number) {
        Frame frame = frames.lastFrame();
        frame.bowl(number);

        if (!frame.isLastFrame() && frame.isFinished()) {
            frames.addFrame();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
