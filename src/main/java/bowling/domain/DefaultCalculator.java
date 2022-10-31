package bowling.domain;

import java.util.List;

public class DefaultCalculator implements ScoreCalculator {
    @Override
    public int calculate(List<BowlingRound> rounds) {
        if (rounds.size() != 1) {
            throw new IllegalStateException("스패어,스트라이크가 아닌 경우, 한 라운드만 점수산출에 사용됩니다.");
        }
        BowlingRound bowlingRound = rounds.get(0);
        return bowlingRound.sumScores();
    }

}
