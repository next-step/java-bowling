package bowling;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame {

    private static int MIN_FRAMES = 1;
    private static int MAX_FRAMES = 9;

    private int frameNumber;
    private List<Pins> scores;   // 쓰러뜨린 핀 개수 리스트

    private NormalFrame(int frameNumber, List<Pins> scores) {
        valid(frameNumber);
        this.frameNumber = frameNumber;
        this.scores = scores;
    }

    public static NormalFrame of(int frameNumber, int countOfPins) {
        List<Pins> scores = new ArrayList<>();
        Pins pins = new Pins(countOfPins);
        scores.add(pins);
        return new NormalFrame(frameNumber, scores);
    }

    public NormalFrame bowl(int countOfPins) {
        Pins totalPins = scores.get(0).totalPins(countOfPins);
        scores.add(totalPins);
        return this;
    }

    public boolean isFinished() {
        if (getScore() == 10 || scores.size() == 2) {
            return true;
        }
        return false;
    }

    public List<Pins> getValues() {
        return scores;
    }

    public int getScore() {
        return scores.stream()
            .mapToInt(Pins::getFalledPins)
            .sum();
    }

    private void valid(int frameNumber) {
        if (frameNumber < MIN_FRAMES || frameNumber > MAX_FRAMES) {
            throw new IllegalArgumentException("잘못된 프레임 번호입니다.");
        }
    }
}
