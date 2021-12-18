package bowling.service.dto;

import bowling.domain.Board;
import bowling.domain.frame.Round;

import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {

    private final List<Integer> allRounds;
    private final GameResultDto gameResultDto;

    private BoardDto(List<Integer> allRounds, GameResultDto gameResultDto) {
        this.allRounds = allRounds;
        this.gameResultDto = gameResultDto;
    }

    public static BoardDto of(Board board) {
        List<Integer> allRounds = board.getAllRounds().stream()
                .map(Round::value)
                .collect(Collectors.toList());
        return new BoardDto(allRounds, GameResultDto.of(board.getGameResult()));
    }

    public List<Integer> getAllRounds() {
        return allRounds;
    }

    public GameResultDto getGameResultDto() {
        return gameResultDto;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                ", gameResultDto=" + gameResultDto +
                '}';
    }
}
