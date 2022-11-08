package bowling;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {
    private static int MIN_FRAMES = 1;
    private static int MAX_FRAMES = 9;

    private int frameNumber;
    private List<Pins> scores;   // 쓰러뜨린 핀 개수 리스트
    private Thrown thrown;

    // TODO
    private NormalFrame(int frameNumber, List<Pins> scores) {
        valid(frameNumber);
        this.frameNumber = frameNumber;
        this.scores = scores;
    }

    private NormalFrame(int frameNumber, Thrown thrown) {
        this.frameNumber = frameNumber;
        this.thrown = thrown;
    }

    public static NormalFrame of2(int frameNumber, int countOfPins) {
        return new NormalFrame(frameNumber, new Thrown(Pins.from(countOfPins)));
    }

    // TODO
    public static NormalFrame of(int frameNumber, int countOfPins) {
        List<Pins> scores = new ArrayList<>();
        Pins pins = new Pins(countOfPins);
        scores.add(pins);
        return new NormalFrame(frameNumber, scores);
    }

    @Override
    public boolean isFinished() {
        if (getScore() == 10) {
            return true;
        }
        return false;
    }

    // TODO
    public List<Pins> getValues() {
        return scores;
    }

    @Override
    public int getScore() {
        return thrown.getScore();
    }

    private void valid(int frameNumber) {
        if (frameNumber < MIN_FRAMES || frameNumber > MAX_FRAMES) {
            throw new IllegalArgumentException("잘못된 프레임 번호입니다.");
        }
    }

    // TODO
    @Override
    public Frame bowl(int countOfPins) {
        thrown.bowl(countOfPins);
        Pins totalPins = scores.get(0).totalPins(countOfPins);
        scores.add(totalPins);
        return this;
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

}
