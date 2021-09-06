package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Scores {
    private static final int NORMAL_SIZE = 2;
    private static final int FINAL_SIZE = 3;
    private static final int MAX_FRAME_NO = 10;

    private List<Score> scores = new ArrayList<>();


    public void add(int score) {
        add(score, 1);
    }

    public void add(int score, int frameNo) {
        validate(frameNo);
        if (firstTurn()) {
            scores.add(Score.of(score));
            return;
        }
        Score before = scores.get(size() - 1);
        scores.add(Score.of(before, score));
    }

    private void validate(int frameNo) {
        if (frameNo > MAX_FRAME_NO) {
            throw new IllegalStateException("마지막 프레임의 투구를 모두 마쳤습니다.");
        }
        if (done(frameNo)) {
            throw new IllegalStateException("현재 프레임에서 투구를 모두 마쳤습니다.");
        }
    }

    public boolean done(int frameNo) {
        if (firstTurn()) {
            return false;
        }
        if (frameNo == MAX_FRAME_NO && (scores.contains(Score.STRIKE) || scores.contains(Score.SPARE))) {
            return scores.size() == FINAL_SIZE;
        }
        return scores.contains(Score.STRIKE) || scores.contains(Score.SPARE) || scores.size() == NORMAL_SIZE;
    }

    private boolean firstTurn() {
        return size() == 0;
    }

    private int size() {
        return scores.size();
    }

    public List<Score> getScores() {
        return scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scores scores1 = (Scores) o;
        return Objects.equals(scores, scores1.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }
}
