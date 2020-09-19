package bowling.domain;

import java.util.LinkedList;
import java.util.regex.Pattern;

import static bowling.domain.LastFrame.LAST_FRAME_NUMBER;

public class Bowling {
    public static final int NAME_LENGTH = 3;
    public static final String REGX_ONLY_ENGLISH_NAME = "^[a-zA-Z]*$";

    private String username;
    private LinkedList<Frame> frames;

    private Bowling(String username) {
        this.username = username;
        this.frames = new LinkedList<Frame>() {{
            add(NormalFrame.from());
        }};
    }

    public static Bowling from(String username) {
        if (!Pattern.matches(REGX_ONLY_ENGLISH_NAME, username)) {
            throw new IllegalArgumentException("폴레이어의 이름은 영어로만 입력가능합니다.");
        }

        if (username.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("폴레이어의 이름은 3자리로 제한됩니다.");
        }
        return new Bowling(username);
    }

    public String getPlayerName() {
        return username;
    }

    public Frame hit(int count) {
        Frame frame = getLastFrame().hit(count);

        if (hasNextFrame() && getLastFrame().isFinish()) {
            frames.add(next());
        }

        return frame;
    }

    private Frame next() {
        if (!hasNextFrame()) {
            throw new RuntimeException("다음 프레임이 존재하지 않습니다.");
        }

        int nextFrameNumber = getNextFrameNumber();
        return nextFrameNumber == LAST_FRAME_NUMBER ? LastFrame.from() : NormalFrame.of(nextFrameNumber);
    }

    public boolean hasNextFrame() {
        return getNextFrameNumber() <= LAST_FRAME_NUMBER;
    }

    private int getNextFrameNumber() {
        return getLastFrame().getNumber() + 1;
    }

    private Frame getLastFrame() {
        return frames.getLast();
    }

    public boolean isEnd() {
        return !hasNextFrame() && getLastFrame().isFinish();
    }
}
