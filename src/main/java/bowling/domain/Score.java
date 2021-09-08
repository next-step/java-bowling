package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Score {
    private static final int MAX_PIN_NUMBER = 10;
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int SCORES_MAX_INDEX = 2;

    private List<Integer> scores = new ArrayList<>();

    public Score(int firstBall) {
        scores.add(firstBall);
    }

    public void secondBall(int second) {
        if (scores.get(FIRST_INDEX) + second > MAX_PIN_NUMBER) {
            throw new IllegalArgumentException("핀의 최고 갯수는 10개 입니다.");
        }

        scores.add(SECOND_INDEX, second);
    }

    public int firstScore() {
        return scores.get(FIRST_INDEX);
    }

    public int secondScore() {
        if (scores.size() < SCORES_MAX_INDEX) {
            throw new IllegalArgumentException("두번째 점수 입력해주세요.");
        }

        return scores.get(SECOND_INDEX);
    }

    public List<Integer> scores() {
        return scores;
    }
}
