package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

    private static final int LAST_NORMAL_FRAME_SIZE = 10;

    private final List<Frame> frames;
    private Round round;

    private Frames(List<Frame> frames) {
        this.frames = frames;
        this.round = Round.firstRound();
    }

    public static Frames init() {
        List<Frame> frames = generateNormalFrame();
        frames.add(FinalFrame.getFrame());
        return new Frames(frames);
    }

    private static List<Frame> generateNormalFrame() {
        return IntStream.range(1, LAST_NORMAL_FRAME_SIZE)
                .mapToObj(i -> NormalFrame.from(Round.from(i)))
                .collect(Collectors.toList());
    }

    public int size() {
        return frames.size();
    }

    public boolean isContinue() {
        return !(round.isFinalRound() && frames.get(round.now()).roundEnded());
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }

    public Frame nFrame(int n) {
        if (n >= 0) {
            return frames.get(n);
        }
        return nFrame(0);
    }

    public void throwBall(int topplePin) {
        Frame nowFrame = nFrame(round.now());
        nowFrame.throwBall(topplePin);
        beforeFrameAddBonus(topplePin);
        if (nowFrame.isLastRound() && nowFrame.isLastRound()) {
            nowFrame.createScore();
        }
        if (nowFrame.roundEnded()) {
            nowFrame.createScore();
            round = round.nextRound();
        }
    }

    private void beforeFrameAddBonus(int score) {
        if (hasLeftSecondFrame() && !leftSecondFrameCanCalculate()) {
            leftSecondFrameAddBonus(score);
        }
        if (hasLeftFrame() && !leftFrameCanCalculate()) {
            leftFrameAddBonus(score);
        }
    }

    private void leftFrameAddBonus(int score) {
        Frame leftFrame = nFrame(round.before());
        leftFrame.additionalScore(score);
    }

    private void leftSecondFrameAddBonus(int score) {
        Frame leftSecondFrame = nFrame(round.beforeSecond());
        leftSecondFrame.additionalScore(score);
    }

    private boolean leftSecondFrameCanCalculate() {
        Frame leftSecondFrame = nFrame(round.beforeSecond());
        return leftSecondFrame.canCalculate();
    }

    private boolean hasLeftSecondFrame() {
        return round.hasBeforeSecond();
    }

    private boolean leftFrameCanCalculate() {
        Frame leftFrame = nFrame(round.before());
        return leftFrame.canCalculate();
    }

    private boolean hasLeftFrame() {
        return round.hasBefore();
    }

    public int round() {
        return round.round();
    }
}
