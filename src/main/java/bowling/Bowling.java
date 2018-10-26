package bowling;

import java.util.ArrayList;
import java.util.List;

public class Bowling {
    private List<Integer> falledPins = new ArrayList<>();

    public List<String> roll(int falledPin) {
        falledPins.add(falledPin);
        if (falledPins.size() < 19) {
            addBlankPinWhenStrike(falledPin);
        }
        return createResult(falledPins);
    }

    private void addBlankPinWhenStrike(int falledPin) {
        if (isStrike(falledPin)) {
            falledPins.add(0);
        }
    }

    List<String> createResult(List<Integer> falledPins) {
        List<String> result = new ArrayList<>();
        int offsetOfFrame = 0;
        while (offsetOfFrame < falledPins.size()) {
            if (isTenFrame(offsetOfFrame)) {
                result.add(createResultOf10(getTenFalledPins(falledPins, offsetOfFrame)));
                offsetOfFrame += 3;
            } else {
                result.add(status(falledPins, offsetOfFrame));
                offsetOfFrame += 2;
            }
        }
        return result;
    }

    private boolean isTenFrame(int offsetOfFrame) {
        return offsetOfFrame == (10 - 1) * 2;
    }

    private List<Integer> getTenFalledPins(List<Integer> falledPins, int offsetOfFrame) {
        return falledPins.subList(offsetOfFrame, falledPins.size());
    }

    String createResultOf10(List<Integer> falledPinsOf10) {
        return createResultOf10(falledPinsOf10, 0);
    }

    private String createResultOf10(List<Integer> falledPinsOf10, int offsetOfFrame) {
        if (falledPinsOf10.size() <= offsetOfFrame) {
            return "";
        }
        String status = status(falledPinsOf10, offsetOfFrame);
        if (offsetOfFrame == 2) {
            return status;
        }

        if (status.equals("X")) {
            return status + createResultOf10(falledPinsOf10, offsetOfFrame + 1);
        }

        if (falledPinsOf10.size() == 3) {
            return status + status(falledPinsOf10, 2);
        }

        return status(falledPinsOf10, offsetOfFrame);
    }

    private String status(List<Integer> falledPins, int offsetOfFrame) {
        if (falledPins.size() <= offsetOfFrame) {
            return "";
        }

        int first = falledPins.get(offsetOfFrame);
        if (isStrike(first)) {
            return "X";
        }

        int nextOffset = offsetOfFrame + 1;
        if (nextOffset < falledPins.size()) {
            int second = falledPins.get(nextOffset);
            if (isSpare(first, second)) {
                return first + "/";
            }
            return first + "" + second;
        }

        return first + "";
    }

    private boolean isStrike(int first) {
        return first == 10;
    }

    private boolean isSpare(int first, int second) {
        return first + second == 10;
    }
}
