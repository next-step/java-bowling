package bowling.model.score;

import bowling.model.Result;
import bowling.model.frame.Frame;
import bowling.model.frame.Frames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {
    List<Score> scores;

    private Scores(List<Score> scores) {
        this.scores = Collections.unmodifiableList(scores);
    }

    public static Scores of(Frames framesInstance) {
        List<Frame> frames = framesInstance.getFrames();
        List<Score> scores = new ArrayList<>();
        for (int i = 0; i < Frame.MAX_FRAME_INDEX; i++) {
            scores.add(getScoreOfFrame(frames, frames.get(i)));
        }
        return new Scores(scores);
    }

    private static Score getScoreOfFrame(List<Frame> frames, Frame target) {
        if (!target.isEnded()) {
            return Score.UNKNOWN;
        }

        Score score = Score.of(target.getTotalPinCount());
        if (target.getIndex() == Frame.MAX_FRAME_INDEX) {
            return score;
        }
        return score.add(ofBonus(frames, target));
    }

    private static Score ofBonus(List<Frame> frames, Frame target) {
        if (target.hasStrike()) {
            return ofStrike(frames, target);
        }

        if (target.hasSpare()) {
            return ofSpare(frames, target);
        }
        return Score.of(0);
    }

    private static Score ofStrike(List<Frame> frames, Frame target) {
        Frame nextFrame = frames.get(target.getIndex());
        Result nextResult = nextFrame.getResult(0);
        Result nextAfterResult = nextFrame.getResult(1);

        if (nextResult.isStrike() && target.getIndex() < Frame.MAX_FRAME_INDEX - 1) {
            Result nextAfterFrameResult = frames.get(target.getIndex() + 1).getResult(0);
            return Score.of(nextResult).add(Score.of(nextAfterFrameResult));
        }
        return Score.of(nextResult).add(Score.of(nextAfterResult));
    }

    private static Score ofSpare(List<Frame> frames, Frame target) {
        Frame nextFrame = frames.get(target.getIndex());
        Result nextResult = nextFrame.getResult(0);

        return Score.of(nextResult);
    }

    private void add(Score score) {
        scores.add(score);
    }

    public List<Score> getScores() {
        return scores;
    }
}
