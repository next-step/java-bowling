package bowling.domain;

import java.util.List;

public class StrikeCalculator implements ScoreCalculator {
    @Override
    public int calculate(List<BowlingRound> rounds) {
        if (rounds.size() < 2) {
            throw new IllegalStateException("스트라이크 라운드는 다음 2번쨰 투구의 점수를 보너스점수로 받아야합니다.");
        }
        BowlingRound firstRound = rounds.get(0);
        BowlingRound secondRound = rounds.get(1);
        int originScore = firstRound.sumScores();
        if(secondRound.containsStrike()){
            BowlingRound thirdRound = rounds.get(2);
            Score bonusFirst = secondRound.getFirstScore();
            Score bonusSecond = thirdRound.getFirstScore();
            return getSum(getSum(originScore, bonusFirst),bonusSecond);
        };
        return originScore + secondRound.sumScores();
    }

    private int getSum(int originScore, Score bonusFirst) {
        return bonusFirst.sum(originScore);
    }
}
