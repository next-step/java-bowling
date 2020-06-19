package bowling.step4.domain.frame;

import bowling.step4.domain.Frames;
import bowling.step4.domain.Score;
import bowling.step4.domain.ScoresType;
import bowling.step4.domain.scores.FinalScores;
import bowling.step4.domain.scores.Scores;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class FinalFrame extends Frame {

    private FinalFrame(int frame, Scores scores) {
        super(frame, scores);
    }

    public static Frame of(int frame, Scores scores) {
        return new FinalFrame(frame, scores);
    }

    public static Frame init() {
        return of(Frames.LAST_FRAME, FinalScores.init());
    }

    public void createNextFrameOfScores(Scores scores) {
        this.scores = scores;
    }

    @Override
    public Frame getNextFrame() {
        return null;
    }

    @Override
    public boolean isFull() {
        if (ScoresType.STRIKE.of(scores) || ScoresType.SPARED.of(scores)) {
            return scores.stream().noneMatch(Objects::isNull);
        }
        return ScoresType.FULL.of(scores);
    }

    @Override
    protected int calculateScoreOfStrike() {
        FinalScores finalScores = (FinalScores) scores;
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