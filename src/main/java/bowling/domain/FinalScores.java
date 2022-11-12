package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class FinalScores extends Scores {

    private static final int MAX_SCORE_SIZE = 3;

    private List<Score> scores;

    public FinalScores() {
        this(new ArrayList<>());
    }

    private FinalScores(List<Score> scores) {
        super(scores);
        this.scores = scores;
    }

    @Override
    public void recordingScore(Score score) {
        if (scores.size() == MAX_SCORE_SIZE) {
            throw new IllegalArgumentException();
        }

        scores.add(score);
    }

    @Override
    public String toString() {
        StringJoiner markJoiner = convertToMark();
        if (bonusScoreExist()) {
            markJoiner.add(scores.get(2).isStrike() ? STRIKE : convertToMark(scores.get(2)));
        }

        if (isStrike()) {
            markJoiner.add(scores.get(1).isStrike() ? STRIKE : convertToMark(scores.get(1)));
        }

        return markJoiner.toString();
    }

    public boolean isBonusPitching() {
        return scores.size() == 2;
    }

    public boolean bonusScoreExist() {
        return scores.size() == 3;
    }
}
