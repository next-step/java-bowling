package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Trial {
    private static final String STRIKE_SYMBOL = "X";
    private static final String SPARE_SYMBOL = "/";
    private static final String DELIMITER = "|";

    private static final int DOUBLE_STRIKE_NUMBER = 20;
    private static final int NON_MISS_NUMBER = 10;
    private static final int SECOND_TRIAL = 2;

    private final List<Score> scores = new ArrayList<>();
    private final StringBuilder scoreString = new StringBuilder();

    public void add(Score score) {
        scores.add(score);
        scoreString.append(isTotalHigherOrEqualThan(NON_MISS_NUMBER) ? getBonusScoreString() : score.getString())
                .append(DELIMITER);
    }

    public boolean isNormalEnd() {
        return isTotalHigherOrEqualThan(NON_MISS_NUMBER) || isSecondTrial();
    }

    public boolean isFinalEnd() {
        return isFullMiss() || isAfterSecond();
    }

    public String getScoreString() {
        if (scores.isEmpty()) {
            return "";
        }

        String raw = scoreString.toString();

        return raw.substring(0, raw.length() - 1);
    }

    public FrameResult getFrameResult() {
        if (isTotalHigherOrEqualThan(NON_MISS_NUMBER)) {
            return getSpareOrStrike();
        }

        return FrameResult.MISS;
    }

    private FrameResult getSpareOrStrike() {
        if (!isSecondTrial() || isDoubleStrike()) {
            return FrameResult.STRIKE;
        }

        return FrameResult.SPARE;
    }

    private boolean isDoubleStrike() {
        return isSecondTrial() && isTotalHigherOrEqualThan(DOUBLE_STRIKE_NUMBER);
    }

    private List<Integer> getNumbers() {
        return scores.stream()
                .map(Score::toInt)
                .collect(Collectors.toList());
    }

    private boolean isAfterSecond() {
        return scores.size() > SECOND_TRIAL;
    }

    private boolean isSecondTrial() {
        return scores.size() == SECOND_TRIAL;
    }

    private boolean isFullMiss() {
        return isSecondTrial() && getFrameResult().equals(FrameResult.MISS);
    }

    private boolean isTotalHigherOrEqualThan(int number) {
        int sum = getNumbers().stream()
                .reduce(Integer::sum)
                .orElse(0);

        return sum >= number;
    }

    private String getBonusScoreString() {
        if(getFrameResult().equals(FrameResult.STRIKE)) {
            return STRIKE_SYMBOL;
        }

        return SPARE_SYMBOL;
    }
}
