package bowling.domain.status;

import bowling.domain.Pitches;

import java.util.ArrayList;
import java.util.List;

public class Bonus extends Symbol implements Status {
    private final String keyword;

    public Bonus(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean condition(Pitches pitches) {
        return pitches.count() == 3;
    }

    @Override
    public String keyword() {
        return keyword;
    }

    @Override
    public String display(Pitches pitches) {
        List<String> result = new ArrayList<>();
        pitches.forEach(pitch -> result.add(String.valueOf(pitch.intValue())));
        return String.join(DELIMITER, result);
    }
}
