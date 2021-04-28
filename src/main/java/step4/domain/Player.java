package step4.domain;

import java.util.Objects;

public class Player {
    private final Name name;
    private final Frames frames;

    public Player(Name name) {
        this.name = name;
        this.frames = new Frames();
    }

    public Player(String name) {
        this(new Name(name));
    }

    public Name name() {
        return name;
    }

    public void throwBowl(String pinCount) {
        frames.throwBowl(pinCount);
    }

    public Frames frames() {
        return frames;
    }

    public int currentFrameIndex() {
        return frames.currentIndex();
    }

    public boolean hasFinishedGame() {
        return frames.isAllFinished();
    }

    public boolean hasFinishedFrame(int index) {
        return frames.get(index).isFinished();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
