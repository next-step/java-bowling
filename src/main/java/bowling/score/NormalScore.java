package bowling.score;

import bowling.score.rollling.Rolling;

import java.util.ArrayList;
import java.util.List;

import static bowling.score.rollling.Pin.MAX_PIN_NUMBER;

public class NormalScore implements Score {
    private static final String SUM_OF_PIN_COUNT_EXCEPTION = "점수들의 합은 10을 넘길 수 없습니다.";
    private List<Rolling> rollings;

    public NormalScore() {
        this.rollings = new ArrayList<>();
    }

    public void addScore(int score) {
        if (sumScore() + score > MAX_PIN_NUMBER) {
            throw new IllegalArgumentException(SUM_OF_PIN_COUNT_EXCEPTION);
        }
        this.rollings.add(Rolling.of(score));
    }

    public int size() {
        return rollings.size();
    }

    @Override
    public List<Rolling> getRollings() {
        return rollings;
    }

    @Override
    public int sumScore() {
        return rollings.stream()
                .map(Rolling::getScore)
                .reduce(Integer::sum).orElse(0);
    }
}
