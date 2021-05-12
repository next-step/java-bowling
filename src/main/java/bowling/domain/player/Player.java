package bowling.domain.player;

import bowling.domain.HitNumber;
import bowling.domain.frame.Frames;

import java.util.Objects;

public class Player {
    private final Name name;
    private final Frames frames;

    private Player(Name name) {
        this.name = name;
        this.frames = Frames.of();
    }

    private Player(Name name, Frames frames) {
        this.name = name;
        this.frames = frames;
    }

    public static Player of(String name) {
        return new Player(Name.of(name));
    }

    public static Player of(String name, Frames frames) {
        return new Player(Name.of(name), frames);
    }

    public boolean bowlWithNext(HitNumber hitNumber) {
        return frames.playWithNext(hitNumber);
    }

    public boolean isFinished() {
        return frames.isFinished();
    }

    public String toStringName() {
        return name.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(frames, player.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, frames);
    }

}
