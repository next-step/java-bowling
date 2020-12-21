package bowling.dto;

import java.util.Iterator;
import java.util.List;

public class BowlingGamesDto implements Iterable<BowlingGameDto> {
    private final List<BowlingGameDto> value;

    private BowlingGamesDto(List<BowlingGameDto> value) {
        this.value = value;
    }

    public static BowlingGamesDto of(List<BowlingGameDto> value) {
        return new BowlingGamesDto(value);
    }

    @Override
    public Iterator<BowlingGameDto> iterator() {
        return value.iterator();
    }
}
