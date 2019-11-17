package game;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Score {
    private static List<Score> scoreList = IntStream.rangeClosed(0, 10).boxed()
            .map(Score::new).collect(toList());
    private int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(int score) {
        try {
            return scoreList.get(score);
        } catch (Exception e) {
            throw new IllegalArgumentException("점수는 10 점 이상이 될 수 없습니다.");
        }
    }

    public int getScore() {
        return this.score;
    }
}
