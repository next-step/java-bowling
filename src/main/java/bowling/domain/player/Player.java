package bowling.domain.player;

import bowling.domain.frame.Frames;

import java.util.Objects;

public class Player {
    private Name name;
    private Frames frames;

    public Player(String name) {
        this.name = new Name(name);
        this.frames = new Frames();
    }

    public String getName() {
        return name.getValue();
    }

    public Frames getFrames() {
        return frames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(frames, player.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, frames);
    }
}
