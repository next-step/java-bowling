package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int MAX_FRAMES_SIZE = 10;
    private static final int START_NUMBER = 1;
    private List<Frame> frames;
    private int currentNumber;

    public Frames() {
        frames = new ArrayList<>();
        currentNumber = START_NUMBER;
    }

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public Frame first(int countOfPins) {
        frames.add(new NormalFrame(countOfPins, currentNumber));
        return frames.get(currentNumber - 1);
    }

//    public Frame next(int countOfPins) {
//        return frames.get(currentNumber-1).addPinCount(countOfPins);
//    }
//
//    public Frame nextFrame(int countOfPins) {
//        return frames.get(currentNumber-1).nextFrame(countOfPins);
//    }


    public boolean isEndSize() {
        return frames.size() == MAX_FRAMES_SIZE;
    }
}
