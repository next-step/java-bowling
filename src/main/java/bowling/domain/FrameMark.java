package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FrameMark {
    private static final int FIRST_PIN_COUNT_INDEX = 0;
    private static final int SECOND_PIN_COUNT_INDEX = 1;
    private static final int THIRD_PIN_COUNT_INDEX = 2;

    private final Frame frame;
    private List<String> marks;

    public FrameMark(Frame frame) {
        this.frame = frame;
        this.marks = new ArrayList<>();
    }

    public List<String> marks() {
        List<PinCount> pinCounts = frame.pinCounts().pinCounts();

        if (pinCounts.size() == 1) {
            marks.add(firstMark(pinCounts));
        }

        if (pinCounts.size() == 2) {
            marks.addAll(Arrays.asList(firstMark(pinCounts), secondMark(pinCounts)));
        }

        if (pinCounts.size() == 3) {
            marks.addAll(Arrays.asList(firstMark(pinCounts), secondMark(pinCounts), thirdMark(pinCounts)));
        }

        return marks;
    }

    private String firstMark(List<PinCount> pinCounts) {
        return Mark.firstMark(pinCounts.get(FIRST_PIN_COUNT_INDEX));
    }

    private String secondMark(List<PinCount> pinCounts) {
        return Mark.secondMark(pinCounts.get(FIRST_PIN_COUNT_INDEX), pinCounts.get(SECOND_PIN_COUNT_INDEX));
    }

    private String thirdMark(List<PinCount> pinCounts) {
        return Mark.thirdMark(pinCounts.get(FIRST_PIN_COUNT_INDEX), pinCounts.get(SECOND_PIN_COUNT_INDEX), pinCounts.get(THIRD_PIN_COUNT_INDEX));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameMark that = (FrameMark) o;
        return Objects.equals(frame, that.frame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frame);
    }
}
