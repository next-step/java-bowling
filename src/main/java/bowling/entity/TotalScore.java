package bowling.entity;

import bowling.entity.score.CalculateImPossibleException;
import bowling.entity.score.ScoreType;
import bowling.entity.score.Spare;
import bowling.entity.score.Strike;

import java.util.LinkedList;
import java.util.List;

public class TotalScore {
    private final LinkedList<ScoreType> scoreTypes;
    private LinkedList<Integer> scoreResults;

    public TotalScore() {
        this.scoreTypes = new LinkedList<>();
        this.scoreResults = new LinkedList<>();
    }

    public void addScore(List<ScoreType> scoreTypes) {
        this.scoreTypes.addAll(scoreTypes);
        setScores();
    }

    public void setScores() {
        Score score = null;
        scoreResults = new LinkedList<>();
        int sum = 0;
        int size = scoreTypes.size();

        for (int i = 0; i < size; i++) {

            score = getScore(score, i);

            sum = sumScore(score, sum);

            score = scoreCalculateEnd(score);

            i = remainAndLoopEndCheck(score, size, i);
        }
    }

    private Score getScore(Score score, int i) {
        ScoreType scoreType = scoreTypes.get(i);

        if (scoreType.isFrameEnd()) {
            score = calculateScore(scoreType.frameScore(), i);
        }

        return score;
    }

    private boolean isScoreResultsAddPossible(Score score) {
        return (score != null) && (score.calculatePossible());
    }

    private int sumScore(Score score, int sum) {
        if (isScoreResultsAddPossible(score)) {
            sum += score.score();
            lastFrameResultDelete();
            scoreResults.add(sum);
        }

        return sum;
    }

    private Score scoreCalculateEnd(Score score) {
        if (isScoreResultsAddPossible(score)) {
            score = score.calculateEnd();
        }
        return score;
    }

    private void lastFrameResultDelete() {
        if (scoreResults.size() == 10) {
            scoreResults.removeLast();
        }
    }

    private Score calculateScore(Score frameScore, int index) {
        int nextIndex = index + 1;
        if (nextIndex >= scoreTypes.size() || frameScore.calculatePossible()) {
            return frameScore;
        }

        frameScore = frameScore.calculate(scoreTypes.get(nextIndex).score().score());

        if (!frameScore.calculatePossible()) {
            return calculateScore(frameScore, nextIndex);
        }

        return frameScore;
    }

    private int remainAndLoopEndCheck(Score score, int size, int i) {
        if ((score != null) && (score.remain() > 0)) {
            i = size - 1;
        }
        return i;
    }

    public int getFrameScoreResults(int frameNo) {
        if (scoreResults.size() > frameNo - 1) {
            return scoreResults.get(frameNo - 1);
        }
        throw new CalculateImPossibleException("아직 계산이 끝나지 않은 프레임 입니다.");
    }
}
