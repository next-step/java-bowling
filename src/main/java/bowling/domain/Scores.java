package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {
    private static final int FIRST_SCORE_INDEX = 0;
    private static final int SECOND_SCORE_INDEX = 1;
    private static final int UNIT_SCORE_INDEX = 1;

    private final List<Score> scores;

    public Scores(List<Score> scores) {
        validateScore(scores);
        this.scores = scores;
    }

    private void validateScore(List<Score> scores) {
        if (scores == null) {
            throw new IllegalArgumentException("점수 리스트는 null 일 수 없습니다.");
        }
    }

    public List<Integer> accumulateScore() {
        int scoreSize = scores.size();
        if (scoreSize == 0) {
            return Collections.emptyList();
        }

        List<Integer> accumulateScore = new ArrayList<>();
        accumulateScore.add(scores.get(FIRST_SCORE_INDEX).score());

        for (int scoreIndex = SECOND_SCORE_INDEX; scoreIndex < scoreSize; scoreIndex++) {
            Integer previousScore = accumulateScore.get(accumulateScore.size() - UNIT_SCORE_INDEX);
            accumulateScore.add(scores.get(scoreIndex).score() + previousScore);
        }

        return accumulateScore;
    }
}