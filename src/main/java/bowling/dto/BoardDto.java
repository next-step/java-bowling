package bowling.dto;

public class BoardDto {
    private final FramesDto framesDto;
    private final ScoresDto scoresDto;

    public BoardDto(FramesDto framesDto, ScoresDto scoresDto) {
        this.framesDto = framesDto;
        this.scoresDto = scoresDto;
    }

    public FramesDto getFramesDto() {
        return framesDto;
    }

    public ScoresDto getScoresDto() {
        return scoresDto;
    }
}
