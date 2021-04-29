package bowling.domain.status;

import bowling.domain.Pitches;

import java.util.ArrayList;
import java.util.List;

public class Open extends Symbol implements Status {
    private final String keyword;

    public Open(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean condition(Pitches pitches) {
        return pitches.count() == 2 && pitches.sum() < 10;
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
