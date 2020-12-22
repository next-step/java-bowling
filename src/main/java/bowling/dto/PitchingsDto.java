package bowling.dto;

import bowling.domain.Pitching;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class PitchingsDto implements Iterable<Pitching> {
    private final List<Pitching> pitchings;

    private PitchingsDto(List<Pitching> pitchings) {
        this.pitchings = pitchings;
    }

    public static PitchingsDto of(List<Pitching> pitchings) {
        return new PitchingsDto(pitchings);
    }

    public Stream<Pitching> stream() {
        return pitchings.stream();
    }

    @Override
    public Iterator<Pitching> iterator() {
        return pitchings.iterator();
    }
}
