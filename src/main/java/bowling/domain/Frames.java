package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int TOTAL_NORMAL_FRAME = 9;
    private static final int TOTAL_FRAME = 10;
    private static final int NEXT_FRAME = 1;
    private static final int NEXT_AFTER_FRAME = 2;
    private List<Frame> frames;
    private Frame frame;
    private FrameScores frameScores = new FrameScores();

    public FrameScores getFrameScores() {
        return frameScores;
    }

    public Frames() {
        this.frames = new ArrayList<>();
        this.frame = new Frame();
    }

    public Frame getFrame() {
        return frame;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int currentFrame() {
        return frames.size();
    }

    public void addFrame(Frame frame) {
        this.frames.add(frame);
    }

    public boolean isFinalGame() {
        return frame.isEndGame();
    }

    public void processPin(Score numberOfPin) {
        boolean isFinalFrame = isFinalFrame();
        frame.addFrame(numberOfPin, isFinalFrame);
        if (isFinalFrame) {
            processFinalFrame();
        }
        if (!isFinalFrame) {
            processNormalFrame();
        }
        calculateScore();
    }

    private void processNormalFrame() {
        if (frame.isNextFrame()) {
            addFrame(new NormalFrame(frame));
            this.frame = new Frame();
        }
    }

    private void processFinalFrame() {
        if (frame.isEndGame()) {
            addFrame(new FinalFrame(frame));
        }
    }

    public boolean isFinalFrame() {
        return currentFrame() >= TOTAL_NORMAL_FRAME;
    }

    public boolean isEndGame() {
        return currentFrame() == TOTAL_FRAME;
    }

    public Frame finalFrame() {
        return frames.get(TOTAL_FRAME - 1);
    }

    public Frame nextFrame(int index) {
        return frames.get(index + NEXT_FRAME);
    }

    public Frame nextAfterFrame(int index) {
        return frames.get(index + NEXT_AFTER_FRAME);
    }

    public void calculateScore() {
        frameScores = new FrameScores();
        int sumScore = 0;
        calculate(sumScore);
    }

    private int calculate(int sumScore) {
        for (int i = 0; i < currentFrame(); i++) {
            sumScore = calculateCase(i, sumScore);
        }
        if (isEndGame()) {
            Frame finalFrame = finalFrame();
            frameScores.add(sumScore + finalFrame.sumScore());
        }
        return sumScore;
    }

    private int calculateCase(int index, int sumScore) {
        Frame nowFrame = frames.get(index);
        if (nowFrame.isEnableCalculate()) {
            sumScore = isMissCase(sumScore, nowFrame);
        }
        if (nowFrame.isStrike()) {
            sumScore = calculateStrike(index, sumScore);
        }
        if (nowFrame.isSpare()) {
            sumScore = calculateSpare(index, sumScore);
        }
        return sumScore;
    }

    private int calculateSpare(int index, int sumScore) {
        if (isNextFrame(index)) {
            sumScore += nextFrame(index).calculateSpare();
            return addScore(sumScore);
        }
        if (!frame.isNumberOfTryZero() && !isFinalFrame()) {
            sumScore += getFrame().calculateSpare();
            return addScore(sumScore);
        }
        return sumScore;
    }

    private boolean isNextFrame(int index) {
        return index < currentFrame() - 1;
    }

    private boolean isNextAfterFrame(int index) {
        return index < currentFrame() - 2;
    }

    private int calculateStrike(int index, int sumScore) {
        if (isNextFrame(index) && !nextFrame(index).isStrike()) {
            sumScore += nextFrame(index).calculateSingleStrike();
            return addScore(sumScore);
        }
        if (isFinalFrame() && frame.isCountOfStrike()) {
            sumScore += getFrame().calculateDoubleStrike();
            return addScore(sumScore);
        }
        if (isNextAfterFrame(index) && nextFrame(index).isStrike()) {
            sumScore += nextAfterFrame(index).calculateDoubleStrike();
            return addScore(sumScore);
        }
        return sumScore;
    }

    private int isMissCase(int sumScore, Frame nowFrame) {
        sumScore += nowFrame.calculate();
        addScore(sumScore);
        return sumScore;
    }

    private int addScore(int sumScore) {
        frameScores.add(sumScore);
        return sumScore;
    }
}
