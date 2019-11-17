package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BowlingGame {
    private String name;
    private List<Frame> frames;

    public BowlingGame(String name) {
        this.name = name;
        this.frames = new ArrayList<>();
    }

    public void addFrame(Frame frame) {
        if (this.frames.size() > 9) {
            throw new IllegalArgumentException("프레임은 10번을 넘을 수 없습니다");
        }
        this.frames.add(frame);
    }

    public String getName() {
        return this.name;
    }

    public List<Frame> getFrames() {
        return this.frames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(frames, that.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, frames);
    }
}
