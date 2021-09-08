package bowling.domain.score;

import static bowling.domain.frame.Frame.*;
import static bowling.domain.frame.Frames.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import bowling.domain.frame.Frames;

public class PureScores {
    private static final int MAX_SCORE_SIZE = 21;
    private static final String SCORE_DATA_MUST_NOT_BE_NULL_EXCEPTION_STATEMENT = "점수 데이터 리스트는 null이 될 수 없습니다";
    private static final String OUT_OF_COUNT_OF_THROW_BALL_EXCEPTION_STATEMENT = "투구 횟수의 범위를 벗어났습니다";

    private final List<Integer> scores;
    private int lastCalculatedIndex;

    private PureScores(List<Integer> scores, int lastCalculatedIndex) {
        validate(scores, lastCalculatedIndex);

        this.lastCalculatedIndex = lastCalculatedIndex;
        this.scores = new ArrayList<>();
    }

    public static PureScores from(List<Integer> scores, int lastCalculatedIndex) {
        return new PureScores(scores, lastCalculatedIndex);
    }

    private void validate(final List<Integer> scores, final int lastCalculatedIndex) {
        if (Objects.isNull(scores)) {
            throw new IllegalArgumentException(SCORE_DATA_MUST_NOT_BE_NULL_EXCEPTION_STATEMENT);
        }

        if (lastCalculatedIndex < 0 || lastCalculatedIndex > MAX_SCORE_SIZE - 1) {
            throw new IllegalArgumentException(OUT_OF_COUNT_OF_THROW_BALL_EXCEPTION_STATEMENT);
        }
    }

    public void initFrameAsScore(final Frames frames, final int index, List<Integer> cumulativeScores) {
        List<Score> scores = frames.frames().get(index).scores();
        IntStream.range(0, scores.size())
            .forEach(i -> {
                this.scores.add(scores.get(i).score());
                getCumulativeScores(frames, cumulativeScores);
            });
    }

    public List<Integer> getCumulativeScores(Frames frames, List<Integer> cumulativeScores) {
        if (scores.size() == 0 || scores.size() <= lastCalculatedIndex) {
            return cumulativeScores;
        }

        if (scores.get(lastCalculatedIndex) == 10 && (lastCalculatedIndex + 2) >= scores.size()) {
            return cumulativeScores;
        }

        calculateScores(cumulativeScores);
        if (frames.isFinish() && cumulativeScores.size() != TOTAL_FRAME_NUMBER) {
            calculateScores(cumulativeScores);
        }

        return cumulativeScores;
    }

    private void calculateScores(List<Integer> cumulativeScores) {
        if (calculateScoreIfStrike(cumulativeScores)) {
            return;
        }

        if (calculateScoreIfSpare(cumulativeScores)) {
            return;
        }

        calculateScoreIfMiss(cumulativeScores);
    }

    private int addAndGetScores(int index) {
        return scores.get(index) + scores.get(index + 1);
    }

    private boolean calculateScoreIfStrike(List<Integer> cumulativeScores) {
        if ((lastCalculatedIndex + 2) < scores.size() && scores.get(lastCalculatedIndex) == TEN_SCORE.score()) {
            cumulativeScores.add(TEN_SCORE.score() + addAndGetScores(lastCalculatedIndex + 1));
            updateScoreBoard(cumulativeScores);
            lastCalculatedIndex++;
            return true;
        }
        return false;
    }

    private boolean calculateScoreIfSpare(List<Integer> cumulativeScores) {
        if ((lastCalculatedIndex + 2) < scores.size() && addAndGetScores(lastCalculatedIndex) == TEN_SCORE.score()) {
            cumulativeScores.add(TEN_SCORE.score() + scores.get(lastCalculatedIndex + 2));
            updateScoreBoard(cumulativeScores);
            lastCalculatedIndex += 2;
            return true;
        }
        return false;
    }

    private boolean calculateScoreIfMiss(List<Integer> cumulativeScores) {
        if ((lastCalculatedIndex + 1) < scores.size() && addAndGetScores(lastCalculatedIndex) != TEN_SCORE.score()) {
            cumulativeScores.add(addAndGetScores(lastCalculatedIndex));
            updateScoreBoard(cumulativeScores);
            lastCalculatedIndex += 2;
            return true;
        }
        return false;
    }

    private void updateScoreBoard(List<Integer> cumulativeScores) {
        if (cumulativeScores.size() <= 1) {
            return;
        }
        int lastIndex = cumulativeScores.size() - 1;
        cumulativeScores.set(lastIndex, cumulativeScores.get(lastIndex - 1) + cumulativeScores.get(lastIndex));
    }

    public List<Integer> scores() {
        return scores;
    }

}
