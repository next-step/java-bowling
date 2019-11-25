package bowling.controller;

import bowling.domain.Frames;
import bowling.view.ResultView;
import bowling.view.Template;

public class BowlingController {

    public Frames start(int score) {
        Frames frames = Frames.initiate(score);
        Template.apply(frames);
        ResultView.printTemplate();
        return frames;
    }

    public Frames execute(Frames frames, int score) {
        Frames framesWithScore;
        if (frames.isRemain()) {
            framesWithScore = frames.nextSecond(score);
            Template.apply(framesWithScore);
            return framesWithScore;
        }
        framesWithScore = frames.next(score);
        Template.apply(framesWithScore);
        return framesWithScore;
    }
}
