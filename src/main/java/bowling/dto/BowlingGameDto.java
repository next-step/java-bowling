package bowling.dto;

import bowling.domain.BowlingGame;

import java.util.List;

public class BowlingGameDto {
    List<BowlingDto> bowlingGameDto;

    private BowlingGameDto(List<BowlingDto> bowlingGameDto) {
        this.bowlingGameDto = bowlingGameDto;
    }

    public static BowlingGameDto valueOf(BowlingGame bowlingGame) {
        
    }


}
