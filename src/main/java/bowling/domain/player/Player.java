package bowling.domain.player;

import bowling.domain.frame.FrameResults;
import bowling.domain.frame.Frames;
import bowling.domain.state.PinCount;

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

    public FrameResults getCurrentResult() {
        return frames.getCurrentFrameResults();
    }

    public String getName() {
        return name;
    }

    private void assertPlayerName(String name) {
        if (name.length() != PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException(PLAYER_NAME_ERROR);
        }
    }
}
