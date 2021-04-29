package bowling.domain.status;

import bowling.domain.Pitches;

public interface Status {
    boolean condition(Pitches pitches);

    String keyword();

    String display(Pitches pitches);
}
