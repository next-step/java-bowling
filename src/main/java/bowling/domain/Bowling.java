package bowling.domain;

import bowling.domain.ScoreUI.ScoreFactory;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class Bowling {

    private Frames frames;
    private BowlingResults bowlingResults = new BowlingResults();
    private int frameCount = 0;

    public Bowling() {
        frames = new Frames();
    }

    public void addPlayerScore(int inputScore) {
        Frame frame = frames.addFrameScore(frameCount, inputScore);
        bowlingResults.addScoreUI(frameCount, ScoreFactory.of(inputScore, frame));
        frameCount += (frame.validateLimitScore()) ? 1 : 0;
    }

    public BowlingResult getBowLingResult(int resultCount) {
        return bowlingResults.getResult(resultCount);
    }
}
