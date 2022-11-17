package bowling.step4.dto;

import java.util.List;

public class ResultDto {

    public final String name;
    public final List<PitchDto> pitchesDto;
    public final List<ScoreDto> scoresDto;

    public ResultDto(String name, List<PitchDto> pitchesDto, List<ScoreDto> scoresDto) {
        this.name = name;
        this.pitchesDto = pitchesDto;
        this.scoresDto = scoresDto;
    }
}
