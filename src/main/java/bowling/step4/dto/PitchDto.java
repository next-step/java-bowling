package bowling.step4.dto;

import bowling.step4.domain.Pitch;
import bowling.step4.domain.Pitches;

import java.util.List;
import java.util.stream.Collectors;

public class PitchDto {
    public final List<Integer> pinCounts;

    public PitchDto(List<Integer> pinCounts) {
        this.pinCounts = pinCounts;
    }

    public static PitchDto from(Pitches pitches) {
        return new PitchDto(pitches.value().stream().map(Pitch::count).collect(Collectors.toList()));
    }
}
