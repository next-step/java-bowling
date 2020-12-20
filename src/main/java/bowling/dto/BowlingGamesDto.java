package bowling.dto;

import java.util.List;

public class BowlingGamesDto {
    private final List<BowlingGameDto> value;

    private BowlingGamesDto(List<BowlingGameDto> value) {
        this.value = value;
    }

    public static BowlingGamesDto of(List<BowlingGameDto> value) {
        return new BowlingGamesDto(value);
    }

    public List<BowlingGameDto> getValue() {
        return value;
    }
}
