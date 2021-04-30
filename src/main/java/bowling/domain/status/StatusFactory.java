package bowling.domain.status;

import bowling.domain.Pitches;

import java.util.ArrayList;
import java.util.List;

public class StatusFactory extends Symbol {
    private static final List<Status> statuses = new ArrayList<>();

    static {
        statuses.add(new Strike());
        statuses.add(new Spare());
        statuses.add(new Open());
        statuses.add(new Bonus());
    }

    public static Status status(Pitches pitches) {
        return statuses.stream()
                .filter(status -> status.condition(pitches))
                .findFirst()
                .orElse(new Default());
    }

    public static Status status(int fallenPins, int accumulatedPins, int pitchIndex) {
        return statuses.stream()
                .filter(status -> status.conditionOf(fallenPins, accumulatedPins, pitchIndex))
                .findFirst()
                .orElse(new Default());
    }
}
