package bowling.domain.score;

import bowling.domain.score.symbol.Digit;
import bowling.domain.score.symbol.Gutter;
import bowling.domain.score.symbol.Spare;
import bowling.domain.score.symbol.Strike;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FrameScore {
    private static final int STRIKE_SCORE = 10;
    private static final int GUTTER_SCORE = 0;

    private List<Score> scoreHistory;

    public FrameScore() {
        scoreHistory = new ArrayList<>();
    }

    public void addScore(int score) {
        if (isSpare(score)) {
            scoreHistory.add(new Score(score, new Spare()));
            return;
        }
        if (score == STRIKE_SCORE) {
            scoreHistory.add(new Score(STRIKE_SCORE, new Strike()));
            return;
        }
        if (score == GUTTER_SCORE) {
            scoreHistory.add(new Score(GUTTER_SCORE, new Gutter()));
            return;
        }
        scoreHistory.add(new Score(score, new Digit(score)));
    }

    private boolean isSpare(int score) {
        int prevIndex = scoreHistory.size() - 1;
        if (prevIndex >= 0) {
            Score prevScore = scoreHistory.get(prevIndex);
            return prevScore.checkIfSpare(score);
        }
        return false;
    }

    public List<String> getFormattedScores() {
        return scoreHistory.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
