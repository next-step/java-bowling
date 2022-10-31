package bowling.domain;

import java.util.List;

public class SpareCalculator implements ScoreCalculator {

    @Override
    public int calculate(List<BowlingRound> rounds) {
        if (rounds.size() != 2) {
            throw new IllegalStateException("스패어 라운드는 다음 투구의 점수를 보너스점수로 받아야합니다.");
        }
        BowlingRound firstRound = rounds.get(0);
        BowlingRound secondRound = rounds.get(1);
        int originScore = firstRound.sumScores();
        Score bonusScore = secondRound.getFirstScore();
        return bonusScore.sum(originScore);
    }
}
