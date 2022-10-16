package bowling.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Scores {
    private LinkedList<Score> values;
    private static final int MAX_TIMES = 2;
    private static final int MAX_SCORE = 10;

    private Scores(LinkedList<Score> values) {
        this.values = values;
    }

    public static Scores of(Score score) {
        LinkedList<Score> scores = new LinkedList<>();
        scores.add(score);
        return new Scores(scores);
    }

    public void bowl(Score score) {
        if (isFinished()) {
            throw new IllegalStateException("턴이 종료되었습니다.");
        }
        if (getSum() + score.getValue() > MAX_SCORE) {
            throw new IllegalArgumentException();
        }
        values.add(score);
    }

    public boolean isSpare() {
        return isFinished() && getSum() == MAX_SCORE;
    }

    public boolean isMiss() {
        return isFinished() && getSum() < MAX_SCORE;
    }

    private int getSum() {
        return values.stream().mapToInt(Score::getValue).sum();
    }

    public List<Integer> getRecord(){
        return values.stream().map(Score::getValue).collect(Collectors.toUnmodifiableList());
    }

    public int getRemainPins(){
        return MAX_SCORE - getSum();
    }

    private boolean isFinished(){
        return values.size() == MAX_TIMES || values.stream().anyMatch(Score::isStrike);
    }
}
