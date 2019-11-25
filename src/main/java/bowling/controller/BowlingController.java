package bowling.controller;

import bowling.domain.Frames;

public class BowlingController {

    public Frames start(int score) {
        Frames frames = Frames.initiate(score);
        return frames;
    }

    public Frames execute(Frames frames, int score) {
        Frames framesWithScore;
        if (frames.isRemain()) {
            framesWithScore = frames.nextSecond(score);
            return framesWithScore;
        }
        framesWithScore = frames.next(score);
        return framesWithScore;
    }
}
