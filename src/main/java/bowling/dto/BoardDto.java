package bowling.dto;

import bowling.domain.Board;
import bowling.domain.frame.Round;

import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {
    private final List<Integer> allRounds;
    private final GameResultsDto gameResultsDto;

    private BoardDto(List<Integer> allRounds, GameResultsDto gameResultsDto) {
        this.allRounds = allRounds;
        this.gameResultsDto = gameResultsDto;
    }

    public static BoardDto of(Board board) {
        List<Integer> allRounds = board.getAllRounds().stream()
                .map(Round::value)
                .collect(Collectors.toList());
        return new BoardDto(allRounds, GameResultsDto.of(board.getGameResults()));
    }

    public List<Integer> getAllRounds() {
        return allRounds;
    }

    public GameResultsDto getGameResultsDto() {
        return gameResultsDto;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                ", gameResultDto=" + gameResultsDto +
                '}';
    }
}
