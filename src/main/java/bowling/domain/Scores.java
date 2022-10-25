package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Scores {

    private static final int NORMAL_ROUND_TRY_NUM = 2;
    private static final int LAST_ROUND_TRY_NUM = 3;

    private final List<Score> scores =  new ArrayList<>();

    public void add(Integer pins) {
        scores.add(new Score(pins));
    }

    public boolean isNormalRoundEnd() {
        return scores.size() == NORMAL_ROUND_TRY_NUM || getSum().isStrike();
    }

    public boolean isLastRoundEnd(){
        return scores.size() == LAST_ROUND_TRY_NUM;
    }

    private Score getSum(){
        return scores.stream()
                .reduce(Score::sum)
                .orElse(new Score(0));
    }

    public List<Score> getScores() {
        return scores;
    }

    public boolean isMiss(){
        return scores.size() == 2 && !getSum().isStrike();
    }

    public boolean isSpare(){
        return scores.size() == 2 && getSum().isStrike();
    }

    public boolean isStrike(){
        return scores.size() == 1 && scores.get(0).isStrike();
    }


}
