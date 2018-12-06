package bowling.domain.score;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

import static bowling.utils.BowlingConstants.ONE;
import static bowling.utils.BowlingConstants.ZERO;

public class BowlingScores {

    private List<Integer> frameScore ;

    public BowlingScores (List<Frame> frames) {
        frameScore = new ArrayList<>();

        frames.stream()
                .mapToInt(frame -> frame.calculateScore().getScore())
                .forEach(this::addScore);
    }

    void addScore(int score) {
        int prevScore = 0;

        if(!frameScore.isEmpty()) {
            prevScore = frameScore.get(frameScore.size() - ONE);
        }

        if(score >= ZERO)
            frameScore.add(score + prevScore);
    }

    public List<Integer> getFrameScore() {
        return frameScore;
    }
}
