package bowling_step3.domain;

import bowling_step3.domain.state.GameOver;

public class Player {
    private final String name;
    private final Frames frames;

    public Player(String name, Frames frames) {
        verify(name);
        this.name = name;
        this.frames = frames;
    }

    private void verify(String name) {
        if (name.length() > 3) {
            throw new IllegalArgumentException("name cannot be longer than 3.");
        }
    }

    public String name() {
        return this.name;
    }

    public boolean gameOver() {
        return this.frames.last().status() instanceof GameOver;
    }

    public Frames frames() {
        return this.frames;
    }
}
