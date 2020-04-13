package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scores {
    private static final int FRAME_MAX_SCORE = 10;
    private static final int FINAL_MAX_SCORE = 20;
    private static final int NORMAL_TRY_NUMBER = 2;
    private static final int FINAL_TRY_NUMBER = 3;
    private static final int ZERO = 0;
    private static final String STRIKE_SIGN = "X";
    private static final String SPARE_SIGN = "/";
    private static final String GUTTER_SIGN = "-";
    private List<Score> scores = new ArrayList<>();
    private List<String> signs = new ArrayList<>();


    public Scores() {
        this.scores = new ArrayList<>();
    }

    public void add(Score score) {
        this.scores.add(score);
        String sign = makeSign(score);
        signs.add(sign);
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

    private String makeSign(Score score) {
        String sign = "";
        if (score.getScore() == FRAME_MAX_SCORE) { //10개쳤을 경우 스트라이크표시 후 아래에서 보정작업진행( 0 / 10 같은경우)
            sign = STRIKE_SIGN;
        }
        if (isSpare()) {
            sign = SPARE_SIGN;
        }
        if (isFinalFrameSpare(score)) {
            sign = SPARE_SIGN;
        }
        if (score.getScore() == 0) {
            sign = GUTTER_SIGN;
        }
        if ("".equals(sign)) {
            sign = score.toString();
        }
        return sign;
    }

    private boolean isFinalFrameSpare(Score score) {
        // x,0,/    0,/,X  , 2,/,X
        return numberOfTry() == FINAL_TRY_NUMBER && sum() == FINAL_MAX_SCORE
                && scores.get(FINAL_TRY_NUMBER-2).getScore() + score.getScore() == FRAME_MAX_SCORE;
    }

    public int size() {
        return scores.size();
    }

    public int sum() {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    public int numberOfTry() {
        return (int) scores.stream()
                .count();
    }

    public boolean nextFrame() {
        return isStrike() || numberOfTry() == NORMAL_TRY_NUMBER;
    }

    private boolean isStrike() {
        return numberOfTry() == 1 && sum() == FRAME_MAX_SCORE;
    }

    private boolean isSpare() {
        return numberOfTry() == NORMAL_TRY_NUMBER && sum() == FRAME_MAX_SCORE;
    }

    private boolean isGutter() {
        return sum() == ZERO;
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

    public boolean isEndFinalFrame() {
        if (numberOfTry() == NORMAL_TRY_NUMBER && isMiss()) {
            return true;
        }
        if (numberOfTry() == FINAL_TRY_NUMBER) {
            return true;
        }
        return false;
    }


    public void checkBeforeAddFinal(int numberOfPin) {
        //처음에스트라이크가 아닌경우
        if (numberOfTry() == 1 && !isStrike() && sumUntilThisValue(numberOfPin) > FRAME_MAX_SCORE) {
            throw new IllegalArgumentException(FRAME_MAX_SCORE + "을 넘으면 안됩니다.");
        }
        //첫번째 스트라이크, 두번째
        if (numberOfTry() == 2 && sum() < FINAL_MAX_SCORE && sumUntilThisValue(numberOfPin) > FINAL_MAX_SCORE) {
            throw new IllegalArgumentException(FINAL_MAX_SCORE + "을 넘으면 안됩니다.");
        }
    }
}
