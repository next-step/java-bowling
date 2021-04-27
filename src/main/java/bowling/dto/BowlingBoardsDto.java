package bowling.dto;

import bowling.domain.bowlingboard.BowlingBoard;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingBoardsDto {

    public final List<BowlingBoardDto> bowlingBoardDtos;

    private BowlingBoardsDto(List<BowlingBoardDto> bowlingBoardDtos) {
        this.bowlingBoardDtos = bowlingBoardDtos;
    }

    public static BowlingBoardsDto of(List<BowlingBoard> bowlingBoards) {
        return bowlingBoards.stream()
                .map(BowlingBoardDto::of)
                .collect(Collectors.collectingAndThen(Collectors.toList(), BowlingBoardsDto::new));
    }

}
