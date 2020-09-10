package bowling.domain;

import java.util.List;
import java.util.Objects;

public class Bowler {

    private String name;
    private Frames frames;

    private Bowler(String name) {
        this.name = name;
        frames = Frames.create();
    }

    public static Bowler from(String name) {
        return new Bowler(name);
    }

    public boolean isPlaying() {
        return frames.isPlaying();
    }

    public void record(int hitCount) {
        frames.record(hitCount);
    }

    public int getPlayStage() {
        return frames.getPlayStage();
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bowler bowler = (Bowler) o;
        return Objects.equals(name, bowler.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
