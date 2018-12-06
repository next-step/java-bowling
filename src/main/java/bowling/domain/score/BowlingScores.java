package bowling.domain.score;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

import static bowling.utils.BowlingConstants.INITIAL_SCORE;
import static bowling.utils.BowlingConstants.ONE;

public class BowlingScores {

    private List<Integer> frameScore ;

    public BowlingScores (List<Frame> frames) {
        frameScore = new ArrayList<>();

        frames.stream()
                .mapToInt(frame -> Score.scoreForFrame(frame.calculateScore()))
                .forEach(this::addScore);
    }

    void addScore(int score) {
        int prevScore = 0;

        if(!frameScore.isEmpty()) {
            prevScore = frameScore.get(frameScore.size() - ONE);
        }

        if(score != INITIAL_SCORE)
            frameScore.add(score + prevScore);
    }

    public List<Integer> getFrameScore() {
        return frameScore;
    }
}
