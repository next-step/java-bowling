package bowling;

import java.util.Objects;

public class Player {
    private String name;
    private Frames frames;

    Player(String name) {
        this.name = name;
        this.frames = new Frames();
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
