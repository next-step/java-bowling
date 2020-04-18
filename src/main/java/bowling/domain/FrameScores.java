package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FrameScores {
    private static final int BONUS = 10;
    private static final int DOUBLE_BONUS = 20;
    private static final int ZERO = 0;
    private List<Integer> frameScores;
    private int sumScore;

    public FrameScores(List<Integer> frameScores) {
        this.frameScores = frameScores;
    }

    public FrameScores() {
        this.frameScores = new ArrayList<>();
        this.sumScore = ZERO;
    }

    public List<String> getScore() {
        return frameScores.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return frameScores.get(0).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        FrameScores that = (FrameScores) o;
        return Objects.equals(frameScores, that.frameScores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameScores);
    }

    public void add(int score) {
        this.frameScores.add(score);
    }

    public void calculateScore(Frames frames) {
        this.init();
        calculateNormalFrame(frames);
        calculateFinalFrame(frames);
    }

    private void calculateFinalFrame(Frames frames) {
        if (frames.isEndGame()) {
            Frame finalFrame = frames.finalFrame();
            frameScores.add(sumScore + finalFrame.sumScore());
        }
    }

    private void calculateNormalFrame(Frames frames) {
        for (int i = 0; i < frames.currentFrame(); i++) {
            calculateCase(i, frames);
        }
    }

    private void calculateCase(int index, Frames frames) {
        Frame nowFrame = frames.nowFrame(index);
        if (nowFrame.isStrike()) {
            calculateStrikeCase(index, frames);
        }
        if (nowFrame.isSpare()) {
            calculateSpareCase(index, frames);
        }
        if (nowFrame.isMiss()) {
            calculateMissCase(nowFrame);
        }
        return;
    }

    private void calculateMissCase(Frame nowFrame) {
        sumScore = sumScore + nowFrame.sumScore();
        frameScores.add(sumScore);
    }

    private void init() {
        this.frameScores = new ArrayList<>();
        this.sumScore = ZERO;
    }

    private void calculateSpareCase(int index, Frames frames) {
        Frame rollPin = frames.getFrame();
        if (frames.isSpare(index)) {
            Frame nextFrame = frames.nextFrame(index);
            sumScore = sumScore + BONUS + nextFrame.firstScore();
            frameScores.add(sumScore);
            return;
        }
        if (!frames.isNumberOfTryZero()) {
            sumScore = sumScore + BONUS + rollPin.firstScore();
            frameScores.add(sumScore);
            return;
        }
        return;
    }

    private void calculateStrikeCase(int index, Frames frames) {
        if (calculateDoubleStrike(index, frames)) return;
        if (calculateDoubleStrikeFinalFrame(frames)) return;
        if (calculateSingleStrike(index, frames)) return;
        return;
    }

    private boolean calculateDoubleStrikeFinalFrame(Frames frames) {
        Frame rollPin = frames.getFrame();
        if (frames.isDoubleStrikeFinalFrame()) {
            sumScore = sumScore + DOUBLE_BONUS + rollPin.firstScore();
            frameScores.add(sumScore);
            return true;
        }
        return false;
    }

    private boolean calculateSingleStrike(int index, Frames frames) {
        if (frames.isSingleStrike(index)) {
            Frame nextFrame = frames.nextFrame(index);
            sumScore = sumScore + BONUS + nextFrame.sumStrikeScore();
            frameScores.add(sumScore);
            return true;
        }
        return false;
    }

    private boolean calculateDoubleStrike(int index, Frames frames) {
        if (frames.isDoubleStrike(index)) {
            Frame nextAfterFrame = frames.nextAfterFrame(index);
            sumScore = sumScore + DOUBLE_BONUS + nextAfterFrame.firstScore();
            frameScores.add(sumScore);
            return true;
        }
        return false;
    }
}
