package bowling.domain;

import bowling.domain.ScoreUI.ScoreFactory;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class Bowling {

    private Frames frames;
    private BowlingResults bowlingResults = new BowlingResults();

    public Bowling() {
        frames = new Frames();
    }

    public int addPlayerScore(int frameCount, int inputScore) {

        Frame frame = frames.addFrameScore(frameCount, inputScore);
        bowlingResults.addScoreUI(frameCount, ScoreFactory.of(inputScore, frame));

        return frame.moveNextFrame();
    }

    public BowlingResult getBowLingResult(int resultCount) {
        return bowlingResults.getResult(resultCount);
    }
}
