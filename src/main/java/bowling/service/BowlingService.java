package bowling.service;

import bowling.domain.Score;
import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

public class BowlingService {

    private final Score score = new Score();
    private final List<Frame> frames = new ArrayList<>();

    public void pitch(int firstBall, int secondBall) {
        frames.add(new Frame(firstBall, secondBall));
    }

    public int totalScore() {
        for(Frame frame : frames) {
            score.plusScore(frame.score());
        }
        return score.totalScore();
    }
}
