package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class ScoreResult {

    private final List<Integer> scores = new ArrayList<>();


    public void addScore(Integer score) {
        scores.add(score);
    }

    public List<Integer> getScores() {
        return new ArrayList<>(scores);
    }

    public List<Integer> sum() {
        List<Integer> result = new ArrayList<>();
        Integer sum = 0;
        for (Integer score : scores) {
            sum += score;
            result.add(sum);
        }
        return result;
    }

}
