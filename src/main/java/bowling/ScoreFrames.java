package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScoreFrames {

    private final ScoreFrame firstScoreFrame;
    private ScoreFrame lastScoreFrame;

    public ScoreFrames() {
        this.firstScoreFrame = new NormalScoreFrame(Turn.ofStart());
    }

    public void bowl(int score) {
        if (isBeforeStart()) {
            lastScoreFrame = firstScoreFrame;
        }

        updateLastScoreFrame(lastScoreFrame.addScore(score));
    }

    public List<String> getCalculatedScores() {
        List<String> calculatedScores = new ArrayList<>();

        ScoreFrame currentScoreFrame = firstScoreFrame;
        Score previousScore = Score.ofZero();
        while (currentScoreFrame.isCalculable()) {
            previousScore = currentScoreFrame.getScore(previousScore);
            calculatedScores.add(previousScore.getScoreString());
            currentScoreFrame = currentScoreFrame.getNextFrame();
        }

        return calculatedScores;
    }

    public List<String> getScoreStrings() {
        List<String> scoreStringList = new ArrayList<>();

        ScoreFrame currentScoreFrame = firstScoreFrame;
        while (currentScoreFrame != null) {
            scoreStringList.add(currentScoreFrame.getScoreString());
            currentScoreFrame = currentScoreFrame.getNextFrame();
        }

        return scoreStringList;
    }

    public int getCurrentTurn() {
        if (isBeforeStart()) {
            return Turn.ofStart().getNumber();
        }

        return lastScoreFrame.getTurnNumber();
    }

    public boolean isContinued() {
        return isBeforeStart() || lastScoreFrame.isContinued();
    }

    private void updateLastScoreFrame(ScoreFrame nextScoreFrame) {
        if (lastScoreFrame != nextScoreFrame) {
            lastScoreFrame = nextScoreFrame;
        }
    }

    private boolean isBeforeStart() {
        return !Optional.ofNullable(lastScoreFrame).isPresent();
    }
}
