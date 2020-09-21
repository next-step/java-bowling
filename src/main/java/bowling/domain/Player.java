package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.Objects;

public class Player {

    public static final int MAX_NAME_LENGTH = 3;

    private final String name;
    private final Frame firstFrame = NormalFrame.firstFrame();

    private Frame frame = firstFrame;

    public Player(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("세글자 까지 입력할 수 있습니다.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Frame bowl(int numberOfPins) {
        frame = frame.bowl(numberOfPins);
        return frame;
    }

    public Frame getFirstFrame() {
        return firstFrame;
    }

    public Frame getFrame() {
        return frame;
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
