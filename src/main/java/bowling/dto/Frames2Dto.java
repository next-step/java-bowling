package bowling.dto;

import java.util.List;

public class Frames2Dto {
    private final List<Frame2Dto> value;

    private Frames2Dto(List<Frame2Dto> value) {
        this.value = value;
    }

    public static Frames2Dto of(List<Frame2Dto> value) {
        return new Frames2Dto(value);
    }

    public Frame2Dto get(int frameNo) {
        if (frameNo <= value.size()) {
            int index = frameNo - 1;
            return value.get(index);
        }

        return null;
    }
}
