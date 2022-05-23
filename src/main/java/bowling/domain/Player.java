package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.pin.Pin;

import java.util.List;
import java.util.regex.Pattern;

public class Player {

    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z]{3}");

    private final String name;
    private final Frames frames;

    public Player(String name) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("Player name must be 3 alphabet letters");
        }
        this.name = name;
        this.frames = new Frames();
    }

    public void bowl(Pin no) {
        frames.bowl(no);
    }

    public boolean finished() {
        return frames.finished();
    }

    public int currentFrame() {
        return frames.currentFrame();
    }

    public String name() {
        return name;
    }

    public List<String> expressions() {
        return frames.expressions();
    }

    public List<Integer> scores() {
        return frames.scores();
    }
}
