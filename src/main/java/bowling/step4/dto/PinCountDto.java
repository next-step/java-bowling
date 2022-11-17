package bowling.step4.dto;

import bowling.step4.domain.Pitch;
import bowling.step4.domain.Pitches;

import java.util.List;

public class PinCountDto {

    public final int count;

    public PinCountDto(int count) {
        this.count = count;
    }

    public static PinCountDto from(Pitch pitch) {
        return new PinCountDto(pitch.count());
    }
}
