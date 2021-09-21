package bowling.domain.frame;

import bowling.domain.score.NormalScore;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NormalFrame extends Frame {

    private static final int FIRST_IDX = 1;

    private static final int LAST = 9;

    private final int index;

    private NormalScore score;

    private int totalScore;

    private final Frame prevFrame;

    private Frame nextFrame;

    private NormalFrame(int index, NormalScore score, int trial, Frame prevFrame, Frame nextFrame) {
        super(trial);
        this.index = index;
        this.score = score;
        this.totalScore = -1;
        this.prevFrame = prevFrame;
        this.nextFrame = nextFrame;
    }

    public static NormalFrame start(int score) {
        return of(FIRST_IDX, NormalScore.first(score), FIRST_TRIAL);
    }

    protected static NormalFrame of(int index, NormalScore score, int trial) {
        return new NormalFrame(index, score, trial, null, null);
    }

    protected static NormalFrame of(int index, NormalScore score, int trial, Frame prevFrame, Frame nextFrame) {
        return new NormalFrame(index, score, trial, prevFrame, nextFrame);
    }

    public Score score() {
        return score;
    }

    @Override
    public int next() {
        if (isNowFirstTry() && !this.score.isStrike()) {
            return index;
        }
        return index + 1;
    }

    @Override
    public List<Integer> getAllScores() {
        return score.getAll();
    }

    @Override
    public int calculateScore() {

        if (hasTotalScore()) {
            return totalScore;
        }

        int baseScore = baseScoreFromPrev();

        if (noSet(baseScore)) {
            return NONE_SCORE;
        }

        calculateWith(baseScore);

        return totalScore;
    }

    private void calculateWith(int baseScore) {
        if (endsWithNeitherSpareNorStrike()) {
            totalScore = baseScore + score.getFirst() + score.getSecond();
        }
        calculateWhenSpareOrStrike(baseScore);
    }

    private void calculateWhenSpareOrStrike(int baseScore) {
        if (nextFrame == null) {
            return;
        }

        calculateTotalScoreIfSpare(baseScore);
        calculateTotalScoreIfStrike(baseScore);
    }

    private void calculateTotalScoreIfSpare(int baseScore) {
        if (!score.isSpare()) {
            return;
        }
        totalScore = baseScore + nextFrame.addWithFirstScore(score.sum());
    }

    private void calculateTotalScoreIfStrike(int baseScore) {
        if (!score.isStrike()) {
            return;
        }
        calculateTotalScoreIfNextScoresMoreThanTwo(baseScore, nextFramesScores());
    }

    private void calculateTotalScoreIfNextScoresMoreThanTwo(int baseScore, List<Integer> nextScores) {
        if (nextScores.size() > 1) {
            totalScore = baseScore + score.getFirst() + nextScores.get(0) + nextScores.get(1);
        }
    }

    private List<Integer> nextFramesScores() {

        List<Integer> nextFrameAllScores = nextFrame.getAllScores();

        List<Integer> nextNextFrameAllScores =
                Optional.ofNullable(this.nextFrame.getNextFrame()).map(Frame::getAllScores).orElse(new ArrayList<>());

        return Stream.concat(nextFrameAllScores.stream(), nextNextFrameAllScores.stream())
                .filter(score -> score != NONE_SCORE)
                .collect(Collectors.toList());
    }

    private boolean noSet(int baseScore) {
        return baseScore == NONE_SCORE;
    }

    private boolean endsWithNeitherSpareNorStrike() {
        return !score.isSpare() && !score.isStrike() && score.isDone();
    }

    private boolean hasTotalScore() {
        return totalScore != NONE_SCORE;
    }

    private int baseScoreFromPrev() {
        int baseScore = 0;

        if (hasPrevFrame()) {
            baseScore = prevFrame.getTotalScore();
        }

        return baseScore;

    }

    private boolean hasPrevFrame() {
        return prevFrame != null;
    }

    @Override
    public boolean isLast() {
        if (index == LAST) {
            return score.isStrike() || isNowSecondTry();
        }
        return index > LAST;
    }

    @Override
    protected int addWithFirstScore(int score) {
        return this.score.getFirst() + score;
    }

    @Override
    protected int getTotalScore() {
        return totalScore;
    }

    @Override
    protected Frame getNextFrame() {
        return nextFrame;
    }

    @Override
    public Frame tryNext(int score) {
        if (isNowFirstTry() && !this.score.isStrike()) {
            return trySecond(score);
        }
        return tryFirst(index + 1, score);
    }

    private NormalFrame tryFirst(int index, int score) {
        NormalFrame nextFrame =
                of(index, NormalScore.first(score), FIRST_TRIAL, this, null);
        this.nextFrame = nextFrame;
        return nextFrame;
    }

    private NormalFrame trySecond(int score) {
        this.score = this.score.second(score);
        this.trial = SECOND_TRIAL;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index && trial == that.trial && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, score, trial);
    }
}
