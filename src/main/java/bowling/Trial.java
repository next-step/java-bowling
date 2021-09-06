package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Trial {
    private static final String STRIKE_SYMBOL = "X";
    private static final String SPARE_SYMBOL = "/";

    private static final int NON_MISS_NUMBER = 10;
    private static final int SECOND_TRIAL = 2;

    private final List<Score> scores = new ArrayList<>();
    private final StringBuilder scoreString = new StringBuilder();

    public void add(Score score) {
        scores.add(score);
        scoreString.append(isMaxScore() ? getBonusScoreString() : score.getString())
                .append("|");

        // Todo : 마지막 Frame 트리플 스트라이크가 제대로 표시되지 않는 부분 수정
    }

    public boolean isNormalEnd() {
        return isMaxScore() || isSecond();
    }

    public boolean isFinalEnd() {
        return isFullMiss() || isAfterSecond();
    }

    public FrameResult getFrameResult() {
        if (isMaxScore()) {
            return getSpareOrStrike();
        }

        return FrameResult.MISS;
    }

    public String getScoreString() {
        if (scores.size() == 0) {
            return "";
        }

        String raw = scoreString.toString();

        return raw.substring(0, raw.length() - 1);
    }

    private List<Integer> getNumbers() {
        return scores.stream()
                .map(Score::toInt)
                .collect(Collectors.toList());
    }

    private boolean isAfterSecond() {
        return scores.size() > SECOND_TRIAL;
    }

    private boolean isFullMiss() {
        return scores.size() == SECOND_TRIAL &&
                getFrameResult() == FrameResult.MISS;
    }

    private boolean isSecond() {
        return scores.size() == SECOND_TRIAL;
    }

    private boolean isMaxScore() {
        int sum = getNumbers().stream()
                .reduce(Integer::sum)
                .orElse(0);

        return sum >= NON_MISS_NUMBER;
    }

    private String getBonusScoreString() {
        if(getFrameResult() == FrameResult.STRIKE) {
            return STRIKE_SYMBOL;
        }

        return SPARE_SYMBOL;
    }

    private FrameResult getSpareOrStrike() {
        if (isSecond()) {
            return FrameResult.SPARE;
        }

        return FrameResult.STRIKE;
    }
}
