package bowling;

import java.util.ArrayList;
import java.util.List;

public class Bowling {
    public static final int BLANK_FOR_STRIKE = 0;
    private List<List<Integer>> frames;

    public Bowling() {
        this.frames = new ArrayList<>();
    }

    public List<String> roll(int fallenPinCount) {
        List<Integer> currentFrame = frames
                .stream()
                .filter(frame -> frame.size() != 2)
                .findFirst().orElse(new ArrayList<>());

        frames.add(recordFrameResult(currentFrame, fallenPinCount));

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
            if (frame.size() == 2) {
                if (isStrike(frame.get(0))) {
                    result.add(status(frame.get(0)));
                } else if (isSpare(frame.get(0), frame.get(1))) {
                    result.add(status(frame.get(0)) + "|/");
                } else {
                    result.add(status(frame.get(0)) + "|" + status(frame.get(1)));
                }
            }
            if (frame.size() == 1) {
                result.add(status(frame.get(0)));
            }
        }

        return result;
    }

    private String status(int countOfPins) {
        if (isStrike(countOfPins)) {
            return "X";
        }
        if (countOfPins == 0) {
            return "-";
        }
        return countOfPins + "";
    }

    private boolean isStrike(int countOfPins) {
        return countOfPins == 10;
    }

    private boolean isSpare(int first, int second) {
        if (isStrike(first)) {
            return false;
        }
        return first + second == 10;
    }
}
