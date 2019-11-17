package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Frames frames;

    public Player(String name) {
        this.name = name;
        this.frames = new Frames();
    }

    public List<String> getResult() {
        List<String> result = new ArrayList<>();
        result.add(name);
        result.addAll(frames.getResult());

        return result;
    }

    public Frame frameByIndex(int index) {
        return frames.frameByIndex(index);
    }
}
