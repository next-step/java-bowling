package bowling.dto;

import bowling.domain.Frame;

import java.util.ArrayList;
import java.util.List;

public class FrameDto {

    private int number;

    private List<Integer> pintCounts = new ArrayList<>();

    public FrameDto(Frame frame) {
        this.number=frame.number();
        this.pintCounts=frame.pinCounts();
    }

    public int number() {
        return number;
    }

    public List<Integer> pinCounts() {
        return pintCounts;
    }
}
