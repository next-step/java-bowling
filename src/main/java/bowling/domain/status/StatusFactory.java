package bowling.domain.status;

import bowling.domain.Pitches;

import java.util.ArrayList;
import java.util.List;

public class StatusFactory extends Symbol {
    private static final List<Status> statuses = new ArrayList<>();

    static {
        statuses.add(new Strike(STRIKE));
        statuses.add(new Spare(SPARE));
        statuses.add(new Open(OPEN));
        statuses.add(new Bonus(BONUS));
    }

    public static Status status(Pitches pitches) {
        return statuses.stream()
                .filter(status -> status.condition(pitches))
                .findFirst()
                .orElse(new Default());
    }
}
