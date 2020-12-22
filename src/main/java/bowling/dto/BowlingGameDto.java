package bowling.dto;

import java.util.Iterator;
import java.util.List;

public class BowlingGameDto implements Iterable<PlayerDto> {
    private final List<PlayerDto> value;

    private BowlingGameDto(List<PlayerDto> value) {
        this.value = value;
    }

    public static BowlingGameDto of(List<PlayerDto> value) {
        return new BowlingGameDto(value);
    }

    @Override
    public Iterator<PlayerDto> iterator() {
        return value.iterator();
    }
}
