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

    public boolean isNextFrame() {
        return frames.get(currentFrame() - 1).isNextFrame();
    }

    public boolean isFinalGame() {
        return frame.isEndGame();
    }

    public void processPin(int numberOfPin) {
        frame.addFrame(numberOfPin, isFinalFrame());
        if (isFinalFrame()) {
            processFinalFrame();
        }
        if (!isFinalFrame()) {
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

    public Frame nowFrame(int index) {
        return frames.get(index);
    }

    public Frame nextFrame(int index) {
        return frames.get(index + NEXT_FRAME);
    }

    public Frame nextAfterFrame(int index) {
        return frames.get(index + NEXT_AFTER_FRAME);
    }

    public boolean isSingleStrike(int index) {
        return index < currentFrame() - 1 && !nextFrame(index).isStrike();
    }

    public boolean isDoubleStrike(int index) {
        return (index < currentFrame() - 2 && nextFrame(index).isStrike());
    }

    public boolean isSpare(int index) {
        return index < currentFrame() - 1;
    }

    public boolean isNumberOfTryZero() {
        return frame.isNumberOfTryZero();
    }

    public boolean isDoubleStrikeFinalFrame() {
        return isFinalFrame() && frame.isCountOfStrike();
    }

    public void calculateScore() {
        frameScores = new FrameScores();
        int sumScore = 0;
        sumScore = calculateNormalFrame(sumScore);
        calculateFinalFrame(sumScore);
    }

    private void calculateFinalFrame(int sumScore) {
        if (isEndGame()) {
            Frame finalFrame = finalFrame();
            frameScores.add(sumScore + finalFrame.sumScore());
        }
    }

    private int calculateNormalFrame(int sumScore) {
        for (int i = 0; i < currentFrame(); i++) {
            sumScore = calculateCase(i, sumScore);
        }
        return sumScore;
    }

    private int calculateCase(int index, int sumScore) {
        Frame nowFrame = nowFrame(index);
        if (nowFrame.isStrike()) {
            sumScore = calculateStrike(index, sumScore);
        }
        if (nowFrame.isSpare()) {
            sumScore = calculateSpare(index, sumScore);
        }
        if (nowFrame.isMiss()) {
            sumScore = sumScore + nowFrame.calculateMissCase();
            frameScores.add(sumScore);
        }
        return sumScore;
    }

    private int calculateSpare(int index, int sumScore) {
        if (isSpare(index)) {
            Frame nextFrame = nextFrame(index);
            sumScore = nextFrame.calculateSpare(sumScore);
            return addScore(sumScore);
        }
        if (!isNumberOfTryZero() && index < TOTAL_NORMAL_FRAME ) {
            Frame rollPin = getFrame();
            sumScore = rollPin.calculateSpare(sumScore);
            return addScore(sumScore);
        }
        return sumScore;
    }

    private int calculateStrike(int index, int sumScore) {
        if (isSingleStrike(index)) {
            Frame nextFrame = nextFrame(index);
            sumScore = nextFrame.calculateSingleStrike(sumScore);
            return addScore(sumScore);
        }
        if (isDoubleStrikeFinalFrame()) {
            Frame rollPin = getFrame();
            sumScore = rollPin.calculateDoubleStrike(sumScore);
            return addScore(sumScore);
        }
        if (isDoubleStrike(index)) {
            Frame nextAfterFrame = nextAfterFrame(index);
            sumScore = nextAfterFrame.calculateDoubleStrike(sumScore);
            return addScore(sumScore);
        }
        return sumScore;
    }

    private int addScore(int sumScore) {
        frameScores.add(sumScore);
        return sumScore;
    }
}
