package bowling.domain.status;

import bowling.domain.Pitches;

import java.util.ArrayList;
import java.util.List;

public class Default extends Symbol implements Status {
    @Override
    public boolean condition(Pitches pitches) {
        return false;
    }

    @Override
    public String keyword() {
        return null;
    }

    @Override
    public String display(Pitches pitches) {
        List<String> result = new ArrayList<>();
        pitches.forEach(pitch -> result.add(String.valueOf(pitch.intValue())));
        return String.join(DELIMITER, result);
    }
}
