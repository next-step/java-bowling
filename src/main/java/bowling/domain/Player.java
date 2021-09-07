package bowling.domain;

import java.util.Objects;

public class Player {
    private String name;
    private Frames frames;

    public Player(String name) {
        this.name = name;
        this.frames = new Frames();
    }

    public String getName() {
        return name;
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
