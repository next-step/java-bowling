package game;

import java.util.Objects;

public class BowlingGame {
    private static final String USER_NAME_LENGTH_EXCEPTION = "이용자 이름은 3글자입니다";
    private String name;
    private Frames frames;

    public BowlingGame(String name) {
        if (name.length() > 3) {
            throw new IllegalArgumentException(USER_NAME_LENGTH_EXCEPTION);
        }
        this.name = name;
        this.frames = new Frames();
    }

    public void addFrame(Frame frame) {
        this.frames.addFrame(frame);
    }

    public String getName() {
        return this.name;
    }

    public Frames getFrames() {
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
