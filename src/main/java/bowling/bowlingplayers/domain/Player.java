package bowling.bowlingplayers.domain;

import bowling.bowlingplayers.domain.frame.Frames;
import bowling.bowlingplayers.exception.CustomException;

import java.util.regex.Pattern;

public class Player {
    private final String name;
    private final Frames frames;

    public Player(String name) {
        validateName(name);
        this.name = name;
        this.frames = new Frames();
    }

    private void validateName(String name) {
        String regex = "[a-zA-z]{3}";
        if(!Pattern.matches(regex, name)) {
            throw new CustomException("player 이름은 알파벳 3글자만 허용합니다.");
        }
    }

    public void pitch(int pins) {
        frames.pitch(pins);
    }

    public Frames frames() {
        return frames;
    }

    public boolean end() {
        return frames.end();
    }

    public String name() {
        return name;
    }

    public int currentFrame() {
        return frames.currentFrameNumber();
    }
}
