package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FinalFrame {

    private List<Integer> scores = new ArrayList<>();
    private boolean isEnd;
    private boolean isSpare;

    public void addScore(int score) {
        this.scores.add(score);
        this.isEnd = isEndCondition();
        checkIsSpare();
    }

    private boolean isEndCondition() {
        return hasNotStrikeOrSpare() || hasSize(3);
    }

    private void checkIsSpare() {
        if (scores.size() != 2) {
            return;
        }

        isSpare = scores.get(0) + scores.get(1) == 10;
    }

    private boolean hasNotStrikeOrSpare() {
        return scores.size() == 2 && scores.get(0) + scores.get(1) < 10;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public String getScoreFormat() {
        String format = String.format("%3s", findFirstScoreFormat());

        if (hasSize(1)) {
            return format + "   ";
        }

        if (hasSize(2)) {
            return format + String.format("%s%-2s", "|", findSecondScoreFormat());
        }

        return String.format("%2s|%s|%s", findFirstScoreFormat(), findSecondScoreFormat(), findThirdScoreFormat());
    }

    private boolean hasSize(int size) {
        return scores.size() == size;
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

    private String findThirdScoreFormat() {
        int score = this.scores.get(2);

        Optional<FrameType> frameType = FrameType.findByScore(score);
        if (frameType.isPresent()) {
            return frameType.get().getCode();
        }

        return String.valueOf(score);
    }

    public boolean hasNotFinalFrame() {
        return this.scores.size() == 0;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public boolean isSpare() {
        return isSpare;
    }
}
