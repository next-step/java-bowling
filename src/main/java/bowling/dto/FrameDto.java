package bowling.dto;

import bowling.domain.FrameOld;

import java.util.ArrayList;
import java.util.List;

public class FrameDto {

    private int number;

    private List<Integer> pintCounts = new ArrayList<>();

    public FrameDto(FrameOld frameOld) {
        this.number= frameOld.number();
        this.pintCounts= frameOld.pinCounts();
    }

    public int number() {
        return number;
    }

    public List<Integer> pinCounts() {
        return pintCounts;
    }
}
