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
                .mapToObj(i -> NormalFrame.from())
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

    public Frame roundFrame(int round) {
        if (round >= 0) {
            return frames.get(round);
        }
        return roundFrame(0);
    }

    public void throwBall(int topplePin) {
        Frame nowFrame = roundFrame(round.now());
        nowFrame.throwBall(topplePin);
        beforeFrameAddBonus(topplePin);
        if (round.isFinalRound()) {
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
        Frame leftFrame = roundFrame(round.before());
        leftFrame.additionalScore(score);
    }

    private void leftSecondFrameAddBonus(int score) {
        Frame leftSecondFrame = roundFrame(round.beforeSecond());
        leftSecondFrame.additionalScore(score);
    }

    private boolean leftSecondFrameCanCalculate() {
        Frame leftSecondFrame = roundFrame(round.beforeSecond());
        return leftSecondFrame.canCalculate();
    }

    private boolean hasLeftSecondFrame() {
        return round.hasBeforeSecond();
    }

    private boolean leftFrameCanCalculate() {
        Frame leftFrame = roundFrame(round.before());
        return leftFrame.canCalculate();
    }

    private boolean hasLeftFrame() {
        return round.hasBefore();
    }

    public int round() {
        return round.round();
    }

    public Score getScore(int round) {
        return Score.of(roundFrame(round).score(), ScoreState.ofNone());
    }

    public boolean canCalculateScore(int round) {
        Frame nowFrame = roundFrame(round);
        return nowFrame.hasScore() && nowFrame.canCalculate();
    }
}
