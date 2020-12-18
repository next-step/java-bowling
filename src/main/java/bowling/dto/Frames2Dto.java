package bowling.dto;

import java.util.List;
import java.util.stream.Stream;

public class Frames2Dto {
    private final List<Frame2Dto> value;

    private Frames2Dto(List<Frame2Dto> value) {
        this.value = value;
    }

    public static Frames2Dto of(List<Frame2Dto> value) {
        return new Frames2Dto(value);
    }

    public Stream<Frame2Dto> viewDtoStream() {
        return value.stream();
    }
}
