package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scores {
    private static final int FRAME_MAX_SCORE = 10;
    private static final int FINAL_MAX_SCORE = 20;
    private static final int ZERO = 0;
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
        String sign = Sign.matchSign(this).getSign();
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

    public boolean nextFrame() {
        return isStrike() || isSecondTry();
    }

    public boolean isStrike() {
        return recentScore() == FRAME_MAX_SCORE && !isNormalFrameSpare() && !isFinalFrameSpare();
    }

    private boolean isMiss() {
        return sum() < FRAME_MAX_SCORE;
    }

    public void checkBeforeAddNormal(int numberOfPin) {
        if (sumUntilThisValue(numberOfPin) > FRAME_MAX_SCORE) {
            throw new IllegalArgumentException(FRAME_MAX_SCORE + "을 넘으면 안됩니다.");
        }
    }

    private int sumUntilThisValue(int numberOfPin) {
        return sum() + numberOfPin;
    }

    public boolean isEndGame() {
        if (isSecondTry() && isMiss()) {
            return true;
        }
        if (isFinalTry()) {
            return true;
        }
        return false;
    }

    public void checkBeforeAddFinal(int numberOfPin) {
        //처음에스트라이크가 아닌경우
        if (isFirstTry() && !isStrike() && sumUntilThisValue(numberOfPin) > FRAME_MAX_SCORE) {
            throw new IllegalArgumentException(FRAME_MAX_SCORE + "을 넘으면 안됩니다.");
        }
        //첫번째 스트라이크, 두번째
        if (isSecondTry() && sum() < FINAL_MAX_SCORE && sumUntilThisValue(numberOfPin) > FINAL_MAX_SCORE) {
            throw new IllegalArgumentException(FINAL_MAX_SCORE + "을 넘으면 안됩니다.");
        }
    }

    private boolean isFirstTry() {
        return numberOfTry() == FIRST_TRY_NUMBER;
    }

    private boolean isSecondTry() {
        return numberOfTry() == SECOND_TRY_NUMBER;
    }

    private boolean isFinalTry() {
        return numberOfTry() == FINAL_TRY_NUMBER;
    }

    public int firstScore() {
        return scores.get(0).getScore();
    }

    public int secondScore() {
        return scores.get(1).getScore();
    }

    public int recentScore() {
        return scores.get(numberOfTry() - 1).getScore();
    }

    public boolean isGutter() {
        return recentScore() == ZERO;
    }

    public boolean isNormalFrameSpare() {
        return numberOfTry() == SECOND_TRY_NUMBER && sum() == FRAME_MAX_SCORE;
    }

    public boolean isFinalFrameSpare() {
        return numberOfTry() == FINAL_TRY_NUMBER && sum() == FINAL_MAX_SCORE && recentScore() != FRAME_MAX_SCORE;
    }
}
