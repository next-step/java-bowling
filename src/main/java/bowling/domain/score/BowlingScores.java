package bowling.domain.score;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

import static bowling.utils.BowlingConstants.ONE;

public class BowlingScores {

    private List<Integer> frameScore;

    public BowlingScores (List<Frame> frames) {

        frameScore = new ArrayList<>();

        for(Frame frame : frames) {
            addScore(frame.calculateScore().getScore());
        }
    }

    public void addScore(int score) {
        int prevScore = 0;

        if(!frameScore.isEmpty()) {
            prevScore = frameScore.get(frameScore.size() - ONE);
        }

        frameScore.add(score + prevScore);
    }

    @Override
    public String toString() {
        return frameScore.toString();
    }
}
