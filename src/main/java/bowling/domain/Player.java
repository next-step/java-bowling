package bowling.domain;

import java.util.List;

public class Player {
    private String name;
    private Frames frames;

    public Player(String name) {
        this.name = name;
        this.frames = new Frames();
    }

    public String getName() {
        return name;
    }

    public Frame frameByIndex(int index) {
        return frames.frameByIndex(index);
    }
}
