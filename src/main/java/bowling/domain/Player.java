package bowling.domain;

import bowling.domain.frame.Frames;

public class Player {
    public static final String PLAYER_NAME_ERROR = "플레이어의 이름은 3글자로 입력해주세요.";
    private static final int PLAYER_NAME_LENGTH = 3;

    private String name;
    private Frames frames;

    public Player(String name) {
        assertPlayerName(name);
        this.name = name;
        this.frames = new Frames();
    }

    public void play(PinCount felledPin) {
        frames.play(felledPin);
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public int getCurrentFrameNumber() {
        return frames.getCurrentFrameNumber();
    }

    public String getName() {
        return name;
    }

    public Frames getFrames() {
        return frames;
    }

    private void assertPlayerName(String name) {
        if (name.length() != PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException(PLAYER_NAME_ERROR);
        }
    }
}
