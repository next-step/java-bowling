package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 각 프레임에서 기록한 점수를 저장한다.
 */
public class Scores {
    private final List<Integer> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public int getTotalScore() {
        return scores.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public void add(int score) {
        int totalScore = getTotalScore();
        if (totalScore + score > 10) {
            throw new IllegalArgumentException();
        }
        this.scores.add(score);
    }
}
