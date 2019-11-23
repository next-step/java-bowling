package bowling;

import bowling.frame.Frame;

import java.util.Objects;

public class BowlingGame {
    private static final String USER_NAME_LENGTH_EXCEPTION = "이용자 이름은 3글자입니다";
    private String user;
    private Frames frames;

    public BowlingGame(String user) {
        if (user.length() > 3) {
            throw new IllegalArgumentException(USER_NAME_LENGTH_EXCEPTION);
        }
        this.user = user;
        this.frames = new Frames();
    }

    public Frame roll(int pins) {
        return frames.roll(pins);
    }

    public String getUser() {
        return this.user;
    }

    public Frames getFrames() {
        return this.frames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(frames, that.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, frames);
    }
}
