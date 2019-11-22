package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Frame {

    private List<Integer> scores = new ArrayList<>();
    private boolean isEnd;
    private boolean isSpare;


    public void addScore(int score) {
        this.scores.add(score);
        this.isEnd = isEndCondition(score);
        checkIsSpare();
    }

    private void checkIsSpare() {
        if (scores.size() < 2) {
            return;
        }

        isSpare = scores.get(0) + scores.get(1) == 10;
    }

    private boolean isEndCondition(int score) {
        return this.scores.size() > 1 || score == 10;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<Integer> getScores() {
        return scores;

    }

    public boolean hasOneScore() {
        return scores.size() == 1;
    }

    public String getScoreFormat() {
        String format = String.format("%3s", findFirstScoreFormat());

        if (this.hasOneScore()) {
            return format + "   ";
        }

        return format + String.format("%s%-2s", "|", findSecondScoreFormat());
    }

    private String findFirstScoreFormat() {
        int score = this.scores.get(0);

        Optional<FrameType> frameType = FrameType.findByScore(score);
        if (frameType.isPresent()) {
            return frameType.get().getCode();
        }
        return String.valueOf(score);
    }

    private String findSecondScoreFormat() {
        int score = this.scores.get(1);
        if (isSpare) {
            return FrameType.SPARE.getCode();
        }

        Optional<FrameType> frameType = FrameType.findByScore(score);
        if (frameType.isPresent()) {
            return frameType.get().getCode();
        }

        return String.valueOf(score);
    }

}
