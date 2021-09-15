package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static bowling.domain.Frames.TOTAL_FRAME_COUNT;

public class TotalScores {
    private final List<Integer> scores;
    private int lastIndex;

    private TotalScores(List<Integer> scores) {
        this.lastIndex = 0;
        this.scores = scores;
    }

    private TotalScores(Frames frames, List<Integer> calculatedScores) {
        this(new ArrayList<>());
        IntStream.range(0, frames.frames().size())
                 .forEach(i -> initTotalScores(frames, i, calculatedScores));
    }

    public static TotalScores from(Frames frames, List<Integer> calculatedScores) {
        return new TotalScores(frames, calculatedScores);
    }

    public static TotalScores from(List<Integer> scores) {
        return new TotalScores(scores);
    }

    private void initTotalScores(final Frames frames, final int index, List<Integer> calculatedScores) {
        List<Score> scores = frames.frames().get(index).scores();
        IntStream.range(0, scores.size())
                 .forEach(i -> {
                     this.scores.add(scores.get(i).score());
                     getTotalScores(frames, calculatedScores);
                 });
    }

    public List<Integer> getTotalScores(Frames frames, List<Integer> calculatedScores) {
        if (!checkCalculateSize()) {
            return calculatedScores;
        }

        calculateScores(calculatedScores);
        if (frames.isFinish() && calculatedScores.size() != TOTAL_FRAME_COUNT) {
            calculateScores(calculatedScores);
        }

        return calculatedScores;
    }

    private boolean checkCalculateSize() {
        if ((scores.size() == 0 || scores.size() <= lastIndex) || (scores.get(lastIndex) == Score.TEN_SCORE && (lastIndex + 2) >= scores.size())) {
            return false;
        }
        return true;
    }

    private void calculateScores(List<Integer> calculatedScores) {
        if (calculateStrike(calculatedScores)) {
            return;
        }
        if (calculateSpare(calculatedScores)) {
            return;
        }
        calculateMiss(calculatedScores);
    }

    private int addScores(int index) {
        return scores.get(index) + scores.get(index + 1);
    }

    private boolean calculateStrike(List<Integer> calculatedScores) {
        if ((lastIndex + 2) < scores.size() && scores.get(lastIndex) == Score.TEN_SCORE) {
            calculatedScores.add(Score.TEN_SCORE + addScores(lastIndex + 1));
            updateScores(calculatedScores);
            lastIndex++;
            return true;
        }
        return false;
    }

    private boolean calculateSpare(List<Integer> calculatedScores) {
        if ((lastIndex + 2) < scores.size() && addScores(lastIndex) == Score.TEN_SCORE) {
            calculatedScores.add(Score.TEN_SCORE + scores.get(lastIndex + 2));
            updateScores(calculatedScores);
            lastIndex += 2;
            return true;
        }
        return false;
    }

    private boolean calculateMiss(List<Integer> calculatedScores) {
        if ((lastIndex + 1) < scores.size() && addScores(lastIndex) != Score.TEN_SCORE) {
            calculatedScores.add(addScores(lastIndex));
            updateScores(calculatedScores);
            lastIndex += 2;
            return true;
        }
        return false;
    }

    private void updateScores(List<Integer> calculatedScores) {
        if (calculatedScores.size() <= 1) {
            return;
        }
        int lastIndex = calculatedScores.size() - 1;
        calculatedScores.set(lastIndex, calculatedScores.get(lastIndex - 1) + calculatedScores.get(lastIndex));
    }

    public void addScore(Score score) {
        scores.add(score.score());
    }
}
