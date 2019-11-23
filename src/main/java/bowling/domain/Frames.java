package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {
    public static final int LAST_FRAME = 10;
    public static final int NON_SCORE = -1;
    private static final int SPARE_BONUS_SIZE = 1;
    private static final int STRIKE_BONUS_SIZE = 2;
    private List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public Frames() {
        this.frames = new ArrayList<>();
        for (int i = 0; i < LAST_FRAME; i++) {
            Frame frame = new Frame(i + 1);
            frames.add(frame);
        }
    }

    public Frame frameByIndex(int index) {
        return frames.get(index);
    }

    public boolean isFallDownAble(int i) {
        return frames.get(i).isFallDownAble();
    }

    public void fallDown(int index, int pinCount) {
        frames.get(index).fallDown(pinCount);
    }

    public int getScore(int index) {
        Frame frame = frames.get(index);
        if (frame.isStrike() || frame.isSpare()) {
            return sumBonusScore(frame);
        }

        if (!frame.isEnd()) {
            return NON_SCORE;
        }

        return frame.getScore();
    }

    private int sumBonusScore(Frame frame) {
        int nextBallsSize = frame.isStrike() ? STRIKE_BONUS_SIZE : SPARE_BONUS_SIZE;
        Pins nextPins = nextBalls(frame.getFrameNumber().getNumber(), nextBallsSize);
        if (nextPins.isNotSameSize(nextBallsSize)) {
            return NON_SCORE;
        }
        return frame.getScore() + nextPins.score();
    }

    private Pins nextBalls(int start, int size) {
        List<Pin> Pins = IntStream.range(start, frames.size())
                .mapToObj(i -> frames.get(i))
                .flatMap(f -> f.unmodifiableBalls().stream())
                .filter(Pin::isFallDown)
                .limit(size)
                .collect(Collectors.toList());
        return new Pins(Pins);
    }
}
