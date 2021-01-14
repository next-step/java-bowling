package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PinMarks {

    private final List<PinMark> marks;

    public PinMarks(int maxMarks) {
        this.marks = new ArrayList<>(maxMarks);
    }

    public PinMarks(List<PinMark> marks) {
        this.marks = marks;
    }

    public int mark(PinMark pinMark) {
        marks.add(pinMark);
        return getCountOfMarked();
    }

    public int getCountOfMarked(){
        return marks.stream()
                .map(PinMark::getCountOfFallDownPins)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public boolean isSumGreaterThanMaxPins(PinMark pinMark) {
        int current = marks.stream()
                .map(PinMark::getCountOfFallDownPins)
                .reduce(Integer::sum)
                .orElse(0);
        return current + pinMark.getCountOfFallDownPins() > PinMark.MAX_PINS;
    }

    public List<PinMarkSign> toSigns() {
        return marks.stream()
                .map(mark -> PinMarkSign.number(mark.getCountOfFallDownPins()))
                .collect(Collectors.toList());
    }

    @Deprecated
    public PinMark get(int i) {
        return marks.get(i);
    }

    public List<PinMark> getAll() {
        return Collections.unmodifiableList(marks);
    }
}
