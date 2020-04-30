package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bowling {
    public static final int BLANK_FOR_STRIKE = 0;
    private List<List<Integer>> frames;
    private List<Frame> frames2;

    public Bowling() {
        this.frames = new ArrayList<>();
        this.frames2 = new ArrayList<>();
    }

    public List<String> roll(int fallenPinCount) {
        List<Integer> currentFrame = frames.stream()
                .filter(frame -> frame.size() != 2)
                .findFirst().orElse(new ArrayList<>());

        if (frames2.isEmpty()) {
            frames2.add(new Frame(1));
        }

        Frame currentFrame2 = frames2.get(frames2.size() - 1).bowl(fallenPinCount);
        frames2.add(currentFrame2.getNumber() - 1, currentFrame2);

        if (currentFrame.isEmpty()) {
            frames.add(recordFrameResult(currentFrame, fallenPinCount));
        } else {
            recordFrameResult(currentFrame, fallenPinCount);
        }

        return createResult();
    }

    private List<Integer> recordFrameResult(List<Integer> currentFrame, int fallenPinCount) {
        if (isStrike(fallenPinCount)) {
            currentFrame.add(fallenPinCount);
            currentFrame.add(BLANK_FOR_STRIKE);
            return currentFrame;
        }
        currentFrame.add(fallenPinCount);

        return currentFrame;
    }

    public List<String> createResult() {
        List<String> result = new ArrayList<>();
        for (List<Integer> frame : frames) {
            result.add(status(frame));
        }
        for (Frame frame : frames2) {
            result.add(frame.getRecord());
        }

        return result;
    }

    private String status(List<Integer> frame) {
        if (frame.size() != 2) {
            return frame.get(0) + "";
        }
        if (isStrike(frame.get(0))) {
            return "X";
        }
        if (isSpare(frame.get(0), frame.get(1))) {
            return frame.get(0) + "|/";
        }
        List<String> result = frame.stream()
                .map(p -> p == 0 ? "-" : String.valueOf(p))
                .collect(Collectors.toList());

        return result.get(0) + "|" + result.get(1);
    }

    private boolean isStrike(int countOfPins) {
        return countOfPins == 10;
    }

    private boolean isSpare(int first, int second) {
        return first + second == 10;
    }
}
