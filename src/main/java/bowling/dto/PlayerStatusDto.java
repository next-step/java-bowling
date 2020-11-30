package bowling.dto;

public class PlayerStatusDto {
    private final RollsDto rollsDto;
    private final BoardDto boardDto;

    public PlayerStatusDto(RollsDto rollsDto, BoardDto boardDto) {
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
