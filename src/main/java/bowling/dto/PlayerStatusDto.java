package bowling.dto;

public class PlayerStatusDto {
    private final PinsDto pinsDto;
    private final BoardDto boardDto;

    public PlayerStatusDto(PinsDto pinsDto, BoardDto boardDto) {
        this.pinsDto = pinsDto;
        this.boardDto = boardDto;
    }

    public PinsDto getPinsDto() {
        return pinsDto;
    }

    public BoardDto getBoardDto() {
        return boardDto;
    }
}
