package bowling.controller;

import bowling.domain.Frames;

public class BowlingController {

    public Frames start(int score) {
        Frames frames = Frames.initiate(score);
        frames.applyScore();
        return frames;
    }

    public Frames execute(Frames frames, int score) {
        Frames framesWithScore;
        if (frames.isRemain()) {
            framesWithScore = frames.nextSecond(score);
            frames.applyScore();
            return framesWithScore;
        }
        framesWithScore = frames.next(score);
        frames.applyScore();
        return framesWithScore;
    }

    public Frames finish(Frames frames, int score) {
        Frames framesWithScore = frames.nextSecond(score);
        frames.applyScore();
        return framesWithScore;
    }
}
