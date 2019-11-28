package bowling.view;

import bowling.domain.GameRecord;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.List;

public class ScoreCollection {

    private List<ScoreDto> scores = new ArrayList<>();

    public ScoreCollection(GameRecord gameRecord) {
        addNormalFrameScores(gameRecord.getNormalFrames());
        addFinalFrameScores(gameRecord.getFinalFrame());
    }

    private void addNormalFrameScores(List<NormalFrame> normalFrames) {
        int score = 0;
        for (NormalFrame frame : normalFrames) {
            score += frame.getScore();
            scores.add(new ScoreDto(score));
        }
    }

    private int getCurrentScore() {
        return scores.get(scores.size() - 1).getScore();
    }

    private void addFinalFrameScores(FinalFrame finalFrame) {
        int score = getCurrentScore();
        if (finalFrame.getStatus().getFirstCountOfPin() != 0) {
            score += finalFrame.getStatus().getFirstCountOfPin() +
                    finalFrame.getStatus().getSecondCountOfPin() +
                    finalFrame.getStatus().getThirdCountOfPin();
            scores.add(new ScoreDto(score));
        }
    }

    public List<ScoreDto> getScores() {
        return scores;
    }
}
