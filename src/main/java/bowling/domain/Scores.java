package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Scores {

    protected static final String STRIKE = "X";
    protected static final String GUTTER = "-";
    protected static final String SPARE = "/";

    private static final int MAX_SCORE_SIZE = 2;

    private List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    protected Scores(List<Score> scores) {
        this.scores = scores;
    }

    public void recordingScore(Score score) {
        if (scores.size() == MAX_SCORE_SIZE) {
            return;
        }

        scores.add(score);
    }

    @Override
    public String toString() {
        return convertToMark().toString();
    }

    protected StringJoiner convertToMark() {
        StringJoiner stringJoiner = new StringJoiner("|");
        if(isStrike()) {
            return stringJoiner.add(STRIKE);
        }

        if(isSpare()) {
            stringJoiner.add(convertToMark(scores.get(0)));
            stringJoiner.add(SPARE);
            return stringJoiner;
        }
        scores.stream().map(this::convertToMark).forEach(stringJoiner::add);
        return stringJoiner;
    }

    public boolean isStrike() {
        if (scores.isEmpty()) {
            return false;
        }
        return scores.get(0).isStrike();
    }

    public boolean isSpare() {
        if (scores.size() < 2 || isStrike()) {
            return false;
        }

        return scores.get(0).isSpare(scores.get(1));
    }

    public String convertToMark(Score score) {
        return score.score() == 0 ? GUTTER : String.valueOf(score.score());
    }

    public boolean isFirstPitching() {
        return scores.isEmpty();
    }

    public boolean isSecondPitching() {
        return scores.size() == 1;
    }
}
