package bowling.controller;

import bowling.domain.Frames;

public class BowlingGameController {

    public Frames createFrames(String name) {
        return new Frames(name);
    }

}
