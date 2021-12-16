package bowling.service.dto;

import bowling.domain.Board;

import java.util.List;

public class BoardDto {

    private final List<String> allRounds;
    private final GameResultDto gameResultDto;

    private BoardDto(List<String> allRounds, GameResultDto gameResultDto, List<String> allRounds1, GameResultDto gameResultDto1) {
        this.allRounds = allRounds1;
        this.gameResultDto = gameResultDto1;
    }

    public static BoardDto of(Board board) {
        return null;
    }

}
