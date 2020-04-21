package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scores {
    private static final int FRAME_MAX_SCORE = 10;
    private static final int FINAL_MAX_SCORE = 20;
    private static final int FIRST_TRY_NUMBER = 1;
    private static final int SECOND_TRY_NUMBER = 2;
    private static final int FINAL_TRY_NUMBER = 3;
    private static final String EMPTY = "";
    private List<Score> scores;
    private List<String> signs = new ArrayList<>();

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public void add(int numberOfPin) {
        add(new Score(numberOfPin));
    }

    public void add(Score score) {
        this.scores.add(score);
        String sign = Sign.matchSign(score.getScore(), numberOfTry(), sum()).getSign();
        if (EMPTY.equals(sign)) {
            sign = score.toString();
        }
        this.signs.add(sign);
    }

    @Override
    public String toString() {
        return "Scores{" +
                "scores=" + scores +
                ", signs=" + signs +
                '}';
    }

    public String getSigns() {
        return this.signs.stream()
                .map(Object::toString)
                .collect(Collectors.joining("|"));
    }

    public int countOfSign(Sign sign) {
        return (int)this.signs.stream()
                .filter(n->n.equals(sign.getSign()))
                .count();
    }

    public int sum() {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    public int numberOfTry() {
        return scores.size();
    }

    private boolean isMiss() {
        return sum() < FRAME_MAX_SCORE;
    }

    public void checkBeforeAddNormal(Score numberOfPin) {
        if (sumUntilThisValue(numberOfPin) > FRAME_MAX_SCORE) {
            throw new IllegalArgumentException(FRAME_MAX_SCORE + "을 넘으면 안됩니다.");
        }
    }

    private int sumUntilThisValue(Score numberOfPin) {
        return sum() + numberOfPin.getScore();
    }

    public boolean isEndGame() {
        if (isTry(SECOND_TRY_NUMBER) && isMiss()) {
            return true;
        }
        if (isTry(FINAL_TRY_NUMBER)) {
            return true;
        }
        return false;
    }

    public void checkBeforeAddFinal(Score numberOfPin) {
        //처음에스트라이크가 아닌경우
        if (isTry(FIRST_TRY_NUMBER) && isMiss() && sumUntilThisValue(numberOfPin) > FRAME_MAX_SCORE) {
            throw new IllegalArgumentException(FRAME_MAX_SCORE + "을 넘으면 안됩니다.");
        }
        //첫번째 스트라이크, 두번째
        if (isTry(SECOND_TRY_NUMBER) && sum() < FINAL_MAX_SCORE && sumUntilThisValue(numberOfPin) > FINAL_MAX_SCORE) {
            throw new IllegalArgumentException(FINAL_MAX_SCORE + "을 넘으면 안됩니다.");
        }
    }

    private boolean isTry(int number) {
        return numberOfTry() == number;
    }

    public int firstScore() {
        return scores.get(0).getScore();
    }

    public int secondScore() {
        return scores.get(1).getScore();
    }
}
