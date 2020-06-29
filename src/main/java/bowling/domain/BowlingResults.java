package bowling.domain;

import bowling.domain.ScoreUI.ScoreFactory;
import bowling.domain.frame.Frames;

import java.util.ArrayList;
import java.util.List;


public class BowlingResults {

    private List<BowlingResult> bowlingResults = new ArrayList<>();

    public BowlingResults() {
        for(int i = 0; i <= Frames.BOWLING_GAME_FRAME; i++) {
            bowlingResults.add(new BowlingResult());
        }
    }

    public void addScoreUI(int frameCount, ScoreFactory scoreFactory) {
        bowlingResults.get(frameCount).addScoreUI(scoreFactory);
    }

    public BowlingResult getResult(int frameCount) {
        return bowlingResults.get(frameCount);
    }

}
