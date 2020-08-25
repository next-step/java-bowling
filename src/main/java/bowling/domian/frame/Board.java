package bowling.domian.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {
    private static final int INITIAL_TOTAL_SCORE = 0;

    private List<FrameResult> frameResults;
    private int totalScore;

    public Board() {
        this.frameResults = new ArrayList<>();
        this.totalScore = INITIAL_TOTAL_SCORE;
    }

    public Board addFrameResult(FrameResult frameResult) {
        if (frameResult.canAddTotal()) {
            this.totalScore = frameResult.addTotalScore(totalScore);
        }

        this.frameResults.add(frameResult);

        return this;
    }

    public List<String> getDescs() {
        return frameResults.stream()
                .map(FrameResult::getDesc)
                .collect(Collectors.toList());
    }

    public List<Integer> getTotalScores() {
        if (frameResults.size() < 1) {
            return new ArrayList<>();
        }

        return getCalculatedScores();
    }

    private List<Integer> getCalculatedScores() {
        List<Integer> totalScores = new ArrayList<>();
        int count = this.frameResults.size();

        for (int i = 0; isTotalAddable(i, count); ++i) {
            totalScores.add(this.frameResults.get(i).getTotalScore());
        }

        return totalScores;
    }

    private boolean isTotalAddable(int index, int frameCounts) {
        return index < frameCounts && this.frameResults.get(index).canAddTotal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return totalScore == board.totalScore &&
                Objects.equals(frameResults, board.frameResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameResults, totalScore);
    }

    @Override
    public String toString() {
        return "Board{" +
                "frameResults=" + frameResults +
                ", totalScore=" + totalScore +
                '}';
    }
}
