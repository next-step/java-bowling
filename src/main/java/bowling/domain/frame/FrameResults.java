package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

import static bowling.Constants.CAN_NOT_CALCULATE_SCORE;

public class FrameResults {

    private final List<Frame> frames;

    public FrameResults(List<Frame> frames) {
        this.frames = frames;
    }

    public List<Integer> getScores() {
        List<Integer> scores = new ArrayList<>();

        for(Frame frame : frames) {
            addScore(scores, frame);
        }

        return scores;
    }

    private void addScore(List<Integer> scores, Frame frame) {
        int score = frame.getScore();

        if(score == CAN_NOT_CALCULATE_SCORE) {
            return ;
        }

        if (scores.isEmpty()) {
            scores.add(score);
            return;
        }

        int nextScore = scores.get(scores.size() - 1) + score;
        scores.add(nextScore);
    }

    public List<Frame> getValue() {
        return new ArrayList<>(frames);
    }
}
