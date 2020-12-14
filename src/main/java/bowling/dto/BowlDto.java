package bowling.dto;

public class BowlDto {
    private final FramesDto framesDto;
    private final ScoresDto scoresDto;

    public BowlDto(FramesDto framesDto, ScoresDto scoresDto) {
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
