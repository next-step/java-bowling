package bowling.dto;

import bowling.domain.result.GameResults;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class GameResultsDto {
    private final List<GameResultDto> values;

    private GameResultsDto(List<GameResultDto> values) {
        this.values = values;
    }

    public static GameResultsDto of(GameResults gameResults) {
        return gameResults.getValues().stream()
                .map(GameResultDto::of)
                .collect(collectingAndThen(toList(), GameResultsDto::new));
    }

    public List<GameResultDto> getValues() {
        return values;
    }
}
