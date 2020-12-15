package bowling.domain.pitchings;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;

import java.util.stream.Stream;

//todo abstract class
public interface Pitchings extends Iterable<Pitching> {
    void addPitching(KnockDownPins knockDownPins);

    boolean isEnd();

    Stream<Pitching> stream();

    boolean contains(Pitching pitching);

    int getTotalScore();

    Pitching getNextPitching();

    Pitching getNextAndNextPitching();
}
