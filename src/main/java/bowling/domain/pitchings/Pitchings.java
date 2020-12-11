package bowling.domain.pitchings;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;

import java.util.stream.Stream;

public interface Pitchings extends Iterable<Pitching> {
    void addPitching(KnockDownPins knockDownPins);

    boolean isEnd();

    Stream<Pitching> stream();
}
