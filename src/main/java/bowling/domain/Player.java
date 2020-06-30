package bowling.domain;

import java.util.List;

public class Player {
    private static final int NAME_LENGTH = 3;
    private final String name;
    private final Frames frames;

    public Player(String name) {
        validateName(name);
        this.name = name;
        this.frames = null;
    }

    private void validateName(String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("name length is must " + NAME_LENGTH);
        }
    }

    public Player(String name, int finalRound) {
        this.name = name;
        this.frames = new Frames(finalRound);
    }

    public String getName() {
        return name;
    }

    public Frames getFrames() {
        return frames;
    }

    public List<List<Shot>> getGameResult() {
        return frames.getGameResult();
    }

    public int getCurrentRound() {
        return frames.getCurrentFrame();
    }

    public boolean bowling(int pin) {
        return frames.bowling(pin);
    }
}
