package bowling.dto;

import bowling.domain.BowlingGame;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameDto {
    List<BowlingDto> bowlingGameDto;

    private BowlingGameDto(List<BowlingDto> bowlingGameDto) {
        this.bowlingGameDto = bowlingGameDto;
    }

    public static BowlingGameDto valueOf(BowlingGame bowlingGame) {
        List<BowlingDto> result = new ArrayList<>();
        bowlingGame.getBowlingGame()
                .forEach(bowling -> result.add(BowlingDto.valueOf(bowling)));
        return new BowlingGameDto(result);
    }

    public List<BowlingDto> getBowlingDto() {
        return this.bowlingGameDto;
    }

}
