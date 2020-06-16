package bowling.step3.domain.frame;

import bowling.step3.domain.Score;
import bowling.step3.domain.scores.FinalScores;
import bowling.step3.domain.scores.Scores;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FinalFrame extends Frame {

    private FinalFrame(int frame, Scores scores) {
        super(frame, scores);
    }

    public static Frame of(int frame, Scores scores) {
        return new FinalFrame(frame, scores);
    }

    public void createNextFrame (Scores scores) {
        this.scores = scores;
    }

    @Override
    public Frame getNextFrame() {
        return null;
    }

    @Override
    protected int calculateScoreOfStrike() {
        FinalScores finalScores = (FinalScores)scores;
        if (!finalScores.filledBonus()) {
            return EMPTY_CALC;
        }
        return scores.stream()
                     .reduce(0, (total, score) -> total + score.getValue(), Integer::sum);
    }

    @Override
    protected int calculateScoreOfTwoStrike(int totalScore) {
        List<Score> scoreList = scores.stream().collect(toList());
        Score firstScore = scoreList.get(0);
        Score secondScore = scoreList.get(1);
        if (firstScore == null || secondScore == null) {
            return EMPTY_CALC;
        }
        return totalScore + firstScore.getValue() + secondScore.getValue();
    }

    @Override
    protected int calculateScoreOfSpared() {
        return calculateScoreOfStrike();
    }
}