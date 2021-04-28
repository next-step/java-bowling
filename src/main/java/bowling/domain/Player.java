package bowling.domain;

import java.util.List;
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

    public List<Mark> marks() {
        return frames.marks();
    }

    public List<Integer> scores() {
        return frames.scores();
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
