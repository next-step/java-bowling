package bowling.domain.score;

import bowling.domain.score.symbol.Digit;
import bowling.domain.score.symbol.Miss;
import bowling.domain.score.symbol.Spare;
import bowling.domain.score.symbol.Strike;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FrameScore {
    private List<Score> scoreHistory;

    public FrameScore() {
        scoreHistory = new ArrayList<>();
    }

    public void addScore(int score) {
        if (isSpare(score)) {
            scoreHistory.add(new Score(score, new Spare()));
            return;
        }
        if (score == 10) {
            scoreHistory.add(new Score(10, new Strike()));
            return;
        }
        if (score == 0) {
            scoreHistory.add(new Score(0, new Miss()));
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
