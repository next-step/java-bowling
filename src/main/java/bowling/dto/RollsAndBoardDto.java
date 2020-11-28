package bowling.dto;

public class RollsAndBoardDto {
    private final RollsDto rollsDto;
    private final BoardDto boardDto;

    public RollsAndBoardDto(RollsDto rollsDto, BoardDto boardDto) {
        this.rollsDto = rollsDto;
        this.boardDto = boardDto;
    }

    public RollsDto getRollsDto() {
        return rollsDto;
    }

    public BoardDto getBoardDto() {
        return boardDto;
    }
}
