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
        Frame nowFrame = frames.get(index);
        sumScore = isMissCase(sumScore, nowFrame);

        Frame nextFrame = null;
        if (!nowFrame.isEnableCalculate() && index < currentFrame() - 1) {
            nextFrame = nextFrame(index);
            sumScore = nextFrame.calculateBonus(sumScore);
            return addScore(sumScore);
        }
        if (!nowFrame.isEnableCalculate() && !frame.isNumberOfTryZero() && index < TOTAL_NORMAL_FRAME) {
            nextFrame = getFrame();
            sumScore = nextFrame.calculateSpare(sumScore);
            return addScore(sumScore);
        }
        if (!nowFrame.isEnableCalculate() && isFinalFrame() && frame.isCountOfStrike()) {
            nextFrame = getFrame();
            sumScore = nextFrame.calculateDoubleStrike(sumScore);
            return addScore(sumScore);
        }
        if (!nowFrame.isEnableCalculate() && index < currentFrame() - 2 && nextFrame(index).isStrike()) {
            nextFrame = nextAfterFrame(index);
            sumScore = nextFrame.calculateDoubleStrike(sumScore);
            return addScore(sumScore);
        }
        return sumScore;
    }

    private int isMissCase(int sumScore, Frame nowFrame) {
        if (nowFrame.isEnableCalculate()) {
            sumScore = nowFrame.calculate(sumScore);
            frameScores.add(sumScore);
        }
        return sumScore;
    }

    private int addScore(int sumScore) {
        frameScores.add(sumScore);
        return sumScore;
    }
}
